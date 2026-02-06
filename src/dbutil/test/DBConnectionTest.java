package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {
        // 1. 데이터베이스 연결을 위한 Connection 객체 선언
        Connection conn = null;

        // 2. 데이터베이스 접속 테스트
        try {
            // 1) 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로드 성공");

            // 2) 데이터베이스 접속 정보를 담은 Connection 객체를 생성
            // jdbc:mysql://localhost:3306/jdbc
            // jdbc:mysql:// -> jdbc로 mysql 접속합니다.
            // localhost -> 서버 주소. Docker 현재 여러분 컴퓨터 DB서버가 있어요.
            // :3306 -> 포트 번호 (mysql은 포트번호 3306을 기본 포트로 사용)
            // /jdbc -> DB 이름
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc",
                    "jdbcuser",
                    "jdbcuser");
            System.out.println(conn);
            System.out.println("데이터베이스 접속 성공");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
        } catch (SQLException sqle) {
            System.out.println("SQL 에러 발생(데이터베이스 접속 실패)");
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }

    }

}
