package miyakoshi;

import java.util.Scanner;

import nanamori.Menu_employee;
import nanamori.Menu_master;
import shimizu.Select;
import yoshida.Judge_pass_id;
import yoshida.Main;

public class Item {
	
		static int menu;  //0-6, 注文検索の項目
		static String search ; //注文検索の内容
		
		static int minPrice = -1;//範囲検索の最初の値
		static int maxPrice =-1;//範囲検索の最後の値
		
		public int minPrice() {
			if(!(minPrice == -1)) {
				return minPrice;
			}
			Scanner sc = new Scanner(System.in); 
			while(true) {
				System.out.print("最低価格： ");
				minPrice = sc.nextInt();
				if(minPrice >= 0) {
					break;
				}
				else {
					System.out.println("正しい値を入力してください");
				}
			}//while
			return minPrice;
		}
		
		public int maxPrice() {
			if(!(maxPrice == -1)) {
				return maxPrice;
			}
			Item i = new Item();
			Scanner sc = new Scanner(System.in); 
			while(true) {
				System.out.print("最高価格： ");
				maxPrice = sc.nextInt();
				
				if(maxPrice > i.minPrice()) {
					break;
				}
				else {
					System.out.println("正しい値を入力してください");
				}
			}//while
			return maxPrice;
		}
		
		public static void menuItem(int menu, String search) {
			
			Scanner sc1 = new Scanner(System.in); //項目を選択
			
			while(true) {
				
				System.out.println("【商品管理】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("どの項目を検索しますか？\n "
						+ "操作したい番号をお選びください。\n");
				System.out.println("0.ホーム画面\n" + "1.商品 \n" + "2.価格\n" + 
				"3.商品名\n" + "4.商品分類番号\n" + "5.一覧表示\n" + "6.メニュー画面\n" );
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");	
				System.out.print("番号を入力 ： ");
				menu = sc1.nextInt();

				if  (menu == 1 ||menu == 2 ||menu == 3 || menu == 4 || menu == 5) {
					break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
					Main.main(null) ; //ホーム画面へ
				} else if (menu == 6) {
					Menu_employee employee = new Menu_employee(); 
					employee.menu_employee(); //メニュー画面へ
				} else {
					System.out.println("\n【エラー：項目以外の内容の入力】");
					System.out.println("0〜5の番号を入力してください。\n");
				}
		}
			
			//詳細画面へ
			Scanner sc2 = new Scanner(System.in); 
			
			if (menu == 1) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品番号を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("商品番号： ");
				search = sc2.nextLine();
			}
			
			else if (menu == 2) { 
				System.out.println("\n【詳細検索】");
				while(true) {
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("1.価格検索(例：550円)");
				System.out.println("2.価格範囲検索(例：450～550円)");
				int waySearch = new Scanner(System.in).nextInt();
				
				if(!(waySearch == 1 || waySearch == 2)) {
					System.out.println("1、2のどちらかを入力してください");
					continue;
				}
				
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			    System.out.println("検索したい価格を入力してください。");
			    System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				if(waySearch == 1) {
				    System.out.print("価格 ： ");
				    search = sc2.nextLine();
				    break;
				}
				else if(waySearch == 2) {
					menu = 6; 
					Item i = new Item();
					i.minPrice();
					i.maxPrice();
					
					Select.selectItem( menu, null );
					break;
				}
				
				}//while
			}
			
			else if (menu == 3) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品名を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("商品名 ： ");
				search = sc2.nextLine();
			}
			
			else if (menu == 4) { 
				Select.selectItemGroup();
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品分類番号を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("商品分類番号 ： ");
				search = sc2.nextLine();
			}
			if(menu < 6) {
			    Select.selectItem( menu, search );//結果表示をSelectクラスで
			}
			
			//結果表示後
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("移動したい画面の番号をお選びください\n");
			System.out.println("1.商品管理画面\n" + "2.メニュー画面(ID、パスワード入力)\n" + "0.終了");
			Scanner sc = new Scanner(System.in); 
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			System.out.print("番号： ");
			int move = sc.nextInt();
			
			if(move == 0) {
				System.exit(move);
			}
			else if(move == 1) {
				minPrice = -1;
				maxPrice = -1;
				menuItem(0,null);
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
				sc.close();
			}
	}
}
		