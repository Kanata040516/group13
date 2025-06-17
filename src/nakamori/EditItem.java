package nakamori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import shimizu.Select;
import yoshida.Text;

public class EditItem {
	
	//SQLのデータベース接続
	static String url = Text.url ;
	static String user_name = Text.user_name ;
	static String password = Text.password ;
				
	public int menuEdit ; //どの処理を行うかを選択する変数
			
	Scanner sc = new Scanner(System.in);
			
	public void startMenu( ) {
	  	  System.out.println( "編集メニューを選択してください" ) ;
	  	  System.out.println( "1: 追加" ) ;
	  	  System.out.println( "2: 更新" ) ;
	  	  System.out.println( "3: 削除" ) ;
	  	  System.out.println( "番号を入力" ) ;
	  	
	        menuEdit = Integer.parseInt( sc.nextLine() ) ;
	      
	        if ( menuEdit == 1 ) {
	      	  insert() ;
	        }
	        else if ( menuEdit == 2 ) {
	      	  update() ;
	        }
	        else if ( menuEdit == 3 ) {
	      	  delete() ;
	        } 
	        else {
	      	  System.out.println( "無効なメニュー番号です。" );
	        }
	      
	  	} 

   
//-------------------------------------------
	// 商品情報を追加するメソッド
	public static void insert ( ) {
		
		Scanner sc = new Scanner(System.in) ;
	
    System.out.println( "商品情報を入力してください。" );
    
    int gyousuu = Select.selectItem( 4, null ) +1 ;
		
		System.out.println( "商品の価格" );
		int price = Integer.parseInt(sc.nextLine()) ;
		
		System.out.println( "商品名" );
		String name = sc.nextLine() ;
		
		System.out.println( "商品の分類" );
		String group = sc.nextLine() ;
		
		// IDの生成方法（例としてシンプルに連番を使っているならSelectメソッドを活用）
	    int newProductDetailId = Select.selectItem( /* product_detailのID数を取得するメソッド */ ) + 1;
	    int newPriceHistoryId = Select.selectItem( /* price_historyのID数を取得するメソッド */ ) + 1;
		
		String sql1 = "INSERT INTO price_history VALUES (?, ?, ?, CURRENT_DATE);";
		String sql2 = "INSERT INTO product_detail VALUES ( ?, ?, ?, ?);" ;
		
		try ( 
	  		Connection con = DriverManager.getConnection( url , user_name , password ) ;
	  		PreparedStatement ps1 = con.prepareStatement( sql1 ) ;
			PreparedStatement ps2 = con.prepareStatement( sql2 ) ;
	  		) {
	  		
			con.setAutoCommit(false); // トランザクション開始
			
			// price_historyにINSERT
			ps1.setString(1, String.format("%04d", newPriceHistoryId));
	        ps1.setString(2, String.format("%04d", newProductDetailId)); // product_detail_idを使う
	        ps1.setInt(3, price);
	        int result1 = ps1.executeUpdate();
	        
	        // product_detailにINSERT
	        ps2.setString(1, String.format("%04d", newProductDetailId));
	        ps2.setString(2, group);
	        ps2.setString(3, name);
	        int result2 = ps2.executeUpdate();

	  				
	  		if ( result1 == 1 && result2 == 1 ) {
	  			con.commit(); // 成功したらコミット
	  			System.out.println( "1件の書き込みが完了しました。" );
	  		}
	  		else{
	  			con.rollback(); // どちらか失敗したらロールバック
	  			System.out.println( "書き込みに失敗しました。" );
	  		}
	  				
	  	}
	  	catch ( Exception e ) {
	  		System.out.println( "エラーが発生しました。" );
	  	}
	  	finally {
	  		System.out.println( );
	  	}
		
	}
	
	
    //-------------------------------------------
	// 商品情報を更新するメソッド
	public static void update() {
	
	Scanner sc = new Scanner(System.in) ;
	
	System.out.println( "更新する商品番号を入力してください。" );
	String code = sc.nextLine();
	
	System.out.println( "更新したい項目を選んでください。" );
	
	System.out.println( "1: 商品の価格" );
	System.out.println( "2: 商品名" );
	System.out.println( "3: 商品の分類" );
	System.out.println( "番号を入力" );
	int choice = Integer.parseInt(sc.nextLine()) ;
	
	String columnName = null ;
	String newValue = null ;
	int newIntValue = 0 ;
	boolean isInteger = false ;
	boolean isPriceUpdate = false ;
	
	if ( choice == 1 ) {
		columnName = "price" ;
		System.out.println( "新しい商品の価格を入力してください。" );
		newIntValue = Integer.parseInt(sc.nextLine()) ;
		isInteger = true ;
		isPriceUpdate = true ;
	}
	else if ( choice == 2 ) {
		columnName = "name" ;
		System.out.println( "新しい商品名を入力してください。" );
		newValue = sc.nextLine();
	}
	else if ( choice == 3 ) {
		columnName = "group" ;
		System.out.println( "新しい商品の分類を入力してください。" );
		newIntValue = Integer.parseInt(sc.nextLine()) ;
		isInteger = true ;
	}
	else {
		System.out.println( "無効な番号です。" );
	}
		
	String sql = isPriceUpdate ?
			"UPDATE price_history SET " + columnName + " = ? WHERE price_no = ?;" :
	        "UPDATE product_detail SET " + columnName + " = ? WHERE product_no = ?;" ;
	
	try ( 
		Connection con = DriverManager.getConnection( url , user_name , password ) ;
		PreparedStatement ps = con.prepareStatement( sql ) ;
		) {
		
		if (isInteger) {
	            ps.setInt(1, newIntValue);
	        } else {
	            ps.setString(1, newValue);
	        }
	        ps.setString(2, code);
	      
	    int result = ps.executeUpdate();
			
		if ( result == 1 ) {
			System.out.println( "1件の書き込みが完了しました。" );
		}
		else{
			System.out.println( "書き込みに失敗しました。" );
		}
			
	}
	catch ( Exception e ) {
		System.out.println( "エラーが発生しました。" );
	}
	finally {
		System.out.println( );
	}
	
}
	

//-------------------------------------------
	// 商品情報を削除するメソッド
	public static void delete() {
    Scanner sc = new Scanner(System.in) ;
    
    System.out.println( "削除するIDを入力してください。" );
    String code = sc.nextLine();
	
    String sql = "DELETE FROM product_detail WHERE product_id = ?;" ;
	
	try ( 
		Connection con = DriverManager.getConnection( url , user_name , password ) ;
		PreparedStatement ps = con.prepareStatement( sql ) ;
		) {
		
		//入力値のセット(？マークの部分の差し替え)
		ps.setString( 1, code ) ;
		
		//SQL文の送信。
		int result = ps.executeUpdate( );
			
		if ( result == 1 ) {
			System.out.println( "削除しました。" );
		}
		else{
			System.out.println( "失敗しました。" );
		}
	}
	catch ( Exception e ) {
		System.out.println( "エラーが発生しました。" );
	}
	finally {
		System.out.println( );
	}

   }

  }


