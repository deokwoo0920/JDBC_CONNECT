package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.stream.IntStream;

import domain.PersonVO2;

public class DBInsertTest4 {

    // DB연결을 위한 값을 생성
    static String url = "jdbc:mysql://localhost:3306/jdbc";
    static String user = "jdbcuser";
    static String password = "jdbcuser";

    public static void main(String[] args) {

        // Insert에 사용할 데이터를 생성.
        // PersonVO2 vo = new PersonVO2().builder()
        // .userId("user4").userPw("user4Pw")
        // .userName("user4").userEmail("user4@naver.com")
        // .age(30).build();

        // Stream을 사용하여 10개의 데이터를 추가하는 코드를 작성해 보세요.
        // PersonVO2 객체에 대한...
        IntStream.range(10, 60).forEach(i ->
        // 0 ~ 9 : 10
        insertDB(new PersonVO2().builder()
                .userId("user" + 5 + i).userPw("userPw" + i)
                .userName("user" + i + 5)
                .userEmail("user" + 5 + i + "@naver.com").age(30 + i)
                .phone1("02").phone2("99" + i % 9 + "-123" + i % 10)
                .address1("서울 마포구 성산동")
                .address2("23번지 " + (1 + i)).build()));

    }

    // InsertDB() 생성...
    // DB 매개변수를 받아서 insert 메서드 구현.
    public static void insertDB(PersonVO2 vo) {
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
            pstmt.setString(1, vo.getUserId());
            pstmt.setString(2, vo.getUserPw());
            pstmt.setString(3, vo.getUserName());
            pstmt.setString(4, vo.getUserEmail());
            pstmt.setString(5, vo.getPhone1());
            pstmt.setString(6, vo.getPhone2());
            pstmt.setByte(7, vo.getAge());
            pstmt.setString(8, vo.getAddress1());
            pstmt.setString(9, vo.getAddress2());
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
