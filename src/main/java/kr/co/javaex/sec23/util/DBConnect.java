package kr.co.javaex.sec23.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Enumeration;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnect {

    private static final Properties prop = new Properties();

    public static Connection getConnection() {
        String user = "DA2603";
        String password = "Data2603";

        String walletPath = "C:/wallet/Wallet_DinkDB";

        String url = "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=" + walletPath;

        try {
            System.out.println("데이터베이스 직접 연결 시도 중...");

            Connection conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println(">>> 드라이버 없이 자동 연결 성공! <<<");
            }
            return conn;

        } catch (SQLException e) {
            System.err.println("접속 실패: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
