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
            String sql = "SELECT password FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPass = rs.getString("password");
                
                while (true) {
                    System.out.print("パスワードを入力してください：");
                    pass = scanner.nextLine();

                    if (passrules(pass)) {
                        System.out.println("パスワードがルールを満たしています。");
                        break; // 入力ループ終了
                    } else {
                        System.out.println("パスワードが条件を満たしていません。再入力してください。");
                    }
                }

                if (pass.equals(dbPass)) {
                    System.out.println("ログイン成功！");

                    if (id.equals("0000")) {
                    	Menu_master master = new Menu_master();
                    	master.menu_master();
//                    	店長だったら0を返す
                    	i = 1;
                    	
                    } else {
                    	Menu_employee employee = new Menu_employee();
                    	employee.menu_employee();
//                    	従業員だったら0を返す
                    	i = 2;
                    }
                } else {
                    System.out.println("パスワードが違います。");
                }
            } else {
                System.out.println("IDが存在しません。");
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
    
    public static boolean passrules(String pass) {
        // ① 文字数（例：8文字以上）
        if (pass.length() < 8) {
            System.out.println("パスワードは8文字以上である必要があります。");
            return false;
        }


        // ② 大文字を含むか
        if (!pass.matches(".*[A-Z].*")) {
            System.out.println("パスワードには大文字を1文字以上含めてください。");
            return false;
        }

        // ③ 小文字を含む
        if (!pass.matches(".*[a-z].*")) {
            System.out.println("パスワードには小文字を1文字以上含めてください。");
            return false;
        }

        // ④ 数字を含む
        if (!pass.matches(".*[0-9].*")) {
            System.out.println("パスワードには数字を1文字以上含めてください。");
            return false;
        }

        // ⑤ 特殊記号を含むか（!@#$%^&*()_+ などを対象に）
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            System.out.println("パスワードには特殊記号を1文字以上含めてください。");
            return false;
        }


        // ② 大文字を含むか
        if (!pass.matches(".*[A-Z].*")) {
            System.out.println("パスワードには大文字を1文字以上含めてください。");
            return false;
        }
 
        // ③ 小文字を含む
        if (!pass.matches(".*[a-z].*")) {
            System.out.println("パスワードには小文字を1文字以上含めてください。");
            return false;
        }
 
        // ④ 数字を含む
        if (!pass.matches(".*[0-9].*")) {
            System.out.println("パスワードには数字を1文字以上含めてください。");
            return false;
        }
 
        // ⑤ 特殊記号を含むか（!@#$%^&*()_+ などを対象に）
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            System.out.println("パスワードには特殊記号を1文字以上含めてください。");
            return false;
        }

        // すべての条件を満たす
        return true;
    }
}
