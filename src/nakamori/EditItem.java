package nakamori;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

import shimizu.Select;
import yoshida.Text;

public class EditItem {

	//SQLのデータベース接続
	static String url = Text.url;
	static String user_name = Text.user_name;
	static String password = Text.password;

	public int menuEdit; //どの処理を行うかを選択する変数

	Scanner sc = new Scanner(System.in);

	public void startMenu() {

		System.out.println("編集メニューを選択してください");
		System.out.println("1: 追加");
		System.out.println("2: 更新");
		System.out.println("3: 削除");
		System.out.println("番号を入力");

		menuEdit = Integer.parseInt(sc.nextLine());

		if (menuEdit == 1) {
			insert();
		}
		else if (menuEdit == 2) {
			update();
		}
		else if (menuEdit == 3) {
			delete();
		}
		else {
			System.out.println("無効なメニュー番号です。");
		}

	}

	//price_history の最新の start_date を取得し、そこに1日足して新しい start_date に使う。

	public static LocalDate getMaxStartDate(String productDetailId) {
		String sql = "SELECT MAX(start_date) FROM price_history WHERE product_detail_id = ?";
		try (
			Connection con = DriverManager.getConnection(url, user_name, password);
			PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, productDetailId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Date date = rs.getDate(1);
					if (date != null) {
						return date.toLocalDate();
					}
				}
			}
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return null;

	}

	//-------------------------------------------

	// 商品情報を追加するメソッド
	public static void insert() {

		int gyousuu1 = Select.price_history() + 11; // price_historyのデータ件数+1
		int gyousuu2 = Select.selectItem(5, null) + 1;// product_detailのデータ件数+1
		System.out.println("\n～以上が現在の商品データです～\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("商品情報を入力してください。");

		
		System.out.print("\n商品の価格：");
		int price = Integer.parseInt(sc.nextLine());
		System.out.println("");
		System.out.println("\n商品の分類番号(下記参照)");
		Select.selectItemGroup(); // 分類の表示
		System.out.print("：");
		String group = sc.nextLine();

		System.out.println("\n商品名：");
		String name = sc.nextLine();

		String sql1 = "INSERT INTO product_detail (product_detail_id, product_type_id, product_detail_name )VALUES ( ?, ?, ?);";
		String sql2 = "INSERT INTO price_history (price_history_id, product_detail_id, price, start_date, last_date) VALUES ( ?, ?, ?, CURRENT_DATE, null );";

		try (
			Connection con = DriverManager.getConnection(url, user_name, password);
            PreparedStatement ps1 = con.prepareStatement(sql1);
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ) {

			con.setAutoCommit(false); // トランザクション開始

			// price_historyのIDを作る（例：gyousuu1を0埋め4桁文字列に）
			String priceHistoryId = String.format("%04d", gyousuu1);
			String productDetailId = String.format("%04d", gyousuu2);

			// product_detailにINSERT
			ps1.setString(1, productDetailId);
			ps1.setString(2, group);
			ps1.setString(3, name);

			int result1 = ps1.executeUpdate();

			// price_historyにINSERT
			ps2.setString(1, priceHistoryId);
			ps2.setString(2, productDetailId); // product_detail_idを使う
			ps2.setInt(3, price);

			int result2 = ps2.executeUpdate();

			//SQL文の送信。
			if (result1 == 1 && result2 == 1) {
				System.out.println("\n1件の書き込みが完了しました。");
				con.commit(); //成功したらコミット
			}
			else {
				System.out.println("\n書き込みに失敗しました。");
			}
		}
		catch (Exception e) {
			System.out.println(Text.tryCatch);
			e.printStackTrace();
		}
		finally {
			System.out.println();
		}

	}

	//-------------------------------------------

	// 商品情報を更新するメソッド
	public static void update() {
		Scanner sc = new Scanner(System.in);

		Select.selectItem(5, null); // product_detailの一覧表示
		System.out.println("\n～以上が現在の商品データです～\n");

		
		System.out.print("更新する商品番号を入力してください。：");
		String code = sc.nextLine();

		System.out.println("\n更新したい項目を選んでください。");
		System.out.println("1: 商品の価格");
		System.out.println("2: 商品名");
		System.out.println("3: 商品の分類");
		System.out.print("番号を入力：");

		int choice = Integer.parseInt(sc.nextLine());

		String columnName = null;
		String newValue = null;
		int newIntValue = 0;
		boolean isPriceUpdate = false;

		if (choice == 1) {
			columnName = "price";
			System.out.print("\n新しい商品の価格を入力してください。：");
			newIntValue = Integer.parseInt(sc.nextLine());
			isPriceUpdate = true;
		}
		else if (choice == 2) {
			columnName = "product_detail_name";
            System.out.print("\n新しい商品名を入力してください。：");
			newValue = sc.nextLine();
		}
		else if (choice == 3) {
			System.out.println( "" );
			Select.selectItemGroup();
			columnName = "product_type_id";
			System.out.print("\n上記を参照して新しい商品の分類番号を入力してください：");
			newValue = sc.nextLine();
		}
		else {
			System.out.println("\n無効な番号です。");
		}

		String sql = isPriceUpdate ?
				"INSERT INTO price_history (price_history_id, product_detail_id, price, start_date, last_date) VALUES ( ?, ?, ?, ?, null );":
				"UPDATE product_detail SET " + (columnName.equals("group") ? "`product_detail_id`" : columnName)
						+ " = ? WHERE product_detail_id = ?;";
		
		if (isPriceUpdate) {
			try (Connection con = DriverManager.getConnection(url, user_name, password)) {

				con.setAutoCommit(false); // トランザクション開始

				String formattedCode = String.format("%04d", Integer.parseInt(code.trim()));
				LocalDate maxStartDate = getMaxStartDate(formattedCode);
				LocalDate newStartDate = LocalDate.now();
				LocalDate newLastDate = newStartDate.minusDays(1);

				// 1. 既存の最新価格履歴のlast_dateを更新
				String updateLastDateSql =
						"UPDATE price_history SET last_date = ? "
						+ "WHERE product_detail_id = ? AND last_date IS NULL";

				try (PreparedStatement psUpdate = con.prepareStatement(updateLastDateSql)) {
					psUpdate.setDate(1, java.sql.Date.valueOf(newLastDate));
					psUpdate.setString(2, formattedCode);
					int updateCount = psUpdate.executeUpdate();

					//System.out.println("price_historyの終了日を更新した件数: " + updateCount);//debug
				}

				// 2. 新しい価格履歴を追加
				String insertPriceHistorySql =
						"INSERT INTO price_history(price_history_id, product_detail_id, price, start_date, last_date) "
								+"VALUES (?, ?, ?, ?, NULL)";

				try (PreparedStatement psInsert = con.prepareStatement(insertPriceHistorySql)) {
					// price_history_idをユニークに作成（例: レコード数+1）
					int newId = Select.price_history() + 11;
					psInsert.setString(1, String.format("%04d", newId));
					psInsert.setString(2, formattedCode);
					psInsert.setInt(3, newIntValue);
					psInsert.setDate(4, Date.valueOf(newStartDate));
					psInsert.executeUpdate();
				}

				con.commit();

				System.out.println("\n価格の更新が完了しました。");

			} catch (Exception e) {
				System.out.println("\n価格更新でエラーが発生しました。");
				e.printStackTrace();
			}
		} else {
			// 価格以外の更新は元のUPDATE文でOK
			try (
					Connection con = DriverManager.getConnection(url, user_name, password);
					PreparedStatement ps = con.prepareStatement(sql)
			) {
				ps.setString(1, newValue);
				ps.setString(2, code);

				int result = ps.executeUpdate();

				if (result == 1) {
					System.out.println("\n1件の書き込みが完了しました。");
				}
				else {
					System.out.println("\n書き込みに失敗しました。");
				}
			}

			catch (Exception e) {
				System.out.println(Text.tryCatch);
				e.printStackTrace();
			}
			finally {
				System.out.println();
			}

		}

	}

	//-------------------------------------------

	// 商品情報を削除するメソッド
	public static void delete() {
		Scanner sc = new Scanner(System.in);

		Select.selectItem(5, null);
		System.out.println("\n～以上が現在の商品データです～\n");

		System.out.print("削除するIDを入力してください。：");
		String code = sc.nextLine();

		try (Connection con = DriverManager.getConnection(url, user_name, password)) {

			con.setAutoCommit(false);

			// 1. price_history を削除
			String deleteHistorySql = "DELETE FROM price_history WHERE product_detail_id = ?";
			try (PreparedStatement ps1 = con.prepareStatement(deleteHistorySql)) {

				ps1.setString(1, code);
				ps1.executeUpdate();
			}

			// 2. product_detail を削除
			String deleteDetailSql = "DELETE FROM product_detail WHERE product_detail_id = ?";
			try (PreparedStatement ps2 = con.prepareStatement(deleteDetailSql)) {

				ps2.setString(1, code);

				int result = ps2.executeUpdate();

				if (result == 1) {
					System.out.println("\n削除しました。");
				} else {
					System.out.println("\n削除対象が見つかりませんでした。");
				}
			}
			con.commit();
		}
		catch (Exception e) {
			System.out.println(Text.tryCatch);
			e.printStackTrace();
		}
		finally {
			System.out.println();
		}

	}

}
