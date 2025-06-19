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
	
	
	public static int [] selectReceipt(int menu, String what) {//注文履歴を表示するメソッド
		String sqlReceipt = "WITH ranked_price AS (SELECT price_history.*, "
				+ "receipt.main_id, receipt.date AS receipt_date, ROW_NUMBER() "
				+ "OVER (PARTITION BY receipt.main_id ORDER BY CASE WHEN receipt.date "
				+ "BETWEEN price_history.start_date AND COALESCE(price_history.last_date, CURRENT_DATE) THEN 0 "
				+ "WHEN price_history.start_date <= receipt.date AND COALESCE(price_history.last_date, CURRENT_DATE) "
				+ "<= CURRENT_DATE THEN 1 ELSE 2 END, price_history.start_date DESC) AS rn FROM receipt "
				+ "LEFT JOIN product_detail ON receipt.product_detail_id = product_detail.product_detail_id "
				+ "LEFT JOIN price_history ON product_detail.product_detail_id = price_history.product_detail_id) "
				+ "SELECT receipt.*, product_detail.*, ranked_price.price, product_type.*, product_group.*, customer.* FROM receipt"
				+ " LEFT JOIN product_detail ON receipt.product_detail_id = product_detail.product_detail_id "
				+ "LEFT JOIN (SELECT * FROM ranked_price WHERE rn = 1) AS ranked_price ON receipt.main_id = ranked_price.main_id "
				+ "JOIN product_type ON product_detail.product_type_id = product_type.product_type_id "
				+ "JOIN product_group ON product_type.product_group_id = product_group.product_group_id "
				+ "JOIN customer ON receipt.customer_id = customer.customer_id ";
				
		
		String dataCount = "select count(main_id) from receipt";
		
		//最後の行で注文の日付と価格履歴テーブルを照らし合わせて注文の日の値段を取得しようとしている
		
		int m = menu;
		String w  = what;
		
		int data = 0;//データを20件ずつ表示するために値を増加させていく変数
		
		int count = 0;//データ件数を入れる変数
		
		int [] i = {0,0};//1つ目がデータ件数、2つ目が合計金額を入れる配列
		
		Sales sales = new Sales();
		
		if(m == 7 || m == 8) {
			//日次レポートのときのSQL文の追加
			sqlReceipt += "and  date between ? and ?";
			if(m == 8) {
				//日次と顧客指定のときのSQL文の追加
				sqlReceipt += "and customer_name = ?";
			}
		}
		
		else if(m == 9 || m == 10) {
		    // 月次レポートのときのSQL文の追加
		    sqlReceipt += "and DATE_FORMAT(receipt.date, '%Y-%m') = ?";
		    if(m == 10) {
		        // 月次と顧客指定のレポートのときのSQL文の追加
		        sqlReceipt += "and customer_name = ?";
		    }
		}
		
		else if (m == 6) {
			sqlReceipt +=  "order by receipt.main_id asc limit ?,20 ";
		}
		
		if(m > 6) {
			sqlReceipt += "order by receipt.main_id asc";
		}
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;
				PreparedStatement psCount = con.prepareStatement( dataCount ) ;
				PreparedStatement ps = con.prepareStatement( sqlReceipt ) ;
			)
			{
			
			if(m == 7 || m == 8) {
				java.sql.Date startDate = java.sql.Date.valueOf(sales.startDate());
				java.sql.Date lastDate = java.sql.Date.valueOf(sales.lastDate());
				ps.setDate(1,startDate);//始めの日指定
				ps.setDate(2,lastDate);//終わりの日指定
				
				if(m == 8) {
					ps.setString(3,w);//顧客名を?に代入
				}
			}
			
			else if(m == 9 || m == 10) {
				ps.setString(1, sales.Month());//月指定
				
				if(m == 10) {
					ps.setString(2, w);//顧客名を?に代入
				}
			}
			
		     else if (m == 6) {
		    	 //System.out.println("SQL Query: " + ps.toString());//debug
				ResultSet rsCount = psCount.executeQuery();
				while(rsCount.next()) {
			    count = rsCount.getInt("count(main_id)");
				}//データ件数を数える
				
			}
			
			else {
				// 検索処理
				String columnName = null;
				switch (m) {
				case 1:
					columnName = "main_id";
					break;
				case 2:
					columnName = "receipt.date";
					break;
				case 3:
					columnName = "customer_name";
					break;
				case 4:
					columnName = "product_detail_name";
					break;
				case 5:
					columnName = "product_type.product_type_id";
				}
				
				// SQL再定義
				sqlReceipt += "and " + columnName + " = ? ORDER BY receipt.main_id asc ";
				
				// psの再準備
				try (PreparedStatement psSearch = con.prepareStatement(sqlReceipt)) {
					if(m == 2) {
						java.sql.Date date = java.sql.Date.valueOf(w);
						psSearch.setDate(1, date); // 検索条件をセット
						//System.out.println("SQL Query: " + psSearch.toString());//debug
					}
					else {
					    psSearch.setString(1, w); // 検索条件をセット
					    //System.out.println("SQL Query: " + psSearch.toString());//debug
					}
					
					ResultSet rs = psSearch.executeQuery();
 
					while (rs.next()) {
						//System.out.println("while(rs.next())");//debug
						String id = rs.getString("main_id");//注文ID
						String date = rs.getString("date");//日付
						String customer = rs.getString("customer_name");//顧客名
						String product = rs.getString("product_detail_name");//商品名
						int amount =  rs.getInt("amount");
						int price =rs.getInt("price")*amount;//価格
						String remark = ("remark");//備考
						
						System.out.printf("[%s]     %s\n  %s店\n  取引内容：%s  %d個\n      %d円\n  %s:\n\n",id,date,customer,product,amount,price,remark);
						
						count++;//データ件数を数える
						i[1] += price;//合計金額の計算
						}
					i[0] = count;
					
					System.out.println("\n---------------------------");
					System.out.printf("合計金額：%d円\n",i[1]);
					return i; // 見つかった数など
					
				}//try
			}//else
			
			total:while(true) {
				//System.out.println("total:while");//debug
				 if(m == 6) {
					 ps.setInt(1, data);
				 }//一覧表示のときだけループの中でdataを増加させて表示をわけられるように
				 
				ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//System.out.println("while(rs.next())");//debug
				String id = rs.getString("main_id");//注文ID
				String date = rs.getString("date");//日付
				String customer = rs.getString("customer_name");//顧客名
				String product = rs.getString("product_detail_name");//商品名
				int amount =  rs.getInt("amount");
				int price =rs.getInt("price")*amount;//価格
				String remark = ("remark");//備考
				
				System.out.printf("[%s]     %s\n  %s店\n  取引内容：%s  %d個\n      %d円\n  %s:\n\n",id,date,customer,product,amount,price,remark);
				
				if(!(m == 6)) {
					count++;
				}
				i[1] += price;//合計金額の計算
			}//while
			
			
			if(m == 6 && data<(count-20)) {//一覧表示かつデータ件数の残りが20件以上だったら
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
				
				i[0] = count;
				}//totalwhile
			System.out.println("\n---------------------------");
			if(count > 0) {
			System.out.printf("合計金額：%d円\n",i[1]);}
			}//try
			
			
			
			catch(Exception e){
				System.out.println(Text.tryCatch);
				System.out.println(e);//debug
			}
		    
		    finally {
		    	//System.out.println("select〇");//debug
		    	if(count == 0) {
					System.out.println("＊＊データが見つかりません＊＊");
				}
		    }
			
		return i;
		
	}//selectReceipt
	
	
	public static int selectCustomer(int menu, String what) {//顧客情報を表示するメソッド
		String sqlCustomer = "select * from customer join customer_group "
				+ "on customer.customer_group_id = customer_group.customer_group_id ";
		String dataCustomer = "select count(customer_id) from customer";
		
		int m = menu;
		String w  = what;
		
		int data = 0;//データを20件ずつ表示するために値を増加させていく変数
		int count = 0;//データ件数を入れる変数
		
		if(m == 4) {
			//System.out.println("一覧表示");//debug
			sqlCustomer +=  "limit ?,20";
		}
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;
				PreparedStatement psCount = con.prepareStatement( dataCustomer ) ;
				PreparedStatement ps = con.prepareStatement( sqlCustomer ) ;
			)
			{
			
			
			if(m == 4) {
				ResultSet rsCount = psCount.executeQuery();
				while(rsCount.next()) {
					count = rsCount.getInt("count(customer_id)");
				}//データ件数を数える
			}//if
			else {
				// 検索処理
				String columnName = null;
				switch (m) {
				case 1:
					columnName = "customer_name";
					break;
				case 2:
					columnName = "address";
					break;
				case 3:
					columnName = "customer.customer_group_id";
					break;
				}
 
				// SQL再定義
				sqlCustomer += "and " + columnName + " = ? ";
 
				// psの再準備
				try (PreparedStatement psSearch = con.prepareStatement(sqlCustomer)) {
					psSearch.setString(1, w); // 検索条件をセット
 
					ResultSet rs = psSearch.executeQuery();
 
					while (rs.next()) {
						String id = rs.getString("customer_id");//顧客ID
						String group = rs.getString("customer_group_name");//店舗形態
						String name = rs.getString("customer_name");//顧客名
						
						System.out.printf("%4s: [%s]  %s店\n",id,group,name);
						count++;//データ件数を数える
						}
					return count; // 見つかった数など
				}
			}
			
			total:while(true) {
				
				if(m== 4) {
					ps.setInt(1,data);
				}
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
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

			break;
			}//totalwhile
			
			}//try
			
			catch(Exception e){
				System.out.println(Text.tryCatch);
				//System.out.println(e);//debug
			}
		    
		    finally {
		    	//System.out.println("select〇");//debug
		    	if(count == 0) {
					System.out.println("＊＊データが見つかりません＊＊");
				}
		    }
			
		
			return count;
	}//selectCustomer
	
	
	public static int selectItem(int menu, String what) {//商品情報を表示するメソッド
		String sqlItem = "select * from price_history join product_detail "
				+ "on price_history.product_detail_id = product_detail.product_detail_id "
				+ "join product_type on product_detail.product_type_id = product_type.product_type_id "
				+ "where last_date is null ";
		String dataItem = "select count(product_detail_id) from product_detail ";
 
		int m = menu;
		String w = what;
 
		//System.out.printf("menu:%d  what:%s\n", m, w);//debug
 
		int data = 0;
		int count = 0;
 
		if (m == 5) {
			//System.out.println("一覧表示");//debug
			sqlItem += "order by product_detail.product_detail_id asc "
					+ " limit ?,20 ";
 
		} //if
		try (
				Connection con = DriverManager.getConnection(url, user_name, password); 
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
				String columnName = null;
				switch (m) {
				case 1:
					columnName = "product_detail.product_detail_id";
					break;
				case 2:
					columnName = "price";
					break;
				case 3:
					columnName = "product_detail_name";
					break;
				case 4:
					columnName = "product_detail.product_type_id";
					break;
				}
 
				// SQL再定義
				sqlItem += "and " + columnName + " = ? order by product_detail.product_detail_id asc";
 
				// psの再準備
				try (PreparedStatement psSearch = con.prepareStatement(sqlItem)) {
					psSearch.setString(1, w); // 検索条件をセット
 
					ResultSet rs = psSearch.executeQuery();
 
					while (rs.next()) {
						String id = rs.getString("product_detail_id");//商品ID
						String group = rs.getString("product_type_name");//分類
						String name = rs.getString("product_detail_name");//商品名
						int price = rs.getInt("price");//価格
 
						System.out.printf("%4s: [%s]  %s  %d円\n", id, group, name, price);
						count++;//データ件数を数える
					}
 
					return count; // 見つかった数など
				}
			}
 
			total: while (true) {
 
				if (m == 5) {
					ps.setInt(1, data);
				}
 
				ResultSet rs = ps.executeQuery();
 
				while (rs.next()) {
 
					String id = rs.getString("product_detail_id");//商品ID
					String group = rs.getString("product_type_name");//分類
					String name = rs.getString("product_detail_name");//商品名
					int price = rs.getInt("price");//価格
 
					System.out.printf("%4s: [%s]  %s  %d円\n", id, group, name, price);
 
					//System.out.println("while");//debug
					
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
					//System.out.println("一覧表示以外");//debug
					break total;
				}
 
			} //totalwhile
 
		} //try
 
		catch (Exception e) {
			System.out.println(Text.tryCatch);
			//System.out.println(e);//debug
		}
 
		finally {
			//System.out.println("selectItemのfinally");//debug
			if(count == 0) {
				System.out.println("＊＊データが見つかりません＊＊");
			}
		}
		
		
		return count;
 
	}//selectItem
	
	
	public static String selectCustomerGroup() {//店舗形態を表示するメソッド
		String sqlCustomerGroup = "select * from customer_group";
		String id = null;
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;
				PreparedStatement ps = con.prepareStatement( sqlCustomerGroup ) ;
			)
			{
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				id = rs.getString("customer_group_id");//店舗形態ID
				String name = rs.getString("customer_group_name");//店舗形態
				
				System.out.printf("%4s:   %s\n",id,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(Text.tryCatch);
				//System.out.println(e);//debug
			}
		    
		    finally {
		    	//System.out.println("select〇");//debug
		    }
		
		return id;
	}//selectCustomerGroup
	
	
	public static String selectItemGroup() {//商品分類(食べ物なら「野菜」とか)を表示するメソッド
		String sqlItemGroup = "select * from product_type";
		String id = null;
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;
				PreparedStatement ps = con.prepareStatement( sqlItemGroup ) ;
			)
			{
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				id = rs.getString("product_type_id");//分類ID
				String name = rs.getString("product_type_name");//分類名
				
				System.out.printf("%4s:   %s\n",id,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(Text.tryCatch);
				//System.out.println(e);//debug
			}
		    
		    finally {
		    	//System.out.println("select〇");//debug
		    }
		return id;
	}//selectItemGroup
	
	
	public static int selectMember() {//従業員を表示するメソッド
		
		String sqlMember = "select * from member limit ?,20";
		String dataMember = "select count(member_no) from member";
		
		int data = 0;
		int count = 0;
		
		//ArrayList <String> members = new ArrayList<>();←全てのIDをJudge()に渡すためのアレイリスト、いらなそう
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;
				PreparedStatement psCount = con.prepareStatement( dataMember ) ;
				PreparedStatement ps = con.prepareStatement( sqlMember ) ;
			)
			{
			
			ResultSet rsCount = psCount.executeQuery();
			while(rsCount.next()) {
				count = rsCount.getInt("count(member_no)");
			}//データ件数を数える
			
			
			total:while(true) {
				ps.setInt(1,data);
				ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("member_no");//従業員の番号
				String name = rs.getString("Name");//従業員の名前
				String eID = rs.getString("eID");//従業員のID(ユーザーネーム)
				String ePass = rs.getString("ePass");//パスワード
				
				System.out.println("-------------------------------------------");
				System.out.printf("%4s:   %s\nID:%s   Pass:%s\n",id,name,eID,ePass);
				
				//members.add(eID);←IDを1行ずつリストに格納、繰り返されることで全てのIDが格納される、アレイリスト関連

			}//while
			System.out.println("-------------------------------------------");
			
			if(data<(count-20)) {
				System.out.println("\n次のページを表示しますか？：Enter");
				
				
				while(true) {
				String enter = new Scanner(System.in).nextLine();
				if(enter.equals("")) {
					data+=20;break;
				}
				
				else{
					System.out.println("\n~~~次のページを表示したい場合はEnterのみを押してください~~~");
					continue;
				}
				}//while
				
				}
				
				else{
					break total;
				}
				}//totalwhile
		
			}//try
			
			catch(Exception e){
				System.out.println(Text.tryCatch);
				//System.out.println(e);//debug
			}
		    
		    finally {
		    	//System.out.println("select〇");//debug
		    }
		
		//return members;←アレイリスト関連
		return count;
	
	}//selectMember
	
	
	public static int price_history() {
		String sqlHistory = "select * from price_history";
		int i = 0;
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;
				PreparedStatement ps = con.prepareStatement( sqlHistory ) ;
			){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				i++;
			}
		}
		catch(Exception e) {
			System.out.println(Text.tryCatch);
			//System.out.println(e);//debug
		}
		finally {
			//System.out.println("price_history〇");//debug
		}
		
		return i;
	}//price_history



}//Select
