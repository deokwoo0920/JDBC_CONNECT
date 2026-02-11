package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;
import domain.users.UserVO;

public class UsersDAOImplMariadb implements Users {
    @Override
    public int userAdd(UserVO user) {
        // 부탁해요. 만들어주세요 ~~ ^^, 성공시 1아닌 정수, 실패시 0
        // insert 작업

        int result = 0; // 결과에 대한 반환 값 처리를 위한 변수

        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {

            String sql = "insert into users "
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

        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {

            // SQL
            String sql = "select * from users";
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
        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "delete from users where id = ?";

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
    public int userMod(UserVO after) {
        // sql update
        int result = 0;
        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {

            String sql = "update users set userPw=?, userName=?," +
                    "userEmail=?, phone1=?, phone2=?, age=?, address1=?, address2=?" +
                    ", modifyDate=now() where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, after.getUserPw());
            pstmt.setString(2, after.getUserName());
            pstmt.setString(3, after.getUserEmail());
            pstmt.setString(4, after.getPhone1());
            pstmt.setString(5, after.getPhone2());
            pstmt.setByte(6, after.getAge());
            pstmt.setString(7, after.getAddress1());
            pstmt.setString(8, after.getAddress2());
            // pstmt.setTimestamp(9,
            // new Timestamp(System.currentTimeMillis()));
            pstmt.setLong(9, after.getId());
            System.out.println(pstmt);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB 동작 에러!!");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public Optional<UserVO> login(String userId, String userPw) {
        // sql select , where userId, userName
        Optional<UserVO> user = null;

        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {

            // SQL
            String sql = "select * from users where userId=? and userPw=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user = Optional.of(UserVO.builder()
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

        return user;
    }

    @Override
    public Optional<UserVO> userSearch(String userEmail) {
        // sql select, where email
        Optional<UserVO> result = null;

        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {

            // SQL
            String sql = "select * from users where userEmail=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = Optional.of(UserVO.builder()
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

        return result;
    }
}
