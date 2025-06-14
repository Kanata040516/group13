package miyakoshi;

import java.util.Scanner;

import com.sun.tools.javac.Main;

public class Receipt {
	
	public static void main(String[] args) {
		
		static int menu = 0 ;  //0-6, 注文検索の項目
		static String search = null; //注文検索の内容
		menuReceipt(menu, search);
	}
		
		public static void menuReceipt(int menu, String search) {
			
			Scanner sc1 = new Scanner(System.in); //項目を選択
			
			while(true) {
				
				System.out.println("【注文管理】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("どの項目を検索しますか？\n "
						+ "操作したい番号をお選びください。\n");
				System.out.println("0.ホーム画面\n" + "1. 注文ID \n" + "2.日付\n" + 
				"3.顧客名\n" + "4.商品名\n" + "5.分類\n" + "6.メニュー画面\n");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				
				System.out.print("番号を入力 ： ");
				menu = sc1.nextInt();

				if ( menu == 1 ||menu == 2 || menu == 3 || menu == 4 || menu == 5) {
					break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
//					Main.main() ; //ホーム画面へ
				} else if (menu == 6) {
//					Menu_employee.menu_select();  //メニュー画面へ
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
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("日付 ： ");
				search = sc2.nextLine();
			}
			else if (menu == 3) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい顧客名を入力してください。");
				System.out.print("顧客名 ： ");
				search = sc2.nextLine();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			}			
			else if (menu == 4) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品名を入力してください。");
				System.out.print("商品名 ： ");
				search = sc2.nextLine();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			}		
			else if (menu == 5) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい商品分類を入力してください。");
				System.out.print("商品分類 ： ");
				search = sc2.nextLine();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			}
		
			sc1.close();
			sc2.close();
			selectReceipt();
	}
		public static void selectReceipt() {
			   //Selectへ移動し、注文情報のSQLを実行する
//			Select.selectReceipt(  ); 
			
			Scanner sc3 = new Scanner(System.in);  //検索終了後の画面移動
			System.out.println("\nーーーーーーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("移動する画面を選択してください。\n");
			System.out.println("0.ホーム画面\n" + "[店長] 1.閲覧メニュー画面 \n" + "[従業員] 2. 閲覧メニュー画面");//ここ相談
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーーーーーーー\n");
			
			System.out.print("番号を入力 ： ");
			sc3.nextInt();
			
			while(true) {
				if ( menu == 0 ) {
				Main.main() ; //ホーム画面へ
				} else if (menu == 1) {
//             	Menu_master();
				else if(menu == 2) {
//				    Menu.employee();
				}
				} else {
					System.out.println("\n【エラー：項目以外の内容の入力】");
					System.out.println("0か1の番号を入力してください。\n");
				}
			}	
		}

}
