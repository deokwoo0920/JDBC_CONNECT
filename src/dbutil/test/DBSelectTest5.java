package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonRe;
import domain.PersonVO2;

public class DBSelectTest5 {
    public static void main(String[] args) {
        // 연결을 위한 정보 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO2> list = new ArrayList<>();

        // DB 작업
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "select * from person where id <= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 10);

            // SQL 실행
            ResultSet rs = pstmt.executeQuery();
            // 결과 처리(ResultSet에 들어간 쿼리를 처리)
            while (rs.next()) { // rs.next() 반환값은 boolean
                // vo는 변경되지 않아요.
                PersonRe vo = new PersonRe(
                        rs.getLong("id"),
                        rs.getString("userId"),
                        rs.getString("userPw"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("phone1"),
                        rs.getString("phone2"),
                        rs.getByte("age"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getTimestamp("regDate"),
                        rs.getTimestamp("modifyDate"));
                // 불변 데이터(immutable)로 받는 역할을 하고,
                System.out.println("Record 객체" + vo);

                // Stream을 이용한 형변환... map
                // 불변 데이터 -> 가변 데이터(mutable)로 변경
                list.add(new PersonVO2().builder()
                        .id(vo.id()).userId(vo.userId()).userPw(vo.userPw())
                        .userName(vo.userName()).userEmail(vo.userEmail())
                        .phone1(vo.phone1()).phone2(vo.phone2())
                        .age(vo.age()).address1(vo.address1())
                        .address2(vo.address2()).regDate(vo.regDate())
                        .modifyDate(vo.modifyDate()).build());
            }
            list.stream().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
