package nakamori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import shimizu.Select;
import yoshida.Text;

public class EditCustomer {
	
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
	  	  System.out.println( "番号を入力：" ) ;
	  	
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
  		
  		int gyousuu = Select.selectCustomer( 4, null ) +1 ;
  		System.out.println("\n～以上が現在の顧客データです～\n");
  		Scanner sc = new Scanner(System.in) ;
  	
        System.out.println( "顧客情報を入力してください。\n" );
        
        System.out.println( "店舗形態番号(下記参照)" );
  		Select.selectCustomerGroup() ; // 店舗形態の表示
  		System.out.print("：");
  		String group = sc.nextLine() ;
  		
  		System.out.print( "\n店舗名：" );
  		String name = sc.nextLine() ;
  		
  		System.out.print( "\n店舗のメールアドレス：" );
  		String mail = sc.nextLine() ;
  		
  		System.out.print( "\n店舗の電話番号：" );
  		String number = sc.nextLine() ;
  		
  		System.out.print( "\n店舗の所在地(都道府県)：" );
  		String address = sc.nextLine() ;
  		
  		String sql = "INSERT INTO customer (customer_id, customer_name, mail, tel, address, customer_group_id) VALUES ( ?, ?, ?, ?, ?, ?);" ;
  		
  		try ( 
  	  		Connection con = DriverManager.getConnection( url , user_name , password ) ;
  	  		PreparedStatement ps = con.prepareStatement( sql ) ;
  	  		) {
  	  		
  			
  	  		//入力値のセット(？マークの部分の差し替え)
  	  		ps.setString( 1, String.format("%04d", gyousuu) );
  	  		ps.setString( 2, name );
  	  		ps.setString( 3, mail );
  	  	    ps.setString( 4,number );
	  		ps.setString( 5, address );
	  		ps.setString( 6, group );
  	  				
  	  		//SQL文の送信。
  	  		int result = ps.executeUpdate( );
  	  				
  	  		if ( result == 1 ) {
  	  			System.out.println( "\n1件の書き込みが完了しました。" );
  	  		}
  	  		else{
  	  			System.out.println( "\n書き込みに失敗しました。" );
  	  		}
  	  				
  	  	}
  	  	catch ( Exception e ) {
  	     	System.out.println(Text.tryCatch);
  	  	    e.printStackTrace();
  	  	}
  	  	finally {
  	  		System.out.println( );
  	  	}
  		
  	}
  	
  	
    //-------------------------------------------
  	// 顧客情報を更新するメソッド
  	public static void update() {
		
		Scanner sc = new Scanner(System.in) ;
		
		Select.selectCustomer(4, null); 
		System.out.println("\n～以上が現在の顧客データです～\n");
		
		System.out.print( "更新する顧客番号を入力してください。：" );
		String code = sc.nextLine();
		
		System.out.println( "\n更新したい項目を選んでください。" );
		
		System.out.println( "1: 店舗形態" );
		System.out.println( "2: 店舗名" );
		System.out.println( "3: 店舗のメールアドレス" );
		System.out.println( "4: 店舗の電話番号" );
		System.out.println( "5: 店舗の所在地(都道府県)" );
		System.out.print( "番号を入力：" );
		int choice = Integer.parseInt(sc.nextLine()) ;
		
		String columnName = null ;
		String newValue = null ;
		
		if ( choice == 1 ) {
			System.out.println( "" );
			Select.selectCustomerGroup() ; // 店舗形態の表示
			columnName = "customer_group_id" ;
			System.out.println("\n上記を参照して新しい店舗形態番号を入力してください。：");
			newValue = sc.nextLine();
		}
		else if ( choice == 2 ) {
			columnName = "customer_name" ;
			System.out.print( "\n新しい店舗名を入力してください。：" );
			newValue = sc.nextLine();
		}
		else if ( choice == 3 ) {
			columnName = "mail" ;
			System.out.print( "\n新しい店舗のメールアドレスを入力してください。：" );
			newValue = sc.nextLine();
		}
		else if ( choice == 4 ) {
			columnName = "tel" ;
			System.out.print( "\n新しい店舗の電話番号を入力してください。：" );
			newValue = sc.nextLine();
		}
		else if ( choice == 5 ) {
			columnName = "address" ;
			System.out.print( "\n新しい店舗の所在地(都道府県)を入力してください。：" );
			newValue = sc.nextLine();
		}
		else {
			System.out.println( "\n無効な番号です。" );
		}
			
		String sql = "UPDATE customer SET `" + columnName + "` = ? WHERE customer_id = ?;" ;
		
		try ( 
			Connection con = DriverManager.getConnection( url , user_name , password ) ;
			PreparedStatement ps = con.prepareStatement( sql ) ;
			) {
			
			ps.setString(1, newValue) ;
			ps.setString(2, code) ;
			
			//SQL文の送信。
			int result = ps.executeUpdate( );
				
			if ( result == 1 ) {
				System.out.println( "\n1件の書き込みが完了しました。" );
			}
			else{
				System.out.println( "\n書き込みに失敗しました。" );
			}
				
		}
		catch ( Exception e ) {
			System.out.println(Text.tryCatch);
			e.printStackTrace();
		}
		finally {
			System.out.println( );
		}
		
	}
  	
  	

    //-------------------------------------------
  	// 顧客情報を削除するメソッド
  	public static void delete() {
	Scanner sc = new Scanner(System.in) ;
	
	Select.selectCustomer(4, null);
	System.out.println("\n～以上が現在の顧客データです～\n");
	
	System.out.print( "削除する番号を入力してください。：" );
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
				System.out.println( "\n削除しました。" );
			}
			else{
				System.out.println( "\n失敗しました。" );
			}
		}
		catch ( Exception e ) {
			System.out.println(Text.tryCatch);
		}
		finally {
			System.out.println( );
		}

    }
  
  }
  	

