package shimizu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import miyakoshi.Sales;
import yoshida.Text;

public class Select {
	static String url = Text.url;
	static String user_name = Text.user_name;
	static String password = Text.password;
<<<<<<< HEAD

	public static int[] selectReceipt(int menu, String what) {//注文履歴を表示するメソッド
		String sqlReceipt = "select * from receipt join price_history "
				+ "on receipt.price_history_id = price_history.price_history_id "
				+ "join product_detail on price_history.product_detail_id = product_detail.product_detail_id "
				+ "join product_type on product_detail.product_type_id = product_type.product_type_id "
				+ "join product_group on product_type.product_group_id = product_group.product_group_id "
				+ "join customer on receipt.customer_id = customer.customer_id"
				+ "where order_date between price_history.start_date and coalesce(price_history.last_date,current_date) ";

		String dataCount = "select count(receipt_id) from receipt";

=======
	
	
	public static int [] selectReceipt(int menu, String what) {//注文履歴を表示するメソッド
		String sqlReceipt = "select * from receipt "
				+ "				join product_detail on receipt.product_detail_id = product_detail.product_detail_id "
				+ "               join price_history ON product_detail.product_detail_id = price_history.product_detail_id "
				+ "				join product_type on product_detail.product_type_id = product_type.product_type_id "
				+ "				join product_group on product_type.product_group_id = product_group.product_group_id "
				+ "				join customer on receipt.customer_id = customer.customer_id "
				+ "				where date between price_history.start_date and coalesce(price_history.last_date,current_date) ";
				
		
		String dataCount = "select count(main_id) from receipt";
		
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git
		//最後の行で注文の日付と価格履歴テーブルを照らし合わせて注文の日の値段を取得しようとしている

		int m = menu;
		String w = what;

		int data = 0;//データを20件ずつ表示するために値を増加させていく変数

		int count = 0;//データ件数を入れる変数

		int[] i = { 0, 0 };//1つ目がデータ件数、2つ目が合計金額を入れる配列

		Sales sales = new Sales();

		if (m == 7 || m == 8) {
			//日次レポートのときのSQL文の追加
<<<<<<< HEAD
			sqlReceipt += "and  order_date between ? and ?";
			if (m == 8) {
=======
			sqlReceipt += "and  date between ? and ?";
			if(m == 8) {
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git
				//日次と顧客指定のときのSQL文の追加
				sqlReceipt += "and customer_name = ?";
			}
		}

		else if (m == 9 || m == 10) {
			//月次レポートのときのSQL文の追加
<<<<<<< HEAD
			sqlReceipt += "and order_date like ?";
			if (m == 10) {
=======
			sqlReceipt += "and date like ?";
			if(m == 10) {
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git
				//月次と顧客指定のレポートのときのSQL文の追加
				sqlReceipt += "and customer_name = ?";
			}
		}

		else if (m == 6) {
			sqlReceipt += "limit ?,20";
		}

		else {
			//上記以外で、一覧表示ではなく検索ならwhere句をSQL文に追加する
			sqlReceipt += "and ? = ?";
		} //else

		try (
				Connection con = DriverManager.getConnection(url, user_name, password); //finallyがなくても操作できる
				PreparedStatement psCount = con.prepareStatement(dataCount);
				PreparedStatement ps = con.prepareStatement(sqlReceipt);) {

			if (m == 7 || m == 8) {
				ps.setString(1, sales.startDate());//始めの日指定
				ps.setString(2, sales.lastDate());//終わりの日指定

				if (m == 8) {
					ps.setString(3, w);//顧客名を?に代入
				}
			}

			else if (m == 9 || m == 10) {
				ps.setString(1, sales.Month());//月指定

				if (m == 10) {
					ps.setString(2, w);//顧客名を?に代入
				}
			}

			else if (m == 6) {
				ResultSet rsCount = psCount.executeQuery();
<<<<<<< HEAD
				while (rsCount.next()) {
					count = rsCount.getInt("count(receipt_id)");
				} //データ件数を数える

=======
				while(rsCount.next()) {
			    count = rsCount.getInt("count(main_id)");
				}//データ件数を数える
				
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git
			}

			else {
				switch (m) {
				//メニュー選択で入力された値に基づき1つめの?にカラム名を代入
<<<<<<< HEAD
				case 1:
					ps.setString(1, "receipt_id");
					break;//注文ID
				case 2:
					ps.setString(1, "order_date");
					break;//日付
				case 3:
					ps.setString(1, "customer_name");
					break;//顧客名
				case 4:
					ps.setString(1, "product_detail_name");
					break;//商品名
				case 5:
					ps.setString(1, "product_type_name");
					break;//商品分類
=======
				case 1:ps.setString(1, "main_id");break;//注文ID
				case 2:ps.setString(1, "date");break;//日付
				case 3:ps.setString(1, "customer_name");break;//顧客名
				case 4:ps.setString(1, "product_detail_name");break;//商品名
				case 5:ps.setString(1, "product_type_name");break;//商品分類
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git
				}//switch

				ps.setString(2, w);
				//検索したい値を2つめの?に代入
			} //else

			total: while (true) {
				if (m == 6) {
					ps.setInt(1, data);
				} //一覧表示のときだけループの中でdataを増加させて表示をわけられるように

				ResultSet rs = ps.executeQuery();
<<<<<<< HEAD

				while (rs.next()) {

					String id = rs.getString("receipt_id");//注文ID
					String date = rs.getString("order_date");//日付
					String customer = rs.getString("customer_name");//顧客名
					String product = rs.getString("product_detail_name");//商品名
					int price = rs.getInt("price");//価格
					String remark = ("remark");//備考

					System.out.printf("%4s     %s\n  %s店\n取引内容：%s  %d円\n%s", id, date, customer, product, price,
							remark);

					i[1] += price;//合計金額の計算
				} //while

				if (m == 6 && data < (count - 20)) {//一覧表示かつデータ件数の残りが20件以上だったら
=======
			
			while(rs.next()) {
				
				String id = rs.getString("main_id");//注文ID
				String date = rs.getString("date");//日付
				String customer = rs.getString("customer_name");//顧客名
				String product = rs.getString("product_detail_name");//商品名
				int price =rs.getInt("price");//価格
				String remark = ("remark");//備考
				
				System.out.printf("%4s     %s\n  %s店\n取引内容：%s  %d円\n%s\n",id,date,customer,product,price,remark);
				
				i[1] += price;//合計金額の計算
			}//while
			
			
			if(m == 6 && data<(count-20)) {//一覧表示かつデータ件数の残りが20件以上だったら
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git
					System.out.println("\n次のページを表示しますか？：Enter");

					while (true) {
						String enter = new Scanner(System.in).nextLine();
						if (enter.equals("")) {//Enterが押されたら
							data += 20;
							break;//次のデータを表示させるためにdataを増加させてループ抜ける
						}

						else {//Enter以外の入力があった場合
							System.out.println("\n~~~次のページを表示したい場合はEnterのみを押してください~~~");
							continue;//Enterのみを入力させるためループを繰り返す
						}
					} //while
				} //if

				else {
					break total;
				}

				i[0] = count;
			} //totalwhile
		} //try

		catch (Exception e) {
			System.out.println(Text.tryCatch);
			System.out.println(e);//debug
		}

		finally {
			System.out.println("select〇");//debug
		}

		return i;

	}//selectReceipt

	public static int selectCustomer(int menu, String what) {//顧客情報を表示するメソッド
		String sqlCustomer = "select * from customer join customer_group "
				+ "on customer.customer_group_id = customer_group.customer_group_id ";
		String dataCustomer = "select count(customer_id) from customer";

		int m = menu;
		String w = what;

		int data = 0;//データを20件ずつ表示するために値を増加させていく変数

		int count = 0;//データ件数を入れる変数

		if (m == 4) {
			System.out.println("一覧表示");//debug
			sqlCustomer += "limit ?,20";
		} else {
			//一覧表示ではなく検索ならwhere句をSQL文に追加する
			sqlCustomer += "where ? = ?";
			System.out.println("if");//debug
		} //if

		try (
				Connection con = DriverManager.getConnection(url, user_name, password); //finallyがなくても操作できる
				PreparedStatement psCount = con.prepareStatement(dataCustomer);
				PreparedStatement ps = con.prepareStatement(sqlCustomer);) {

			ResultSet rsCount = psCount.executeQuery();

			while (rsCount.next()) {
				count = rsCount.getInt("count(customer_id)");
			} //データ件数を数える

			if (!(m == 4)) {
				switch (m) {
				//メニュー選択で入力された値に基づき1つめの?にカラム名を代入
				case 1:
					ps.setString(1, "customer_name");
					break;//顧客名
				case 2:
					ps.setString(1, "address");
					break;//住所
				case 3:
					ps.setString(1, "customer_group_name");
					break;//業務形態
				}//switch

				ps.setString(2, w);
				//検索したい値を2つめの?に代入
<<<<<<< HEAD
			} //if
=======
			}//if
				
			
			total:while(true) {
				
				if(m== 4) {
					ps.setInt(1,data);
				}
				
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String id = rs.getString("customer_id");//顧客ID
				String group = rs.getString("customer_group_name");//店舗形態
				String name = rs.getString("customer_name");//顧客名
				
				System.out.printf("%4s: [%s]  %s店\n",id,group,name);
				
				
			}//while
			
			if(m == 4 && data<(count-20)) {//一覧表示かつデータ件数の残りが20件以上だったら
				System.out.println("\n次のページを表示しますか？：Enter");
				
				while(true) {
				String enter = new Scanner(System.in).nextLine();
				if(enter.equals("")) {//Enterが押されたら
					data+=20;break;//次のデータを表示させるためにdataを増加させてループ抜ける
				}
				
				else{//Enter以外の入力があった場合
					System.out.println("\n~~~次のページを表示したい場合はEnterのみを押してください~~~");
					continue;//Enterのみを入力させるためループを繰り返す
				}
				}//while
			}//if
				
				else { break total;}
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git

			total: while (true) {

				if (m == 4) {
					ps.setInt(1, data);
				}

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					String id = rs.getString("customer_id");//顧客ID
					String group = rs.getString("customer_group_name");//店舗形態
					String name = rs.getString("customer_name");//顧客名

					System.out.printf("%4s: [%s]  %s店\n", id, group, name);

				} //while

				if (m == 4 && data < (count - 20)) {//一覧表示かつデータ件数の残りが20件以上だったら
					System.out.println("\n次のページを表示しますか？：Enter");

					while (true) {
						String enter = new Scanner(System.in).nextLine();
						if (enter.equals("")) {//Enterが押されたら
							data += 20;
							break;//次のデータを表示させるためにdataを増加させてループ抜ける
						}

						else {//Enter以外の入力があった場合
							System.out.println("\n~~~次のページを表示したい場合はEnterのみを押してください~~~");
							continue;//Enterのみを入力させるためループを繰り返す
						}
					} //while
				} //if

				else {
					break total;
				}

				break;
			} //totalwhile

		} //try

		catch (Exception e) {
			System.out.println(Text.tryCatch);
			System.out.println(e);//debug
		}

		finally {
			System.out.println("select〇");//debug
		}

		return count;
	}//selectCustomer

	public static int selectItem(int menu, String what) {//商品情報を表示するメソッド
		String sqlItem = "select * from price_history join product_detail "
				+ "on price_history.product_detail_id = product_detail.product_detail_id "
				+ "join product_type on product_detail.product_type_id = product_type.product_type_id ";
		String dataItem = "select count(product_detail_id) from product_detail ";

		int m = menu;
		String w = what;

		System.out.printf("menu:%d  what:%s\n", m, w);//debug

		int data = 0;
		int count = 0;

		if (m == 5) {
			System.out.println("一覧表示");//debug
			sqlItem += "where last_date is null "
					+ "order by product_detail.product_detail_id asc "
					+ " limit ?,20 ";

		} //if
		try (
				Connection con = DriverManager.getConnection(url, user_name, password); //finallyがなくても操作できる
				PreparedStatement psCount = con.prepareStatement(dataItem);
				PreparedStatement ps = con.prepareStatement(sqlItem);) {

			if (m == 5) {
				ResultSet rsCount = psCount.executeQuery();
				while (rsCount.next()) {
					count = rsCount.getInt("count(product_detail_id)");
				} //データ件数を数える
			} //if
			else {
				// 検索処理
				String columnName = "";
				switch (m) {
				case 1:
					columnName = "product_detail.product_detail_id";
					break;
				case 2:
					columnName = "price";
					break;
				case 3:
					columnName = "name";
					break;
				case 4:
					columnName = "product_detail.product_type_id";
					break;
				}
<<<<<<< HEAD
=======
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//検索のときデータあるのにここに入らない
				//他の部分には入ってそう
				
				String id = rs.getString("product_detail_id");//商品ID
				String group = rs.getString("product_type_name");//分類
				String name = rs.getString("product_detail_name");//商品名
				int price = rs.getInt("price");//価格
				
				System.out.printf("%4s: [%s]  %s  %d円\n",id,group,name,price);
				
				System.out.println("while");//debug
			}//while
			if(m == 5 && data<(count-20)) {//一覧表示かつデータ件数の残りが20件以上だったら
				System.out.println("\n次のページを表示しますか？：Enter");
				
				while(true) {
				String enter = new Scanner(System.in).nextLine();
				if(enter.equals("")) {//Enterが押されたら
					data+=20;break;//次のデータを表示させるためにdataを増加させてループ抜ける
				}
				
				else{//Enter以外の入力があった場合
					System.out.println("\n~~~次のページを表示したい場合はEnterのみを押してください~~~");
					continue;//Enterのみを入力させるためループを繰り返す
				}
				}//while
			}//if
				
				else { 
					System.out.println("一覧表示以外");//debug
					break total;}
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git

				// SQL再定義
				sqlItem += "where " + columnName + " = ?";

				// psの再準備
				try (PreparedStatement psSearch = con.prepareStatement(sqlItem)) {
					psSearch.setString(1, w); // 検索条件をセット

					ResultSet rs = psSearch.executeQuery();

					while (rs.next()) {
						String id = rs.getString("product_detail_id");//商品ID
						String group = rs.getString("product_type_name");//分類
						String name = rs.getString("name");//商品名
						int price = rs.getInt("price");//価格

						System.out.printf("%4s: [%s]  %s  %d円\n", id, group, name, price);
					}

					return 1; // 見つかった数など
				}
			}

			total: while (true) {

				if (m == 5) {
					ps.setInt(1, data);
				}

				ResultSet rs = ps.executeQuery();
<<<<<<< HEAD
=======
			
			while(rs.next()) {
				
				String id = rs.getString("member_no");//従業員の番号
				String name = rs.getString("Name");//従業員の名前
				String eID = rs.getString("eID");//従業員のID(ユーザーネーム)
				String ePass = rs.getString("ePass");//パスワード
				
				System.out.println("-------------------------------------------");
				System.out.printf("%4s:   %s\nID:%s   Pass:%s\n",id,name,eID,ePass);
				
				//members.add(eID);←IDを1行ずつリストに格納、繰り返されることで全てのIDが格納される、アレイリスト関連
>>>>>>> branch 'master' of https://github.com/Kanata040516/group13.git

				while (rs.next()) {

					String id = rs.getString("product_detail_id");//商品ID
					String group = rs.getString("product_type_name");//分類
					String name = rs.getString("product_detail_name");//商品名
					int price = rs.getInt("price");//価格

					System.out.printf("%4s: [%s]  %s  %d円\n", id, group, name, price);

					System.out.println("while");//debug
				} //while
				if (m == 5 && data < (count - 20)) {//一覧表示かつデータ件数の残りが20件以上だったら
					System.out.println("\n次のページを表示しますか？：Enter");

					while (true) {
						String enter = new Scanner(System.in).nextLine();
						if (enter.equals("")) {//Enterが押されたら
							data += 20;
							break;//次のデータを表示させるためにdataを増加させてループ抜ける
						}

						else {//Enter以外の入力があった場合
							System.out.println("\n~~~次のページを表示したい場合はEnterのみを押してください~~~");
							continue;//Enterのみを入力させるためループを繰り返す
						}
					} //while
				} //if

				else {
					System.out.println("一覧表示以外");//debug
					break total;
				}

			} //totalwhile

		} //try

		catch (Exception e) {
			System.out.println(Text.tryCatch);
			System.out.println(e);//debug
		}

		finally {
			System.out.println("selectItemのfinally");//debug
		}

		return count;

	}//selectItem

	public static String selectCustomerGroup() {//店舗形態を表示するメソッド
		String sqlCustomerGroup = "select * from customer_group";
		String id = null;

		try (
				Connection con = DriverManager.getConnection(url, user_name, password); //finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement(sqlCustomerGroup);) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getString("customer_group_id");//店舗形態ID
				String name = rs.getString("customer_group_name");//店舗形態

				System.out.printf("%4s:   %s\n", id, name);
			} //while

		}

		catch (Exception e) {
			System.out.println(Text.tryCatch);
			System.out.println(e);//debug
		}

		finally {
			System.out.println("select〇");//debug
		}

		return id;
	}//selectCustomerGroup

	public static String selectItemGroup() {//商品分類(食べ物なら「野菜」とか)を表示するメソッド
		String sqlItemGroup = "select * from product_type";
		String id = null;

		try (
				Connection con = DriverManager.getConnection(url, user_name, password); //finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement(sqlItemGroup);) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getString("product_type_id");//分類ID
				String name = rs.getString("product_type_name");//分類名

				System.out.printf("%4s:   %s\n", id, name);
			} //while

		}

		catch (Exception e) {
			System.out.println(Text.tryCatch);
			System.out.println(e);//debug
		}

		finally {
			System.out.println("select〇");//debug
		}
		return id;
	}//selectItemGroup

	public static int selectMember() {//従業員を表示するメソッド

		String sqlMember = "select * from member limit ?,20";
		String dataMember = "select count(member_no) from member";

		int data = 0;
		int count = 0;

		//ArrayList <String> members = new ArrayList<>();←全てのIDをJudge()に渡すためのアレイリスト、いらなそう

		try (
				Connection con = DriverManager.getConnection(url, user_name, password); //finallyがなくても操作できる
				PreparedStatement psCount = con.prepareStatement(dataMember);
				PreparedStatement ps = con.prepareStatement(sqlMember);) {

			ResultSet rsCount = psCount.executeQuery();
			while (rsCount.next()) {
				count = rsCount.getInt("count(member_no)");
			} //データ件数を数える

			total: while (true) {
				ps.setInt(1, data);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					String id = rs.getString("member_no");//従業員の番号
					String name = rs.getString("eName");//従業員の名前
					String eID = rs.getString("eID");//従業員のID(ユーザーネーム)
					String ePass = rs.getString("ePass");//パスワード

					System.out.println("-------------------------------------------");
					System.out.printf("%4s:   %s\nID:%s   Pass:%s\n", id, name, eID, ePass);

					//members.add(eID);←IDを1行ずつリストに格納、繰り返されることで全てのIDが格納される、アレイリスト関連

				} //while
				System.out.println("-------------------------------------------");

				if (data < (count - 20)) {
					System.out.println("\n次のページを表示しますか？：Enter");

					while (true) {
						String enter = new Scanner(System.in).nextLine();
						if (enter.equals("")) {
							data += 20;
							break;
						}

						else {
							System.out.println("\n~~~次のページを表示したい場合はEnterのみを押してください~~~");
							continue;
						}
					} //while

				}

				else {
					break total;
				}
			} //totalwhile

		} //try

		catch (Exception e) {
			System.out.println(Text.tryCatch);
			System.out.println(e);//debug
		}

		finally {
			System.out.println("select〇");//debug
		}

		//return members;←アレイリスト関連
		return count;

	}//selectMember

	public static int price_history() {
		String sqlHistory = "select * from price_history";
		int i = 0;

		try (
				Connection con = DriverManager.getConnection(url, user_name, password); //finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement(sqlHistory);) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			System.out.println(Text.tryCatch);
			System.out.println(e);//debug
		} finally {
			System.out.println("price_history〇");//debug
		}

		return i;
	}//price_history
}//Select
