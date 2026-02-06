package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import domain.users.UserVO;

public class UsersDAOImpl implements Users {

    // 멤버 변수 선언.
    private String url = "jdbc:mysql://localhost:3306/jdbc";
    private String dbuser = "jdbcuser";
    private String password = "jdbcuser";

    @Override
    public int userAdd(UserVO user) {
        // 부탁해요. 만들어주세요 ~~ ^^, 성공시 1아닌 정수, 실패시 0
        // insert 작업

        int result = 0; // 결과에 대한 반환 값 처리를 위한 변수

        try (Connection conn = DriverManager.getConnection(url, dbuser, password)) {

            String sql = "insert into person "
                    + "(userId, userPw, userName, userEmail, phone1, phone2, age, "
                    + "address1, address2) values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPw());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserEmail());
            pstmt.setString(5, user.getPhone1());
            pstmt.setString(6, user.getPhone2());
            pstmt.setByte(7, user.getAge());
            pstmt.setString(8, user.getAddress1());
            pstmt.setString(9, user.getAddress2());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB작업 실패");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public List<UserVO> userAll() {
        // slq select 전체
        List<UserVO> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, dbuser, password)) {

            // SQL
            String sql = "select * from person";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(UserVO.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getString("userId"))
                        .userPw(rs.getString("userPw"))
                        .userName(rs.getString("userName"))
                        .userEmail(rs.getString("userEmail"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .age(rs.getByte("age"))
                        .address1(rs.getString("address1"))
                        .address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public int userDel(UserVO user) {
        // sql delete
        int result = 0;
        try (Connection conn = DriverManager.getConnection(url, dbuser, password)) {
            String sql = "delete from person where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, user.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int userMod(UserVO befor, UserVO after) {
        // sql update
        int result = 0;
        try (Connection conn = DriverManager.getConnection(url, dbuser, password)) {

            String sql = "update person set userId=?, userPw=?, userName=?," +
                    "userEmail=?, phone1=?, phone2=?, age=?, address1=?, address2=?" +
                    ", modifyDate=? where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, after.getUserId());
            pstmt.setString(2, after.getUserPw());
            pstmt.setString(3, after.getUserName());
            pstmt.setString(4, after.getUserEmail());
            pstmt.setString(5, after.getPhone1());
            pstmt.setString(6, after.getPhone2());
            pstmt.setByte(7, after.getAge());
            pstmt.setString(8, after.getAddress1());
            pstmt.setString(9, after.getAddress2());
            pstmt.setTimestamp(10,
                    new Timestamp(System.currentTimeMillis()));
            pstmt.setLong(11, befor.getId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB 동작 에러!!");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public List<UserVO> userSearch(String userId, String userName) {
        // sql select , where userId, userName
        List<UserVO> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, dbuser, password)) {

            // SQL
            String sql = "select * from person where userId=? and userName=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(UserVO.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getString("userId"))
                        .userPw(rs.getString("userPw"))
                        .userName(rs.getString("userName"))
                        .userEmail(rs.getString("userEmail"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .age(rs.getByte("age"))
                        .address1(rs.getString("address1"))
                        .address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public List<UserVO> userSearch(String userEmail) {
        // sql select, where email
        List<UserVO> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, dbuser, password)) {

            // SQL
            String sql = "select * from person where userEmail=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(UserVO.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getString("userId"))
                        .userPw(rs.getString("userPw"))
                        .userName(rs.getString("userName"))
                        .userEmail(rs.getString("userEmail"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .age(rs.getByte("age"))
                        .address1(rs.getString("address1"))
                        .address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return list;
    }

}
