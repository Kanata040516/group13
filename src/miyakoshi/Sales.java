package miyakoshi;

import java.util.Scanner;
import shimizu.Select;
import nanamori.Menu_employee;
import yoshida.Main;

public class Sales {

	static int menu;//0-6, 注文検索の項目
	static String date; //売り上げの日にちの記入
	
	while(true) {
		
		System.out.println("【注文管理】");
		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		System.out.println("どの項目を検索しますか？\n "
				+ "操作したい番号をお選びください。\n");
		System.out.println("0.ホーム画面\n" + "1.月次\n" + "2.日次\n" + 
		"3.顧客名と月次\n" + "4.顧客名と日次\n" + "5.メニュー画面\n");
		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		
		System.out.print("番号を入力 ： ");
		menu = sc1.nextInt();

		if ( menu == 1 ||menu == 2 || menu == 3 || menu == 4 ) {
			break; //ループを抜けて詳細画面へ
			} else if (menu == 0) {
			Main.main(null) ;//ホーム画面へ
				} else if (menu == 5) {
					Menu_employee employee = new Menu_employee(); 
					employee.menu_employee(); //メニュー画面へ
					} else {
						System.out.println("\n【エラー：項目以外の内容の入力】");
						System.out.println("0〜5の番号を入力してください。\n");
						}
		}
	
	//詳細画面へ
	public static void startDate(String Date) {
		Scanner sc2 = new Scanner(System.in); //開始
		System.out.print("開始月： ");
		date = sc2.nextLine();
	}
	public static void lasrtDate(String Date) {
		Scanner sc3 = new Scanner(System.in); //終了
		System.out.print("終了月： ");
		date = sc3.nextLine();
	}
	
	
	Scanner sc4 = new Scanner(System.in); //顧客
	
	if (menu == 1) { 
		System.out.println("\n【月次】");
		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		System.out.println("期間を指定して売上合計を計算します。\n" 
				+ "表示したいデータの年、開始月、終了月を選択したください。\n");
		startDate();
		lasrtDate();
		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
	}  
	   else if (menu == 2) {
		    System.out.println("\n【日次】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("期間を指定して売上合計を計算します。\n" 
					+ "表示したいデータの年、開始日、終了日を選択したください。\n");

			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
	}
	   else if (menu == 3) {
		    System.out.println("\n【顧客名と月次】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("顧客名と期間を指定して売上合計を計算します。\n" 
					+ "表示したいデータの顧客名、年、開始月、終了月を選択したください。\n");

			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
	}
	   else if (menu == 4) {
		    System.out.println("\n【顧客名と日次】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("顧客名と期間を指定して売上合計を計算します。\n" 
					+ "表示したいデータの顧客名、年、開始日、終了日を選択したください。\n");

			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
	}
	


}
