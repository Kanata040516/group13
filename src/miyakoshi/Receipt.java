package miyakoshi;

import java.util.Scanner;

import nanamori.Menu_employee;
import nanamori.Menu_master;
import shimizu.Select;
import yoshida.Judge_pass_id;
import yoshida.Main;

public class Receipt {
		
		static int menu ;  //0-6, 注文検索の項目
		static String  search ;//注文検索の内容
		
		public static void menuReceipt (int menu, String Receipt) {
			
			Scanner sc1 = new Scanner (System.in); //項目を選択
			
			while (true) {
				
				System.out.println("【注文管理】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("どの項目を検索しますか？\n "
						+ "操作したい番号をお選びください。\n");
				System.out.println("0.ホーム画面\n" + "1. 注文ID \n" + "2.日付\n" + 
				"3.顧客名\n" + "4.商品名\n" + "5.商品分類\n" + "6.メニュー画面\n");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				
				System.out.print("番号を入力 ： ");
				menu = sc1.nextInt();

				if ( menu == 1 ||menu == 2 || menu == 3 || menu == 4 || menu == 5) {
					break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
					Main.main(null) ; //ホーム画面へ
				} else if (menu == 6) {
					Menu_employee employee = new Menu_employee(); 
					employee.menu_employee(); //メニュー画面へ
				} else {
					System.out.println("\n【エラー：項目以外の内容の入力】");
					System.out.println("0〜6の番号を入力してください。\n");
				}
		}
			
			//詳細画面へ
			Scanner sc2 = new Scanner(System.in); 
			
			if (menu == 1) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい注文IDを入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("注文ID ： ");
				search = sc2.nextLine();
			}	
            else if (menu == 2) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい日付を入力してください。");
				System.out.println("記入例：2025-06-10");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("日付 ： ");
				search = sc2.nextLine();
			}
			else if (menu == 3) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい顧客名を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("顧客名 ： ");
				search = sc2.nextLine();
			}			
			else if (menu == 4) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品名を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("商品名 ： ");
				search = sc2.nextLine();
			}		
			else if (menu == 5) { 
				Select.selectItemGroup();
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品分類を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("商品分類 ： ");
				search = sc2.nextLine();
			}
			
			Select.selectReceipt( menu, search ); //結果表示をSelectクラスで
			
			//結果表示後
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
				sc.close();
			}
	}
}
