package miyakoshi;

import java.util.Scanner;

import nanamori.Menu_employee;
import nanamori.Menu_master;
import shimizu.Select;
import yoshida.Judge_pass_id;

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
				System.out.println( "1. 注文番号 \n" + "2.日付\n" + "3.顧客名\n" + 
						"4.商品名\n" + "5.商品分類\n" + "6.閲覧選択画面\n\n" + "0.メニュー画面(ID、パスワード入力)");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				
				System.out.print("番号を入力 ： ");
				menu = sc1.nextInt();

				if ( menu == 1 ||menu == 2 || menu == 3 || menu == 4 || menu == 5) {
					break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
					Judge_pass_id j = new Judge_pass_id ();
					j.judge();//パスワード入力画面へ
				} else if (menu == 6) {
					Menu_employee employee = new Menu_employee(); 
					employee.menu_employee(); //閲覧選択画面へ
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
				System.out.println("検索したい注文番号を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("注文番号 ： ");
				search = sc2.nextLine();
			}	
            else if (menu == 2) { 
            	int year = 0;
            	int month = 0;
            	int date = 0;
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい日付の年、月、日を入力してください。");
				//System.out.println("記入例：2025-06-10");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				Scanner sc = new Scanner(System.in); 
				while(true) {
				System.out.print("年：");
				year = sc.nextInt();
				System.out.print("月：");
				month = sc.nextInt();
				System.out.print("日：");
				date = sc.nextInt();
				if (!(String.valueOf(year).length() == 4) || year < 1900 || year > 2500) {
				    System.out.println("西暦で4桁の正しい年を入力してください");
				    continue;
				}
				if(month > 13 || month < 1) {
					System.out.println("正しい月を入力してください");
					continue;
				}
				if(date > 32 || month < 1) {
					System.out.println("正しい日にちを入力してください");
					continue;
				}
				break;
			    }
				search = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", date);
			}//else if
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
				System.out.println("検索したい商品分類番号を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("商品分類 番号： ");
				search = sc2.nextLine();
			}
			
			Select.selectReceipt( menu, search ); //結果表示をSelectクラスで
			
			//結果表示後
			Scanner sc = new Scanner(System.in); 
			while(true) {
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("移動したい画面の番号をお選びください\n");
			System.out.println("1.注文管理画面\n" + "2.メニュー画面(ID、パスワード入力)\n" + "0.終了");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			System.out.print("番号： ");
			int move = sc.nextInt();
			
			if(move == 0) {
				System.exit(move);
				break;
			}
			else if(move == 1) {
				menuReceipt(0,null);
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
			}//while
			sc.close();
	}
}
