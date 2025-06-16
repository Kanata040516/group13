package nanamori;

import java.util.Scanner;

import nakamori.EditCustomer;
import nakamori.EditItem;
import nakamori.EditMember;
import nakamori.EditReceipt;
import yoshida.Main;

public class Menu_master extends Menu_employee{

	

	public  void menu_employee() {//マスター視点のmenu_select処理
		super.menu_employee();
		
		
		if(mainmenu == 6) {//管理者編集画面に移行する。
			System.out.println("管理者編集画面に移動します。");
			menu_master();
		}
	}
	
	
	
	public  void menu_master() {//管理者画面での処理。
		
		Scanner check = new Scanner(System.in);

		EditReceipt er = new EditReceipt();
		
		EditItem ei = new EditItem();
		
		EditCustomer ec = new EditCustomer();
		
		EditMember em = new EditMember();
		
		System.out.println("実行する操作を選んでください。\n0.ホーム画面へ戻る\n1.注文編集\n2.商品編集\n3.顧客編集\n4.従業員編集");
		mainmenu = check.nextInt();//入力した値をmainmenuに入れる
		
		if(mainmenu == 0) {//ホーム画面に移動する。
			System.out.println("初期画面に移動します。");
			Main.home();
		}
		
		if(mainmenu == 1) {//注文編集画面に移動する。
			System.out.println("注文編集画面に移動します。");
			er.startMenu();//???の部分はどのメソッドを実行するか不明.
		}
		
		if(mainmenu == 2) {//商品編集画面に移動する。
			System.out.println("商品編集画面に移動します。");
			ei.startMenu();//???の部分はどのメソッドを実行するか不明
		}
		
		if(mainmenu == 3) {//顧客編集画面に移動する。
			System.out.println("顧客編集画面に移動します。");
			ec.startMenu();//???の部分はどのメソッドを実行するか不明
		}
		
		if(mainmenu == 4) {//従業員編集画面に移動する。
			System.out.println("従業員編集画面に移動します。");
			em.startMenu();//???の部分はどのメソッドを実行するか不明
		}
		
		if(mainmenu == 5) {//管理者閲覧画面に移動する。
			System.out.println("管理者閲覧画面に移動します。");
			menu_employee();
		}
		
		
	}
	
	
}


