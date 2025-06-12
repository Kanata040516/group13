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
		String sqlReceipt = "select * from receipt join";
		
		int m = menu;
		String w  = what;
		
		
	}//selectReceipt
	
	
	public static String selectCustomer(int menu, String what) {//顧客情報を表示するメソッド
		String sqlCustomer = "select * from customer join customer_group "
				+ "on customer.customer_group_id = customer_group.customer_group_id "
				+ "where ? = ?";
		
		int m = menu;
		String w  = what;
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlCustomer ) ;
			)
			{
			
			switch(m) {
			case 1:ps.setString(1, "customer_name");break;	
			case 2:ps.setString(1, "address");break;
			case 3:ps.setString(1, "customer_group");break;
			}//switch
				
			ps.setString(2, w);
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("customer_id");
				String group = rs.getString("customer_group_name");
				String name = rs.getString("customer_name");
				
				System.out.printf("%s: [%s]  %s店",id,group,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(errorMessage);
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
			
			switch(m) {
			case 1:ps.setString(1, "group_detail_id");break;
			case 2:ps.setString(1, "price");break;
			case 3:ps.setString(1, "group_detail_name");break;
			case 4:ps.setString(1, "product_group");break;
			}//switch
				
			ps.setString(2, w);
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("product_detail_id");
				String group = rs.getString("product_group_name");
				String name = rs.getString("product_detail_name");
				int price = rs.getInt("price");
				
				System.out.printf("%s: [%s]  %s  %d円",id,group,name,price);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(errorMessage);
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
				
				String id = rs.getString("customer_group_id");
				String name = rs.getString("customer_group_name");
				
				System.out.printf("%s:   %s\n",id,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(errorMessage);
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
				
				String id = rs.getString("product_group_id");
				String name = rs.getString("product_group_name");
				
				System.out.printf("%s:   %s\n",id,name);
			}//while
			
			}
			
			catch(Exception e){
				System.out.println(errorMessage);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
	}//selectItemGroup
	
	
	public static String selectMember() {//従業員を表示するメソッド
		String sqlMember = "select * from member";
		
		try(
				Connection con = DriverManager.getConnection( url , user_name , password ) ;//finallyがなくても操作できる
				PreparedStatement ps = con.prepareStatement( sqlMember ) ;
			)
			{
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("member_no");
				String name = rs.getString("eName");
				String eID = rs.getString("eID");
				String ePass = rs.getString("ePass");
				
				System.out.println("-------------------------------------------");
				System.out.printf("%s:   %s\nID:%s   Pass:%s\n",id,name,eID,ePass);
			}//while
			System.out.println("-------------------------------------------");
			}
			
			catch(Exception e){
				System.out.println(errorMessage);
			}
		    
		    finally {
		    	System.out.println("select〇");//debug
		    }
	}//selectMember
	
}//Select
