package miyakoshi;

import java.util.Scanner;

import nanamori.Menu_employee;
import nanamori.Menu_master;
import shimizu.Select;
import yoshida.Judge_pass_id;
import yoshida.Main;

public class Customer {
	
	static int menu;  //0-6, 注文検索の項目
	static String  search ;//注文検索の内容

		public static void menuCustomer(int menu, String search) {
			
			Scanner sc1 = new Scanner(System.in); //項目を選択
			
			while(true) {
				
				System.out.println("【顧客管理】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("どの項目を検索しますか？\n "
						+ "操作したい番号をお選びください。\n");
				System.out.println("0.ホーム画面\n" + "1. 顧客名 \n" + "2.所在地(都道府県)\n" + 
				"3.店舗形態\n" + "4.一覧表示\n"+ "5.メニュー画面\n");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("番号を入力 ： ");
				menu = sc1.nextInt();

				if  (menu == 1 ||menu == 2 ||menu == 3 ||menu == 4  ) {
					break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
					Main.main(null) ; //ホーム画面へ
				} else if (menu == 5) {
					Menu_employee employee = new Menu_employee(); 
					employee.menu_employee(); //メニュー画面へ
				} else {
					System.out.println("\n【エラー：項目以外の内容の入力】");
					System.out.println("0〜4の番号を入力してください。\n");
				}
		}
			
			//詳細画面へ
			Scanner sc2 = new Scanner(System.in); 
			
			if (menu == 1) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい顧客名を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("顧客名 ： ");
				search = sc2.nextLine();
			}
			
			else if (menu == 2) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい所在地(都道府県)を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("都道府県 ： ");
				search = sc2.nextLine();
			}
			else if (menu == 3) { 
				Select.selectCustomerGroup();
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい店舗形態番号を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("店舗形態番号 ");
				search = sc2.nextLine();
			}
			else if (menu == 4) { 
				//Select.selectCustomer( menu, search );//一覧表示なので詳細検索は無し
			}
			
			Select.selectCustomer( menu, search );//結果表示をSelectクラスで
			
			//結果表示後
			Scanner sc = new Scanner(System.in); 
			while(true) {
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("移動したい画面の番号をお選びください\n");
			System.out.println("1.顧客管理画面\n" + "2.メニュー画面(ID、パスワード入力)\n\n" + "0.終了");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			System.out.print("番号： ");
			int move = sc.nextInt();
			
			if(move == 0) {
				System.exit(move);
				break;
			}
			else if(move == 1) {
				menuCustomer(0,null);
				break;
			}
			else if(move == 2) {
				Judge_pass_id j = new Judge_pass_id();
			    int pass = j.judge();
				if ( pass == 1) {
					Menu_master ma = new Menu_master(); //店長のメニュー画面
					ma.menu_master();
	          } else {
	          	Menu_employee em = new Menu_employee(); //従業員のメニュー画面
	          	em.menu_employee();
	          }
				break;
			}
			 else {
					System.out.println("\n【エラー：項目以外の内容の入力】");
					System.out.println("0〜2の番号を入力してください。\n");
				}
			}
			sc.close();
			sc1.close();
			sc2.close();
	}
}
