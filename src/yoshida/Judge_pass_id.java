package yoshida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import nanamori.Menu_employee;
import nanamori.Menu_master;


public class Judge_pass_id {
////テストでメイン作った
//    public static void main(String[] args) {
//        judge();
//    }

    public static int judge() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("IDを入力してください：");
        String id = scanner.nextLine();
        
        System.out.println("パスワードを入力してください：");
        String pass = scanner.nextLine();

        
        int i = 0;

        // DB接続情報
        String url = Text.url;
        String user_name = Text.user_name;
        String password = Text.password;

        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user_name, password);
            String sql = "SELECT ePass FROM member WHERE eID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPass = rs.getString("ePass");

                if (pass.equals(dbPass)) {
                    //System.out.println("ログイン成功！");←いる？？

                    if (id.equals("tentyo")) {
                    	Menu_master master = new Menu_master();
                    	master.menu_master();
//                    	店長だったら1を返す
                    	i = 1;
                    	
                    } else {
                    	Menu_employee employee = new Menu_employee();
                    	employee.menu_employee();
//                    	従業員だったら2を返す
                    	i = 2;
                    }
                } else {
                    System.out.println("パスワードが違います。");
                    judge();
                }
            } else {
                System.out.println("IDが存在しません。");
                judge();
            }

        } catch (SQLException e) {
            System.out.println("DBエラー: " + e.getMessage());
        } finally {
        }
		return i;
    }
////テスト用でメソッド作っといた
//    public static void menu_master() {
//        System.out.println("=== 店長メニュー ===");
//        // 店長向け処理をここに
//    }
//
//    public static void menu_employee() {
//        System.out.println("=== 従業員メニュー ===");
//        // 従業員向け処理をここに
//    }
    

}
