package nakamori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import yoshida.Text;
import shimizu.Select;

public class EditCustomer {
	
	//SQLのデータベース接続
	String url = Text.url ;
	String user_name = Text.user_name ;
	String password = Text.password ;
			
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
  	// 顧客情報を追加するメソッド
  	public static void insert ( ) {
  		
  		Scanner sc = new Scanner(System.in) ;
  	
        System.out.println( "顧客情報を入力してください。" );
        
        int gyousuu = Select.selectCustomer( 5, null ) +1 ;
  		
  		System.out.println( "店舗形態" );
  		String group = sc.nextLine() ;
  		
  		System.out.println( "店舗名" );
  		String name = sc.nextLine() ;
  		
  		System.out.println( "店舗のメールアドレス" );
  		String mail = sc.nextLine() ;
  		
  		System.out.println( "店舗の住所" );
  		String address = sc.nextLine() ;
  		
  		System.out.println( "店舗の電話番号" );
  		String number = sc.nextLine() ;
  		
  		String sql = "INSERT INTO customer VALUES ( ?, ?, ?, ?, ?, ?);" ;
  		
  		try ( 
  	  		Connection con = DriverManager.getConnection( url , user_name , password ) ;
  	  		PreparedStatement ps = con.prepareStatement( sql ) ;
  	  		) {
  	  				
  	  		//入力値のセット(？マークの部分の差し替え)
  	  		ps.setInt( 1, gyousuu );
  	  		ps.setString( 2, group );
  	  		ps.setString( 3, name );
  	  		ps.setString( 4, mail );
  	  	    ps.setString( 5, address );
	  		ps.setString( 6, number );
  	  				
  	  		//SQL文の送信。
  	  		int result = ps.executeUpdate( );
  	  				
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
  	// 顧客情報を更新するメソッド
  	public static void update() {
		
		Scanner sc = new Scanner(System.in) ;
		
		System.out.println( "更新する顧客番号を入力してください。" );
		String code = sc.nextLine();
		
		System.out.println( "更新したい項目を選んでください。" );
		
		System.out.println( "1: 店舗形態" );
		System.out.println( "2: 店舗名" );
		System.out.println( "3: 店舗のメールアドレス" );
		System.out.println( "4: 店舗の住所" );
		System.out.println( "5: 店舗の電話番号" );
		System.out.println( "番号を入力" );
		int choice = Integer.parseInt(sc.nextLine()) ;
		
		String columnName = null ;
		String newValue = null ;
		int newIntValue = 0 ;
		boolean Integer = false ;
		
		if ( choice == 1 ) {
			columnName = "group" ;
			System.out.println( "新しい店舗形態を入力してください。" );
			newIntValue = sc.nextInt();
			Integer = true ;
		}
		else if ( choice == 2 ) {
			columnName = "name" ;
			System.out.println( "新しい店舗名を入力してください。" );
			newValue = sc.nextLine();
		}
		else if ( choice == 3 ) {
			columnName = "mail" ;
			System.out.println( "新しい店舗のメールアドレスを入力してください。" );
			newValue = sc.nextLine();
		}
		else if ( choice == 4 ) {
			columnName = "address" ;
			System.out.println( "新しい店舗の住所を入力してください。" );
			newValue = sc.nextLine();
		}
		else if ( choice == 5 ) {
			columnName = "number" ;
			System.out.println( "新しい店舗の電話番号を入力してください。" );
			newValue = sc.nextLine();
		}
		else {
			System.out.println( "無効な番号です。" );
		}
			
		String sql = "UPDATE customer SET " + columnName + " = ? WHERE customer_no = ?;" ;
		
		try ( 
			Connection con = DriverManager.getConnection( url , user_name , password ) ;
			PreparedStatement ps = con.prepareStatement( sql ) ;
			) {
			
			if ( Integer ) {
				ps.setInt(1, newIntValue) ;
			}
			else {
				ps.setString(1, newValue) ;
			}
			
			ps.setString(2, code) ;
			
			//SQL文の送信。
			int result = ps.executeUpdate( );
				
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
  	// 顧客情報を削除するメソッド
  	public static void delete() {
	Scanner sc = new Scanner(System.in) ;
		
	System.out.println( "削除するIDを入力してください。" );
	String code = sc.nextLine();
		
	String sql = "DELETE FROM customer WHERE customer_id = ?;" ;
		
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
  	
}
