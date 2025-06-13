package shimizu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Select {
	String url = Text.url;
	String user_name = Text.user_name;
	String password = Text.password;
	
	
	public static String selectReceipt(int menu, String what) {//注文履歴を表示するメソッド
		String sqlReceipt = "select * from receipt join price_history "
				+ "on receipt.price_history_id = price_history.price_history_id"
				+ "join product_detail on price_history.product_detail_id = product_detail.product_detail_id"
				+ "join product_group on product_detail.product_group_id = product_group.product_group_id"
				+ "join product_type on product_group.product_type_id = product_type.product_type_id"
				+ "where ? = ?";
		
		int m = menu;
		String w  = what;
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlReceipt ) ;
			)
			{
			
				switch(m) {
				//メニュー選択で入力された値に基づき1つめの?にカラム名を代入
				case 1:ps.setString(1, "receipt_id");break;//注文ID
				case 2:ps.setString(1, "order_date");break;//日付
				case 3:ps.setString(1, "customer_name");break;//顧客名
				case 4:ps.setString(1, "product_detail_name");break;//商品名
				}//switch
				
				ps.setString(2, w);
				//検索したい値を2つめの?に代入

			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("receipt_id");//注文ID
				String date = rs.getString("order_date");//日付
				String customer = rs.getString("customer_name");//顧客名
				String product = rs.getString("product_name");//商品名
				int price =rs.getInt("price");//価格
				String remark = ("remark");//備考
				
				System.out.printf("%s     %s\n  %s店\n取引内容：%s  %d円\n%s",id,date,customer,product,price,remark);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(Text.);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
			
		
		
	}//selectReceipt
	
	
	public static String selectCustomer(int menu, String what) {//顧客情報を表示するメソッド
		String sqlCustomer = "select * from customer join customer_group "
				+ "on customer.customer_group_id = customer_group.customer_group_id ";
		
		int m = menu;
		String w  = what;
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlCustomer ) ;
			)
			{
			
			if(!(m == 4)) {
				//一覧表示ではなく検索ならwhere句をSQL文に追加する
				sqlCustomer += "where ? = ?";
				
				switch(m) {
				//メニュー選択で入力された値に基づき1つめの?にカラム名を代入
				case 1:ps.setString(1, "customer_name");break;//顧客名
				case 2:ps.setString(1, "address");break;//住所
				case 3:ps.setString(1, "customer_group");break;//業務形態
				}//switch
				
				ps.setString(2, w);
				//検索したい値を2つめの?に代入
				
				System.out.println("if");//debug
			}//if
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("customer_id");//顧客ID
				String group = rs.getString("customer_group_name");//店舗形態
				String name = rs.getString("customer_name");//顧客名
				
				System.out.printf("%s: [%s]  %s店",id,group,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(Text.);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
			
			
	}//selectCustomer
	
	
	public static String selectItem(int menu, String what) {//商品情報を表示するメソッド
		String sqlItem = "select * from price_history join product_detail "
				+ "on price_history.product_detail_id = product_detail.product_detail_id "
				+ "join product_group on product_detail.product_group_id = product_group.product_group_id "
				+ "where ? = ?";
		
		int m = menu;
		String w  = what;
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlItem ) ;
			)
			{
			
			if(!(m == 5)) {
				//一覧表示ではなく検索ならwhere句をSQL文に追加する
				sqlItem += "where ? = ?";
				
				switch(m) {
				case 1:ps.setString(1, "group_detail_id");break;//商品ID
				case 2:ps.setString(1, "price");break;//価格
				case 3:ps.setString(1, "group_detail_name");break;//商品名
				case 4:ps.setString(1, "product_group");break;//分類
				}//switch
					
				ps.setString(2, w);
				//検索したい値を2つめの?に代入
				
				System.out.println("if");//debug
			}//if
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("product_detail_id");//商品ID
				String group = rs.getString("product_group_name");//分類
				String name = rs.getString("product_detail_name");//商品名
				int price = rs.getInt("price");//価格
				
				System.out.printf("%s: [%s]  %s  %d円",id,group,name,price);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(Text.);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
	}//selectItem
	
	
	public static String selectCustomerGroup() {//店舗形態を表示するメソッド
		String sqlCustomerGroup = "select * from customer_group";
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlCustomerGroup ) ;
			)
			{
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("customer_group_id");//店舗形態ID
				String name = rs.getString("customer_group_name");//店舗形態
				
				System.out.printf("%s:   %s\n",id,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(Text.);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
	}//selectCustomerGroup
	
	
	public static String selectItemGroup() {//商品分類(食べ物なら「野菜」とか)を表示するメソッド
		String sqlItemGroup = "select * from product_group";
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlItemGroup ) ;
			)
			{
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("product_group_id");//分類ID
				String name = rs.getString("product_group_name");//分類名
				
				System.out.printf("%s:   %s\n",id,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(Text.);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
	}//selectItemGroup
	
	
	public static ArrayList<String> selectMember() {//従業員を表示するメソッド
		String sqlMember = "select * from member";
		
		ArrayList <String> members = new ArrayList<>();//全てのIDをJudge()に渡すためのアレイリスト
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlMember ) ;
			)
			{
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("member_no");//従業員の番号
				String name = rs.getString("eName");//従業員の名前
				String eID = rs.getString("eID");//従業員のID(ユーザーネーム)
				String ePass = rs.getString("ePass");//パスワード
				
				System.out.println("-------------------------------------------");
				System.out.printf("%s:   %s\nID:%s   Pass:%s\n",id,name,eID,ePass);
				
				members.add(eID);//IDを1行ずつリストに格納、繰り返されることで全てのIDが格納される
				
			}//while
			System.out.println("-------------------------------------------");
			}
			
			catch(Exception e){
				System.out.println(Text.);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
		
		return members;
	}//selectMember
	
}//Select
