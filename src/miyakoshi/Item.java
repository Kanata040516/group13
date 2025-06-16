package miyakoshi;

import java.util.Scanner;
import shimizu.Select;
import nanamori.Menu_employee;
import yoshida.Main;

public class Item {
	
		static int menu;  //0-6, 注文検索の項目
		static String search ; //注文検索の内容
	
		public static void menuItem(int menu, String search) {
			
			Scanner sc1 = new Scanner(System.in); //項目を選択
			
			while(true) {
				
				System.out.println("【注文管理】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("どの項目を検索しますか？\n "
						+ "操作したい番号をお選びください。\n");
				System.out.println("");
				System.out.println("0.ホーム画面\n" + "1. 商品ID \n" + "2.価格\n" + 
				"3.商品名\n" + "4.分類\n" + "5.一覧表示\n" + "6.メニュー画面\n" +);
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				
				System.out.print("番号を入力 ： ");
				menu = sc1.nextInt();

				if (menu == 1 ||menu == 2 ||menu == 3 || menu == 4 ) {
					break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
					Main.main(null) ; //ホーム画面へ
				} else if (menu == 5) {
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
				System.out.println("検索したい商品IDを入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("商品ID ： ");
				search = sc2.nextLine();
			}
			
			else if (menu == 2) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい価格を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("価格 ： ");
				search = sc2.nextLine();
			}
			
			else if (menu == 3) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品名を入力してください。");
				System.out.print("商品名 ： ");
				search = sc2.nextLine();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			}
			
			else if (menu == 4) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品分類を入力してください。");
				System.out.print("商品分類 ： ");
				search = sc2.nextLine();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			}
			else if (menu == 5) { 
				Select.selectItem( menu, search);//一覧表示なので詳細検索は無し
			}
			sc1.close();
			sc2.close();
			Select.selectItem( menu, search );
	}
		