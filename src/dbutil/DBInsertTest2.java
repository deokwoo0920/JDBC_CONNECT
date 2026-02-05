package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import domain.PersonVO;

public class DBInsertTest2 {
    public static void main(String[] args) {

        // 연결을 위한 정보 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // PersonVO 객체를 생성!!
        PersonVO vo = new PersonVO();
        vo.setUserId("testuser2");
        vo.setUserPw("testuser2pw");
        vo.setUserId("testuser2");
        vo.setUserEmail("testuser2@test.com");
        vo.setPhone1("02");
        vo.setPhone2("998-8923");
        vo.setAge((byte) 24);
        vo.setAddress1("서울 어딘가에... ");
        vo.setAddress2("살고 있겠지...");

        // 1. Connection 객체 생성.
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성
            String sql = "insert into person " +
                    "(userId, userPw, userName, userEmail, phone1, phone2, age, address1, address2) " +
                    "values ('" + vo.getUserId() + "','" + vo.getUserPw() + "','" + vo.getUserName() + "'," +
                    "'" + vo.getUserEmail() + "', '" + vo.getPhone1() + "', '" + vo.getPhone2() + "', " + vo.getAge()
                    + ", '" + vo.getAddress1() + "','" + vo.getAddress2() + "')";

            // String sql2 = "insert into person (userId, userPw, userName, userEmail,
            // phone1, phone2, age, address1,address2) "
            // +"values (?,?,?,?,?,?,?,?,?)";

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