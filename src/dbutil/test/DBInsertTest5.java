package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import domain.PersonRe;
import domain.PersonVO;

public class DBInsertTest5 {
    public static void main(String[] args) {
        // DB연결을 위한 값을 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // Record객체의 역할은 값을 변경없이 전달, 받는 역할.
        // Record객체는 setter가 없어요. 변경할 수 없어요(immutable).
        PersonRe vo = new PersonRe(
                0, "testuser10", "testuserPw",
                "testuser10", "testuser10@test.com",
                "032", "989-3394", (byte) 34, "인천",
                "인천 어딘가", null, null);
        // 변경할 수 없어요.
        System.out.println(vo.id()); // vo.id() - getter 메서드.

        // DB 작업(PreparedStatement)
        // 1. Connection 객체 생성
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성(PreparedStatement에 사용할)
            String sql = "insert into person "
                    + "(userId, userPw, userName, userEmail, phone1, phone2,"
                    + " age, address1, address2) values (?,?,?,?,?,?,?,?,?)";
            // 3. PreparedStatement 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 4. SQL에 ?에 대한 데이터를 추가
            // 왜? 인덱스 0번이 아닌 1번부터인가요?
            // 0에 SQL 구문이 있다고 생각하시면 됩니다.
            pstmt.setString(1, vo.userId());
            pstmt.setString(2, vo.userPw());
            pstmt.setString(3, vo.userName());
            pstmt.setString(4, vo.userEmail());
            pstmt.setString(5, vo.phone1());
            pstmt.setString(6, vo.phone2());
            pstmt.setByte(7, vo.age());
            pstmt.setString(8, vo.address1());
            pstmt.setString(9, vo.address2());
            // PreparedStatement 사용 장점 : 1) 편의성, 2)보안성(값 검증 처리)
            System.out.println(pstmt);

            // 5. SQL 실행. - 메서드에 매개변수 없어요.
            int result = pstmt.executeUpdate();
            if (result != 0) {
                System.out.println("레코드 추가 성공");
            } else {
                System.out.println("레코드 추가 실패");
            }

        } catch (Exception e) {
            System.out.println("DB작업 실패!");
            System.out.println(e.getMessage());
        }

    }
}
