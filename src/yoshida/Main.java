package yoshida;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		
		System.out.println("     【商品管理システム】");
		
		System.out.println("ーーーーーーーーーーーーーーーーー");
		System.out.println("操作したい番号をお選びください\n");
		System.out.println("1.操作を始める　\n0.終了");
		System.out.println("ーーーーーーーーーーーーーーーーー");
		
		int start =new Scanner(System.in).nextInt();
		
		
		
		if(start==1) {
			Judge_pass_id.judge();
			
		}else if(start==0) {
			System.out.println("終了します");

		}else {
			System.out.println("もう一度入力しなおしてください");
			main(args);
		}
		
		
	}

}
