package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUpdateTest {
    public static void main(String[] args) {
        // 연결을 위한 정보 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // 1. Connection 객체 생성.
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성
            // SimpleDateFormat 객체
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String sql = "update person set phone1='02', phone2='588-9991', age=20, "
                    + "modifyDate='" + sf.format(new Date()) + "' where id=1";

            // 3.Statement 객체 생성.
            Statement stmt = conn.createStatement();
            // 4.SQL을 실행.
            // executeUpdate(sql) 의 반환값 int -> 0 이며 실패, 1이면 1개 쿼리 성공.
            int result = stmt.executeUpdate(sql);
            // 5. 결과 확인
            if (result != 0) {
                System.out.println("레코드 수정 성공!!");
            } else {
                System.out.println("레코드 수정 실패!!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
