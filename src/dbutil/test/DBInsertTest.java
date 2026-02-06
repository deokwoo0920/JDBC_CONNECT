package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBInsertTest {
    public static void main(String[] args) {
        // 1. Connection 연결 객체 생성(DriverManager.getConnection())
        // 2. SQL 작성
        // 3. Connection 객체에서 Statement 객체를 생성.
        // 4. 생성된 Statement 객체를 이용해서 SQL을 실행(Insert)
        // 5. 이후 작업이 있으면, 없으면 종료.

        // 연결을 위한 정보 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // 1. Connection 객체 생성.
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성
            String sql = "insert into person (userId, userPw, username, userEmail) " +
                    "values ('testuser1','testuser1','testuser1','testuser1@test.com')";

            // 3.Statement 객체 생성.
            Statement stmt = conn.createStatement();
            // 4.SQL을 실행.
            // executeUpdate(sql) 의 반환값 int -> 0 이며 실패, 1이면 1개 쿼리 성공.
            int result = stmt.executeUpdate(sql);
            // 5. 결과 확인
            if (result != 0) {
                System.out.println("레코드 추가 성공!!");
            } else {
                System.out.println("레코드 추가 실패!!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
