package nanamori;

import java.util.Scanner;

public class Menu_master extends Menu_employee{


	public static void menu_master() {//管理者画面での処理。
		
		Scanner check = new Scanner(System.in);

		mainmenu = check.nextInt();//入力した値をmainmenuに入れる
		
		if(mainmenu == 0) {//ホーム画面に移動する。
			System.out.println("初期画面に移動します。");
			//Main.home;
		}
		
		if(mainmenu == 1) {//注文編集画面に移動する。
			System.out.println("注文編集画面に移動します。");
			//Editreceipt.???;//???の部分はどのメソッドを実行するか不明.
		}
		
		if(mainmenu == 2) {//商品編集画面に移動する。
			System.out.println("商品編集画面に移動します。");
			//EditItem.???;//???の部分はどのメソッドを実行するか不明
		}
		
		if(mainmenu == 3) {//顧客編集画面に移動する。
			System.out.println("顧客編集画面に移動します。");
			//EditCustomer.???;//???の部分はどのメソッドを実行するか不明
		}
		
		if(mainmenu == 4) {//従業員編集画面に移動する。
			System.out.println("従業員編集画面に移動します。");
			//EditMember.???;//???の部分はどのメソッドを実行するか不明
		}
		
		if(mainmenu == 5) {//管理者閲覧画面に移動する。
			System.out.println("管理者閲覧画面に移動します。");
			menu_select();
		}
		
		
	}
	
	public static void menu_select() {//マスター視点のmenu_select処理
		
		if(mainmenu == 6) {//管理者編集画面に移行する。
			System.out.println("管理者編集画面に移動します。");
			menu_master();
		}
	}
	
	
}


