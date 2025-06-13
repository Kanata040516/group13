package nanamori;

import java.util.Scanner;

public class Menu_employee {

	private static int mainmenu  ;//メインメニューの番号を格納する変数
	
	public static void menu_select() {
		
		System.out.println("実行する操作を選んでください。\n0.ホーム画面へ戻る\n1.注文管理\n2.顧客管理\n3.商品管理\n4.売上管理\n5.データ分析");
		Scanner check = new Scanner(System.in);
		
		
		mainmenu = check.nextInt();//入力した値をmainmenuに入れる
		
		
		if(mainmenu == 0) {//ホームに戻る
			System.out.println("初期画面に移動します。");
			//Main.home();
			
		}
		
		else if(mainmenu == 1) {//注文管理画面に移動
			System.out.println("注文管理画面に移動します。");
			//Receipt.receipt();
		}
		
		else if(mainmenu == 2) {//顧客管理画面に移動
			System.out.println("顧客管理画面に移動します。");
			//Customer.customer();
		}
		
		else if(mainmenu == 3) {//商品管理画面に移動
			System.out.println("商品管理画面に移動します。");
			//Item.item();
		}
		
		else if(mainmenu == 4) {//売上管理画面に移動
			System.out.println("売上管理画面に移動します。");
			//Sales.sales();
		}
				
	}
	

	
}
