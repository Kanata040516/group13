package yoshida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Judge_pass_id {

    public static void main(String[] args) {
        judge();
    }

    public static void judge() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("IDを入力してください：");
        String inputId = scanner.nextLine();
        System.out.println("パスワードを入力してください：");
        String inputPass = scanner.nextLine();

        // DB接続情報
        String url = Text.url;
        String user = Text.user_name;
        String password = Text.password;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT password FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, inputId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPass = rs.getString("password");

                if (inputPass.equals(dbPass)) {
                    System.out.println("ログイン成功！");

                    if (inputId.equals("0000")) {
                        menu_master();
                    } else {
                        menu_employee();
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
    }
//テスト用でメソッド作っとく
    public static void menu_master() {
        System.out.println("=== 店長メニュー ===");
        // 店長向け処理をここに
    }

    public static void menu_employee() {
        System.out.println("=== 従業員メニュー ===");
        // 従業員向け処理をここに
    }
}
