package nakamori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import yoshida.Text;
import shimizu;

public class EditCustomer {
	
	//SQLのデータベース接続
	String url = Text.url ;
	String user_name = Text.user_name ;
	String password = Text.password ;
			
	public int menuEdit ; //どの処理を行うかを選択する変数
		
	Scanner sc = new Scanner(System.in);
		
	System.out.println( "編集メニューを選択してください" ) ;
	System.out.println( "1: 追加" ) ;
	System.out.println( "2: 更新" ) ;
	System.out.println( "3: 削除" ) ;
	System.out.println( "番号を入力" ) ;
		
	menuEdit = Integer.parseInt( sc.nextLine() ) ;
	    
	if ( menuEdit.equals( 1 ) ) {
	    insert() ;
	}
	else if ( menuEdit.equals( 2 ) ) {
	    update() ;
	}
	else if ( menuEdit.equals( 3 ) ) {
	    delete() ;
	} 
	else {
	    System.out.println( "無効なメニュー番号です。" );
	 }
	
	
	//-------------------------------------------
  	// 顧客を追加するメソッド
  	public static void insert ( ) {
  		
  		Scanner sc = new Scanner(System.in) ;
  	
        System.out.println( "顧客情報を入力してください。" );
        
        int gyousuu = Select.selectCustomer(  );
  		
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
  		
  		String sql = "INSERT INTO member VALUES ( ?, ?, ?, ?, ?, ?);" ;
  		
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
  	
  	

}
