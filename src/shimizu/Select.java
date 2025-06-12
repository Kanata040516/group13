package shimizu;

public class Select {
	String url = System.url;
	String user_name = System.user_name;
	String password = System.password;
	
	
	public static String selectReceipt(int menu, String what) {//注文履歴を表示するメソッド
		String sqlReceipt = "";
		
	}//selectReceipt
	
	
	public static String selectCustomer(int menu, String what) {//顧客情報を表示するメソッド
		String sqlCustomer = "";
	}//selectCustomer
	
	
	public static String selectItem(int menu, String what) {//商品情報を表示するメソッド
		String sqlItem = "";
	}//selectItem
	
	
	public static String selectCustomerGroup() {//店舗形態を表示するメソッド
		String sqlCustomerGroup = "";
	}//selectCustomerGroup
	
	
	public static String selectItemGroup() {//商品分類(食べ物なら「野菜」とか)を表示するメソッド
		String sqlItemGroup = "";
	}//selectItemGroup
	
	
	public static String selectMember() {//従業員を表示するメソッド
		String sqlMember = "";
	}//selectMember
	
}//Select
