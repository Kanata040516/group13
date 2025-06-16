package miyakoshi;

import java.util.Scanner;
import shimizu.Select;
import nanamori.Menu_employee;
import yoshida.Main;

public class Sales {

	static int menu = 0;//0-6, 注文検索の項目
	static String date = null; //売り上げ日の記入
	static String month = null;//売り上げ月の記入
	
	public static String startDate() {
		Scanner sc = new Scanner(System.in); //開始日
		System.out.print("開始日： ");
		String startDate = sc.nextLine();
		return startDate;
	}
	public static String lastDate() {
		Scanner sc = new Scanner(System.in); //終了日
		System.out.print("終了日： ");
		String lastDate = sc.nextLine();
		return lastDate;
	}
	public static String Month() {
		Scanner sc = new Scanner(System.in); //月
		System.out.print("月： ");
		String Month = sc.nextLine();
		return Month;
	}
	public static String Customer() {
		Scanner sc = new Scanner(System.in); //顧客
		System.out.print("\n顧客名：  ");
		String Customer = sc.nextLine();
		return Customer;
	}
	
	public static void menuSales(String date, int menu) {
		
		while(true) {
			Scanner sc = new Scanner(System.in); //項目を選択 
			System.out.println("【注文管理】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("どの項目を検索しますか？\n "
					+ "操作したい番号をお選びください。\n");
			System.out.println("0.ホーム画面\n" + "1.月次\n" + "2.日次\n" + 
			"3.顧客名と月次\n" + "4.顧客名と日次\n" + "5.メニュー画面\n");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			
			System.out.print("番号を入力 ： ");
			menu = sc.nextInt();

			if ( menu == 1 ||menu == 2 || menu == 3 || menu == 4 ) {
				break; //ループを抜けて詳細画面へ
				} else if (menu == 0) {
//				Main.main(null) ;//ホーム画面へ
					} else if (menu == 5) {
//						Menu_employee employee = new Menu_employee(); 
//						employee.menu_employee(); //メニュー画面へ
						} else {
							System.out.println("\n【エラー：項目以外の内容の入力】");
							System.out.println("0〜5の番号を入力してください。\n");
							}
			}
		
		//詳細画面へ
		if (menu == 1) { 
			System.out.println("\n【月次】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("期間を指定して売上合計を計算します。\n" 
					+ "表示したいデータの年と月を入力してください。");
			System.out.println("記入例：2025-06");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}  
		   else if (menu == 2) {
			    System.out.println("\n【日次】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("期間を指定して売上合計を計算します。\n" 
						+ "表示したいデータの年、開始日、終了日を入力してください。\n");
				System.out.println("記入例：2025-06-01, 2025-06-30");
				startDate();
				lastDate();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}
		   else if (menu == 3) {
			    System.out.println("\n【顧客名と月次】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("顧客名と期間を指定して売上合計を計算します。\n" 
						+ "表示したいデータの顧客名と月を入力してください。\n");
				System.out.println("記入例：2025-06");
				Month();
				Customer();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}
		   else if (menu == 4) {
			    System.out.println("\n【顧客名と日次】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("顧客名と期間を指定して売上合計を計算します。\n" 
						+ "表示したいデータの顧客名、年、開始日、終了日を選択入力してください。\n");
				System.out.println("記入例：2025-06-01, 2025-06-30");
				startDate();
				lastDate();
				Customer();
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}
		
		//結果
		if (menu == 1) { 
            String month = Month(); // メソッドを呼び出して戻り値を取得
			System.out.println("\n【月次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( month );
			System.out.println("\n売上合計" + Select.selectReceipt()[1]  + "円" ); 
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}  
		else if(menu == 2) {
			String start = startDate();
			String last = lastDate();
			System.out.println("\n【日次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( start + "～" + last);
			System.out.println("\n売上合計" + Select.selectReceipt()[1]  + "円" ); 
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}
		else if (menu == 3) { 
			// Monthメソッドを呼び出して戻り値を取得
            String month = Month(); 
            String customer = Customer();
			System.out.println("\n【月次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( "顧客名 ： " + customer + "\n");
			System.out.println( month );
			System.out.println("\n売上合計" + Select.selectReceipt()[1]  + "円" ); 
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}  
		else if(menu == 4) {
			String start = startDate();
			String last = lastDate();
			 String customer = Customer();
			System.out.println("\n【日次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( "顧客名 ： " + customer + "\n" );
			System.out.println( start + "～" + last);
			System.out.println("\n売上合計" + Select.selectReceipt()[1]  + "円" ); 
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}
	}

}
