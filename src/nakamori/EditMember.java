package nakamori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import nanamori.Menu_employee;
import nanamori.Menu_master;
import shimizu.Select;
import yoshida.Judge_pass_id;
import yoshida.Main;
import yoshida.Text;

public class EditMember {
	
	//SQLのデータベース接続
	static String url = Text.url ;
	static String user_name = Text.user_name ;
	static String password = Text.password ;
		
	public int menuEdit ; //どの処理を行うかを選択する変数
	
    Scanner sc = new Scanner(System.in);
	
    public void startMenu( ) {
    	while(true) {
  	  System.out.println( "編集メニューを選択してください" ) ;
  	  System.out.println( "1: 追加" ) ;
  	  System.out.println( "2: 更新" ) ;
  	  System.out.println( "3: 削除" ) ;
  	  System.out.println( "番号を入力" ) ;
  	
        menuEdit = Integer.parseInt( sc.nextLine() ) ;
      
        if ( menuEdit == 1 ) {
      	  insert() ;
      	  break;
        }
        else if ( menuEdit == 2 ) {
      	  update() ;
      	  break;
        }
        else if ( menuEdit == 3 ) {
      	  delete() ;
      	  break;
        } 
        else {
      	  System.out.println("\n【エラー：項目以外の内容の入力】");
  			System.out.println("1〜3の番号を入力してください。\n");
        }
  	}//while

  		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
  		System.out.println("移動したい画面の番号をお選びください\n");
  		System.out.println("0.ホーム画面\n" + "1.メニュー画面");
  		Scanner sc = new Scanner(System.in); //顧客
  		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
  		System.out.print("番号： ");
  		int move = sc.nextInt();
  		
  		if(move == 0) {
  			Main.main(null); //ホーム画面移動
  		}else if(move == 1) {
  			Judge_pass_id j = new Judge_pass_id();
  		    int pass = j.judge();
  			if ( pass == 1) {
  				Menu_master ma = new Menu_master(); //店長のメニュー画面
  				ma.menu_master();
            } else {
            	Menu_employee em = new Menu_employee(); //従業員のメニュー画面
            	em.menu_employee();
            }
  		}
  	} 
    
    //-------------------------------------------
  	// 従業員を追加するメソッド
  	public static void insert ( ) {
  		
  		Scanner sc = new Scanner(System.in) ;
  		int gyousuu = Select.selectMember( )+1 ;
  		System.out.println("\n～以上が現在の従業員データです～\n");
  		System.out.println( "従業員情報を入力してください。" );
  		
  		System.out.print( "従業員名：" );
  		String eName = sc.nextLine() ;
  		
  		System.out.print( "\n従業員ID：" );
  		String eID = sc.nextLine() ;
  		
  		String ePass = null ;
  		boolean passCheck = false ;
  		
  		while( !passCheck ) {
  		
  		System.out.print( "\n従業員パスワード：" );
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
  				System.out.println( "\n1件の書き込みが完了しました。" );
  			}
  			else{
  				System.out.println( "\n書き込みに失敗しました。" );
  			}
  				
  		}
  		catch ( Exception e ) {
  			System.out.println(Text.tryCatch );
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
		
		System.out.println("\n～以上が現在の従業員データです～\n");
		
		String code =null;
		while(true) {
		System.out.print( "更新する番号を入力してください。：" );
		code = sc.nextLine();
		
		if(Select.object_id_judge(4,code)) {
			break;
		}
		else {
			System.out.println("\n【エラー：存在しないデータ番号の入力】");
			System.out.println("データ内に存在する番号を選んでください\n");
			System.out.println("入力に戻ります");
		}
		}//while
		
		String columnName = null ;
		String newValue = null ;
		
		while(true) {
		System.out.println( "\n更新したい項目を選んでください。" );
		
		System.out.println( "1: 従業員名" );
		System.out.println( "2: 従業員ID" );
		System.out.println( "3: 従業員パスワード" );
		System.out.print( "番号を入力：" );
		int choice = Integer.parseInt(sc.nextLine()) ;
		
		if ( choice == 1 ) {
			columnName = "name" ;
			System.out.print( "\n新しい従業員名を入力してください。：" );
			newValue = sc.nextLine();
			break;
		}
		else if ( choice == 2 ) {
			columnName = "eID" ;
			System.out.print( "\n新しい従業員IDを入力してください。：" );
			newValue = sc.nextLine();
			break;
		}
		else if ( choice == 3 ) {
			columnName = "ePass" ;
			boolean passCheck = false ;
			
	  		while( !passCheck ) {
			System.out.print( "\n新しい従業員パスワードを入力してください。：" );
			newValue = sc.nextLine();
			passCheck = passrules( newValue );
	  		}
	  		break;
		}
		else {
			System.out.println("\n【エラー：項目以外の内容の入力】");
			System.out.println("1〜3の番号を入力してください。");
		}
	}//while
			
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
				System.out.println( "\n1件の書き込みが完了しました。" );
			}
			else{
				System.out.println( "\n書き込みに失敗しました。" );
			}
				
		}
		catch ( Exception e ) {
			System.out.println( Text.tryCatch);
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
  		System.out.println("\n～以上が現在の従業員データです～\n");
  		
  		String code =null;
		while(true) {
		System.out.print( "削除する番号を入力してください。：" );
		code = sc.nextLine();
		
		if(Select.object_id_judge(4,code)) {
			break;
		}
		else {
			System.out.println("\n【エラー：存在しないデータ番号の入力】");
			System.out.println("データ内に存在する番号を選んでください\n");
			System.out.println("入力に戻ります");
		}
		}//while
		
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
  				System.out.println( "\n削除しました。" );
  			}
  			else{
  				System.out.println( "\n失敗しました。" );
  			}
  		}
  		catch ( Exception e ) {
  			System.out.println( Text.tryCatch );
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
  

