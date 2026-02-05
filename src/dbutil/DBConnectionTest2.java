package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest2 {

    public static void main(String[] args) {
        // DB Connection 작업을 직접 해보세요.
        // 1. Connection 객체 선언

        // 2. DB Connection Test
        // 1) 드라이버 로드(ClassNotFoundException)
        // 2) Connection 객체 생성(SQLException)
        // 객체 생성시 정보
        // url, user, password

        // 예외 없이 잘 동작하면 성공! 예외 발생시 실패.

        // try~ resource를 사용하는 방법
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String pasword = "jdbcuser";

        // try ~ catch 구분에 finally를 사용해서 Connection 객체를 해제하지 않아도 됨.
        try (Connection conn = DriverManager.getConnection(url, user, pasword)) {
            System.out.println(conn);
            System.out.println("DB 연결 성공");
        } catch (Exception e) {
            System.out.println("DB 연결 성공");
            System.out.println(e.getMessage());
        }
    }

}
