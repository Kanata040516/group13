package miyakoshi;

import java.util.Scanner;

import com.sun.tools.javac.Main;

public class Customer {
	
	public static void main(String[] args) {
		
		static int menu = 0 ;  //0-6, 注文検索の項目
		static String search = null; //注文検索の内容
		
		menuCustomer(menu, search);
	}

		public static void menuCustomer(int menu, String search) {
			
			Scanner sc1 = new Scanner(System.in); //項目を選択
			
			while(true) {
				
				System.out.println("【顧客管理】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("どの項目を検索しますか？\n "
						+ "操作したい番号をお選びください。\n");
				System.out.println("");
				System.out.println("0.ホーム画面\n" + "1. 顧客名 \n" + "2.住所\n" + 
				"3.店舗形態\n" + "4.一覧表示\n"+ "5.メニュー画面\n");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				
				System.out.print("番号を入力 ： ");
				menu = sc1.nextInt();

				if (menu == 1 ||menu == 2 ||menu == 3 ||menu == 4  ) {
					break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
//					Main.main() ; //ホーム画面へ
				} else if (menu == 4) {
//					Menu_employee.menu_select();  //メニュー画面へ
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
				System.out.println("検索したい住所を入力してください。");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
				System.out.print("住所 ： ");
				search = sc2.nextLine();
			}
			else if (menu == 3) { 
				System.out.println("\n【詳細検索】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("検索したい店舗形態を入力してください。");
				System.out.print("店舗形態 ");
				search = sc2.nextLine();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			}
			else if (menu == 4) { 
				selectCustomer();//一覧表示なので詳細検索は無し
			}
			sc1.close();
			sc2.close();
			selectCustomer();
	}
		public static void selectCustomer() {
			   //Selectへ移動し、注文情報のSQLを実行する
//			Select.selectReceipt(  ); 
			
			Scanner sc3 = new Scanner(System.in);  //検索終了後の画面移動
			System.out.println("\nーーーーーーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("移動する画面を選択してください。\n");
			System.out.println("0.ホーム画面\n" + "1.閲覧メニュー画面 \n");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーーーーーーー\n");
			
			System.out.print("番号を入力 ： ");
			sc3.nextInt();
			
			while(true) {
				if ( menu == 0 ) {
				Main.main() ; //ホーム画面へ
				} else if (menu == 1) {
//          	Menu_master();
//				Menu.employee();
				} else {
					System.out.println("\n【エラー：項目以外の内容の入力】");
					System.out.println("0か1の番号を入力してください。\n");
				}
			}	
		}

}
