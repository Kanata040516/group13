package nakamori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import shimizu.Select;
import yoshida.Text;

public class EditMember {
	
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
  	// 従業員を追加するメソッド
  	public static void insert ( ) {
  		
  		Scanner sc = new Scanner(System.in) ;
  		
  		System.out.println( "従業員情報を入力してください。" );
  		
  		int gyousuu = Select.selectMember( )+1 ;
  		
  		System.out.println( "従業員名" );
  		String eName = sc.nextLine() ;
  		
  		System.out.println( "従業員ID" );
  		String eID = sc.nextLine() ;
  		
  		String ePass = null ;
  		boolean passCheck = false ;
  		
  		while( !passCheck ) {
  		
  		System.out.println( "従業員パスワード" );
  		ePass = sc.nextLine() ;
  		
  		passCheck =passrules( ePass );
  		
  		}
  		
  		String sql = "INSERT INTO member VALUES ( ?, ?, ?, ?);" ;
  		
  		try ( 
  			Connection con = DriverManager.getConnection( url , user_name , password ) ;
  			PreparedStatement ps = con.prepareStatement( sql ) ;
  			) {
  				
  			//入力値のセット(？マークの部分の差し替え)
  			ps.setString(1, String.format("%04d", gyousuu)); // 4桁のゼロ埋め

  			ps.setString( 2, eName );
  			ps.setString( 3, ePass );
  			ps.setString( 4, eID );
  				
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
  			e.printStackTrace();
  		}
  		finally {
  			System.out.println( );
  		}
  		
  	}
  	
    //-------------------------------------------
  	// 従業員を更新するメソッド
    public static void update() {
		
    	Select.selectMember( );
		Scanner sc = new Scanner(System.in) ;
		
		System.out.println( "更新する従業員番号を入力してください。" );
		String code = sc.nextLine();
		
		System.out.println( "更新したい項目を選んでください。" );
		
		System.out.println( "1: 従業員名" );
		System.out.println( "2: 従業員ID" );
		System.out.println( "3: 従業員パスワード" );
		System.out.println( "番号を入力" );
		int choice = Integer.parseInt(sc.nextLine()) ;
		
		String columnName = null ;
		String newValue = null ;
		
		if ( choice == 1 ) {
			columnName = "eName" ;
			System.out.println( "新しい従業員名を入力してください。" );
			newValue = sc.nextLine();
		}
		else if ( choice == 2 ) {
			columnName = "eID" ;
			System.out.println( "新しい従業員IDを入力してください。" );
			newValue = sc.nextLine();
		}
		else if ( choice == 3 ) {
			columnName = "ePass" ;
			boolean passCheck = false ;
			
	  		while( !passCheck ) {
			System.out.println( "新しい従業員パスワードを入力してください。" );
			newValue = sc.nextLine();
			passCheck = passrules( newValue );
	  		}
		}
		else {
			System.out.println( "無効な番号です。" );
		}
			
		String sql = "UPDATE member SET " + columnName + " = ? WHERE member_no = ?;" ;
		
		try ( 
			Connection con = DriverManager.getConnection( url , user_name , password ) ;
			PreparedStatement ps = con.prepareStatement( sql ) ;
			) {
			
			ps.setString(1, newValue) ;
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
  	// 従業員情報を削除するメソッド
  	public static void delete() {
  		
  		Select.selectMember( );
  		Scanner sc = new Scanner(System.in) ;
  		
  		System.out.println( "削除する従業員番号を入力してください。" );
  		String code = sc.nextLine();
  		
  		String sql = "DELETE FROM member WHERE member_no = ?;" ;
  		
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
    public static boolean passrules(String pass) {
        // ① 文字数（例：8文字以上）
        if (pass.length() < 8) {
            System.out.println("パスワードは8文字以上である必要があります。");
            return false;
        }


        // ② 大文字を含むか
        if (!pass.matches(".*[A-Z].*")) {
            System.out.println("パスワードには大文字を1文字以上含めてください。");
            return false;
        }

        // ③ 小文字を含む
        if (!pass.matches(".*[a-z].*")) {
            System.out.println("パスワードには小文字を1文字以上含めてください。");
            return false;
        }

        // ④ 数字を含む
        if (!pass.matches(".*[0-9].*")) {
            System.out.println("パスワードには数字を1文字以上含めてください。");
            return false;
        }

        // ⑤ 特殊記号を含むか（!@#$%^&*()_+ などを対象に）
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            System.out.println("パスワードには特殊記号を1文字以上含めてください。");
            return false;
        }

 
        // ② 大文字を含むか
        if (!pass.matches(".*[A-Z].*")) {
            System.out.println("パスワードには大文字を1文字以上含めてください。");
            return false;
        }
 
        // ③ 小文字を含む
        if (!pass.matches(".*[a-z].*")) {
            System.out.println("パスワードには小文字を1文字以上含めてください。");
            return false;
        }
 
        // ④ 数字を含む
        if (!pass.matches(".*[0-9].*")) {
            System.out.println("パスワードには数字を1文字以上含めてください。");
            return false;
        }
 
        // ⑤ 特殊記号を含むか（!@#$%^&*()_+ などを対象に）
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            System.out.println("パスワードには特殊記号を1文字以上含めてください。");
            return false;
        }
 
        // すべての条件を満たす
        return true;
    }
  	
  }
  

