package nakamori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import shimizu.Select;
import yoshida.Text;

public class EditReceipt {
	
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
	// 注文を追加するメソッド
	public static void insert ( ) {
		
		Scanner sc = new Scanner(System.in) ;
		
		System.out.println( "注文内容を入力してください。" );
		
		int gyousuu = Select.selectReceipt( 6, null )[0] +1 ;
		
		System.out.println( "顧客名" );
		String customer = sc.nextLine() ;
		
		System.out.println( "商品名" );
		String item = sc.nextLine() ;
		
		System.out.println( "数量" ) ;
		int amount = Integer.parseInt(sc.nextLine()) ;
		
		System.out.println( "日付 (YYYY-MM-DD)" );
		String date = sc.nextLine() ;
		
		System.out.println( "備考" );
		String remark = sc.nextLine() ;
		
		String sql = "INSERT INTO receipt VALUES ( ?, ?, ?, ?, ?, ? );" ;
		
		try ( 
			Connection con = DriverManager.getConnection( url , user_name , password ) ;
			PreparedStatement ps = con.prepareStatement( sql ) ;
			) {
			
			//入力値のセット(？マークの部分の差し替え)
			ps.setInt( 1, gyousuu );
			ps.setString( 2, customer );
			ps.setString( 3, item );
			ps.setInt( 4, amount );
			ps.setString( 5, date );
			ps.setString( 6, remark );
			
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
	// 注文を更新するメソッド
	public static void update() {
		
		Scanner sc = new Scanner(System.in) ;
		
		System.out.println( "更新する注文IDを入力してください。" );
		String code = sc.nextLine();
		
		System.out.println( "更新したい項目を選んでください。" );
		System.out.println( "1: 顧客名" );
		System.out.println( "2: 商品名" );
		System.out.println( "3: 数量" );
		System.out.println( "4: 日付" );
		System.out.println( "5: 備考" );
		System.out.println( "番号を入力" );
		int choice = Integer.parseInt(sc.nextLine()) ;
		
		String columnName = null ;
		String newValue = null ;
		int newIntValue = 0 ;
		boolean Integer = false ;
		
		if ( choice == 1 ) {
			columnName = "customer" ;
			System.out.println( "新しい顧客名を入力してください。" );
			newValue = sc.nextLine();
		}
		else if ( choice == 2 ) {
			columnName = "item" ;
			System.out.println( "新しい商品名を入力してください。" );
			newValue = sc.nextLine();
		}
		else if ( choice == 3 ) {
			columnName = "amount" ;
			System.out.println( "新しい数量を入力してください。" );
			newIntValue = sc.nextInt();
			Integer = true ;
		}
		else if ( choice == 4 ) {
			columnName = "date" ;
			System.out.println( "新しい日付(YYYY-MM-DD)を入力してください。" );
			newValue = sc.nextLine();
		}
		else {
			System.out.println( "無効な番号です。" );
		}
		
		String sql = "UPDATE receipt SET " + columnName + " = ? WHERE main_id = ?;" ;
		
		try ( 
			Connection con = DriverManager.getConnection( url , user_name , password ) ;
			PreparedStatement ps = con.prepareStatement( sql ) ;
			) {
				
			//入力値のセット(？マークの部分の差し替え)
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
	// 注文を削除するメソッド
	public static void delete() {
		Scanner sc = new Scanner(System.in) ;
		
		System.out.println( "削除するIDを入力してください。" );
		String code = sc.nextLine();
		
		String sql = "DELETE FROM receipt WHERE main_id = ?;" ;
		
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



