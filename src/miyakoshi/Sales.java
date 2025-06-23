package miyakoshi;
import java.util.Scanner;

import nanamori.Menu_employee;
import nanamori.Menu_master;
import shimizu.Select;
import yoshida.Judge_pass_id;
import yoshida.Main;

public class Sales {
 
	static int menu = 0 ;//0-6, 注文検索の項目
	static String date = null; //売り上げ日の記入
	static String month = null;//売り上げ月の記入
	
	static String startDate = null;
	static String lastDate = null;
	static String Month = null;
	static String Customer = null;
	
	public String startDate() {
		if(!(startDate == null)) {
			return startDate;
		}
		Scanner sc = new Scanner(System.in); //開始日
		System.out.print("開始日： ");
		startDate = sc.nextLine();
		return startDate;
	}
	
	public String lastDate() {
		if(!(lastDate == null)) {
			return lastDate;
		}
		Scanner sc = new Scanner(System.in); //終了日
		System.out.print("終了日： ");
		lastDate = sc.nextLine();
		return lastDate;
	}
	
	public String Month() {
		if(!(Month == null)) {
			return Month;
		}
		Scanner sc = new Scanner(System.in); //月
		System.out.print("月： ");
		Month = sc.nextLine();
		return Month;
	}
	
	public String Customer() {
		if(!(Customer == null)) {
			return Customer;
		}
		Scanner sc = new Scanner(System.in); //顧客
		System.out.print("\n顧客名：  ");
		Customer = sc.nextLine();
		return Customer;
	}
	
	public static void menuSales(String date, int menu) {
		
		while(true) {
			Scanner sc = new Scanner(System.in); //項目を選択
			System.out.println("【売上管理】");
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
		Sales sales = new Sales();
		if (menu == 1) {
			System.out.println("\n【月次】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println("期間を指定して売上合計を計算します。\n"
					+ "表示したいデータの年と月を入力してください。");
			System.out.println("記入例⇒ 2025-06");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}  
		   else if (menu == 2) {
			    System.out.println("\n【日次】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("期間を指定して売上合計を計算します。\n"
						+ "表示したいデータの年、開始日、終了日を入力してください。\n");
				System.out.println("記入例⇒ 開始日：2025-06-01 終了日：2025-06-30");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}
		   else if (menu == 3) {
			    System.out.println("\n【顧客名と月次】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("顧客名と期間を指定して売上合計を計算します。\n"
						+ "表示したいデータの顧客名と月を入力してください。\n");
				System.out.println("記入例⇒ 2025-06\n");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}
		   else if (menu == 4) {
			    System.out.println("\n【顧客名と日次】");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
				System.out.println("顧客名と期間を指定して売上合計を計算します。\n"
						+ "表示したいデータの顧客名、年、開始日、終了日を選択入力してください。\n");
				System.out.println("記入例⇒ 開始日：2025-06-01 終了日：2025-06-30");
				System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		}
		
		//結果
		if (menu == 1) {
            String month = sales.Month(); // メソッドを呼び出して戻り値を取得
			System.out.println("\n【月次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( month +"\n");
			int totalprice = Select.selectReceipt(9,null)[1];
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			System.out.println("小計：" +totalprice + "円" );
			System.out.println("合計：" +(int)(totalprice*1.1) + "円" );
			System.out.println("(うち消費税：" +(int)(totalprice*0.1) + "円)\n" );
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}  
		else if(menu == 2) {
			String start = sales.startDate();
			String last = sales.lastDate();
			System.out.println("\n【日次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( start + "～" + last + "\n");
			int totalprice = Select.selectReceipt(7,null)[1];
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			System.out.println("小計：" +totalprice + "円" );
			System.out.println("合計：" +(int)(totalprice*1.1) + "円" );
			System.out.println("(うち消費税：" +(int)(totalprice*0.1) + "円)\n" );
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}
		else if (menu == 3) {
            String month = sales.Month();
            String customer = sales.Customer();
			System.out.println("\n【月次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( "顧客名 ： " + customer + "\n");
			System.out.println( month + "\n" );
			int totalprice = Select.selectReceipt(10,customer)[1];
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			System.out.println("小計：" +totalprice + "円" );
			System.out.println("合計：" +(int)(totalprice*1.1) + "円" );
			System.out.println("(うち消費税：" +(int)(totalprice*0.1) + "円)\n" );
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}  
		else if(menu == 4) {
			String start = sales.startDate();
			String last = sales.lastDate();
			String customer = sales.Customer();
			System.out.println("\n【日次：合計】");
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
			System.out.println( "顧客名 ： " + customer + "\n" );
			System.out.println( start + "～" + last + "\n");
			int totalprice =  Select.selectReceipt(8,customer)[1];
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
			System.out.println("小計：" +totalprice + "円" );
			System.out.println("合計：" +(int)(totalprice*1.1) + "円" );
			System.out.println("(うち消費税：" +(int)(totalprice*0.1) + "円)\n" );
			System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		}
		
		//結果表示後
		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー");
		System.out.println("移動したい画面の番号をお選びください\n");
		System.out.println("1.売上管理画面\n" + "2.メニュー画面(ID、パスワード入力)\n\n" + "0.終了");
		Scanner sc = new Scanner(System.in); 
		System.out.println("ーーーーーーーーーーーーーーーーーーーーーー\n");
		System.out.print("番号： ");
		int move = sc.nextInt();
		
		if(move == 0) {
			System.exit(move);
		}
		else if(move == 1) {
			menuSales(null,0);
			startDate = null;
			lastDate = null;
			Month = null;
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
 