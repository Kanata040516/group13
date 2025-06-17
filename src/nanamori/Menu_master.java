package nanamori;

import java.util.InputMismatchException;
import java.util.Scanner;

import nakamori.EditCustomer;
import nakamori.EditItem;
import nakamori.EditMember;
import nakamori.EditReceipt;
import yoshida.Main;

public class Menu_master extends Menu_employee{

	
	@Override
	public  void menu_employee() {//マスター視点のmenu_select処理
	
		mainmenu = 0;//mainmenuの値を初期化する処理。
		
		System.out.println("実行する操作を選んでください。\n0.ホーム画面へ戻る\n1.注文管理\n2.顧客管理\n3.商品管理\n4.売上管理\n5.編集画面");
		
		mastermove = true;//店長が操作しているかを判別する。
		
		if(mainmenu == 5) {//管理者編集画面に移行する。
			System.out.println("");
			System.out.println("管理者編集画面に移動します。");
			menu_master();
		}
		else {//menu_employee内で入力された値が5じゃない場合、親クラスのコードを参照して処理を行う。
			super.menu_employee();
		}

	}
	
	
	
	public  void menu_master() {//管理者画面での処理。
		
		Scanner check = new Scanner(System.in);

		EditReceipt er = new EditReceipt();
		
		EditItem ei = new EditItem();
		
		EditCustomer ec = new EditCustomer();
		
		EditMember em = new EditMember();
		
		System.out.println("実行する操作を選んでください。\n0.ホーム画面へ戻る\n1.注文編集\n2.商品編集\n3.顧客編集\n4.従業員編集\n5.閲覧画面に移動");
		
		try {//入力された値が数字じゃなかった場合catchにエラーを投げる
			
			mainmenu = check.nextInt();//入力した値をmainmenuに入れる
			
			if(mainmenu == 0) {//ホーム画面に移動する。
				System.out.println("初期画面に移動します。");
				Main.main(null);
			}
			
			else if(mainmenu == 1) {//注文編集画面に移動する。
				System.out.println("注文編集画面に移動します。");
				er.startMenu();//EditReceipt内のstartMenuを実行
			}
			
			else if(mainmenu == 2) {//商品編集画面に移動する。
				System.out.println("商品編集画面に移動します。");
				ei.startMenu();//EditItem内のstartMenuを実行
			}
			
			else if(mainmenu == 3) {//顧客編集画面に移動する。
				System.out.println("顧客編集画面に移動します。");
				ec.startMenu();//EditCustomer内のstartMenuを実行
			}
			
			else if(mainmenu == 4) {//従業員編集画面に移動する。
				System.out.println("従業員編集画面に移動します。");
				em.startMenu();//EditMember内のstartMenuを実行
			}
			
			else if(mainmenu == 5) {//管理者閲覧画面に移動する。
				System.out.println("管理者閲覧画面に移動します。");
				menu_employee();
			}
			
			else {//指定以外の入力が行われた際に、もう一度入力をさせる処理。
				System.out.println("想定外の入力が行われました。もう一度入力してください。");
				System.out.println("");
				menu_master();
			}
			
			
		}
		catch(InputMismatchException e){//入力された値が数字じゃなかった場合再起処理によってもう一度入力させる処理。
			System.out.println("数字で入力してください。もう一度入力してください。");
			System.out.println("");
			menu_employee();
		}
		
	}
	
	
}


