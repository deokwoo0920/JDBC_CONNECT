package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO;

public class DBSelectTest2 {
    public static void main(String[] args) {
        // Select 동작 확인
        // 연결을 위한 정보 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // 1. Connection 객체 생성.
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성
            String sql = "select * from person";

            // 3.Statement 객체 생성, ResultSet 객체 선언(Query 결과 받을 객체)
            Statement stmt = conn.createStatement();
            ResultSet rs = null;
            // 4.SQL을 실행.
            // Select의 실행은 executeQuery(sql)을 사용함.
            // ResultSet 객체로 반환합니다.
            rs = stmt.executeQuery(sql);
            // 5. 결과 확인
            List<PersonVO> list = new ArrayList<>();
            while (rs.next()) { // rs.next() 반환값은 boolean
                PersonVO vo = new PersonVO(
                        rs.getString("userId"),
                        rs.getString("userPw"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("phone1"),
                        rs.getString("phone2"),
                        rs.getByte("age"),
                        rs.getString("address1"),
                        rs.getString("address2"));
                vo.setId(rs.getLong("id"));
                vo.setRegDate(rs.getTimestamp("regDate"));
                vo.setModifyDate(rs.getTimestamp("modifyDate"));
                list.add(vo);

            }
            System.out.println(list);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
