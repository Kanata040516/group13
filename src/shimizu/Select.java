package shimizu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Select {
	String url = System.url;
	String user_name = System.user_name;
	String password = System.password;
	
	
	public static String selectReceipt(int menu, String what) {//注文履歴を表示するメソッド
		String sqlReceipt = "";
		
		int m = menu;
		String w = what;
		
	}//selectReceipt
	
	
	public static String selectCustomer(int menu, String what) {//顧客情報を表示するメソッド
		
		String sqlCustomer = "select * from customer join customer_group on customer.customer_group_id = customer_group.customer_group_id where ? = ?;";
		
		int m = menu;
		String w = what;
		
		try(
				Connection co = DriverManager.getConnection(url,user_name,password);
				PreparedStatement ps = co.prepareStatement(sqlCustomer);
				){
			
			switch(m) {
			case 1:ps.setString(1, "customer_name");
			case 2:ps.setString(1, "address");
			case 3:ps.setString(1, "customer_group_name");
			}//メニューで選択された値によってSQL文の1つ目の?を変更
			
			ps.setString(2,w);//2つ目の?を入力された値に
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("customer_id");
				String group = rs.getString("customer_group_name");
				String name = rs.getString("customer_name");
				
				System.out.printf("%s : [%s]    %s \n",id,group,name);
			}//while
		}
		catch(Exception e) {
			System.out.println(errorMessage));
		}
		
	}//selectCustomer
	
	
	public static String selectItem(int menu, String what) {//商品情報を表示するメソッド
		String sqlItem = "select * from price_history join product_detail "
				+ "on price_history.product_detail_id = product_detail.product_detail_id "
				+ "join product_group on product_detail.product_group_id = product_group.product_group_id"
				+ "where ? = ?";
		
		int m = menu;
		String w = what;
		
		try(
				Connection co = DriverManager.getConnection(url,user_name,password);
				PreparedStatement ps = co.prepareStatement(sqlItem);
				){
			
			switch(m) {
			case 1:ps.setString(1, "product_detail.product_detail_id");
			case 2:ps.setString(1, "price");
			case 3:ps.setString(1, "product_detail_name");
			case 4:ps.setString(1, "product_group");
			}//メニューで選択された値によってSQL文の1つ目の?を変更
			
			ps.setString(2,w);//2つ目の?を入力された値に
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("product_detail_id");
				String group = rs.getString("product_group_name");
				String name = rs.getString("product_detail_name");
				int price = rs.getInt("price");
				
				System.out.printf("%s : [%s]    %s \n",id,group,name);
			}//while
		}//try
		catch(Exception e) {
			System.out.println(errorMessage));
		}
	}//selectItem
	
	
	public static String selectCustomerGroup() {//店舗形態を表示するメソッド
		String sqlCustomerGroup = "";
	}//selectCustomerGroup
	
	
	public static String selectItemGroup() {//商品分類(食べ物なら「野菜」とか)を表示するメソッド
		String sqlItemGroup = "";
	}//selectItemGroup
	
	
	public static String selectMember() {//従業員を表示するメソッド
		String sqlMember = "select * from 〇〇 ";
	}//selectMember
	
}//Select
