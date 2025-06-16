package nanamori;

import java.util.InputMismatchException;
import java.util.Scanner;

import miyakoshi.Customer;
import miyakoshi.Item;
import miyakoshi.Receipt;
import miyakoshi.Sales;
import yoshida.Main;

public class Menu_employee {

	protected static int mainmenu  ;//メインメニューの番号を格納する変数
	
	protected static boolean mastermove = false;//店長が操作しているかどうかを判別する変数。
	public void  menu_employee() {
		
		if(mastermove == false) {//従業員の時だけ表示するようにするためのif文
			System.out.println("実行する操作を選んでください。\n0.ホーム画面へ戻る\n1.注文管理\n2.顧客管理\n3.商品管理\n4.売上管理");
		}
		
		Scanner check = new Scanner(System.in);
		
		try {//入力された値が数字じゃなかった場合catchにエラーを投げる
			
			mainmenu = check.nextInt();//入力した値をmainmenuに入れる。
			
			if(mainmenu == 0) {//ホームに戻る
				System.out.println("初期画面に移動します。");
				Main.main(null);
				
			}
			
			else if(mainmenu == 1) {//注文管理画面に移動
				System.out.println("注文管理画面に移動します。");
				Receipt.menuReceipt(0, null);
			}
			
			else if(mainmenu == 2) {//顧客管理画面に移動
				System.out.println("顧客管理画面に移動します。");
				Customer.menuCustomer(0,null);
			}
			
			else if(mainmenu == 3) {//商品管理画面に移動。
				System.out.println("商品管理画面に移動します。");
				Item.menuItem(0,null);
			}
			
			else if(mainmenu == 4) {//売上管理画面に移動.
				System.out.println("売上管理画面に移動します。");
				Sales.menuSales(null,0);
			}
			
			else if(mainmenu == 5 && mastermove == true){//店長が操作している時＋mainmenuが5の時にもう一度処理を行う。
				menu_employee();
			}
			
			else{//指定された数字以外が入力された場合にもう一度入力させる処理。
				System.out.println("想定外の入力が行われました。もう一度入力してください。");
				System.out.println("");
				menu_employee();
			}
			
		}
		
		catch(InputMismatchException e){//入力された値が数字じゃなかった場合再起処理によってもう一度入力させる処理。
			System.out.println("数字で入力してください。もう一度入力してください。");
			System.out.println("");
			menu_employee();
		}
		
	}
}
