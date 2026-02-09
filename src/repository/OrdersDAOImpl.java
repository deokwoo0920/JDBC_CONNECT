package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;
import domain.orders.OrdersVO;

public class OrdersDAOImpl implements Orders {

    @Override
    public boolean deleteOrder(long id) {
        boolean result = false;
        // 주문 삭제 (1. 삭제할 주문을 읽어서, 2. id값을 넘겨주세요.)
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "delete from orders where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            if (pstmt.executeUpdate() != 0)
                result = true;

        } catch (SQLException e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean insertOrder(OrdersVO order) {
        // 주문 추가
        // 1. 주문 정보(order)
        // 2. connection을 통한 SQL을 실행.
        // --1) connection 정보는 DBUtil.getConnection()
        // --2) SQL 생성
        // --3) PreparedStatement 객체 생성.
        // --4) SQL을 완성...
        // --5) 실행 결과 확인.
        boolean result = false;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "insert into orders (orderList, orderNum, price, userId) "
                    + "values(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderList());
            pstmt.setInt(2, order.getOrderNum());
            pstmt.setInt(3, order.getPrice());
            pstmt.setString(4, order.getUserId());

            if (pstmt.executeUpdate() != 0)
                result = true;

        } catch (SQLException e) {
            System.out.println("DB 처리 오류!! : " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean modifyOrder(OrdersVO order) {
        boolean result = false;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "update orders set orderList=?, orderNum=?, price=?, date=? "
                    + "where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderList());
            pstmt.setInt(2, order.getOrderNum());
            pstmt.setInt(3, order.getPrice());
            pstmt.setTimestamp(4,
                    new Timestamp(System.currentTimeMillis()));
            pstmt.setLong(5, order.getId());
            if (pstmt.executeUpdate() != 0)
                result = true;
        } catch (Exception e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<OrdersVO> ordersList() {
        // 주문 전체 목록 보기
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "select * from orders";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(OrdersVO.builder()
                        .id(rs.getLong("id"))
                        .orderList(rs.getString("orderList"))
                        .orderNum(rs.getInt("orderNum"))
                        .price(rs.getInt("price"))
                        .date(rs.getTimestamp("date"))
                        .userId(rs.getString("userId"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<OrdersVO> ordersSearch(String userId) {
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "select * from orders where userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(OrdersVO.builder()
                        .id(rs.getLong("id"))
                        .orderList(rs.getString("orderList"))
                        .orderNum(rs.getInt("orderNum"))
                        .price(rs.getInt("price"))
                        .date(rs.getTimestamp("date"))
                        .userId(rs.getString("userId"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return list;
    }

    @Override
    public Optional<OrdersVO> ordersSearch(int orderNum) {
        // 주문 번호를 통한 검색
        Optional<OrdersVO> order = null;

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "select * from orders where orderNum = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderNum);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = Optional.of(
                        OrdersVO.builder()
                                .id(rs.getLong("id"))
                                .orderList(rs.getString("orderList"))
                                .orderNum(rs.getInt("orderNum"))
                                .price(rs.getInt("price"))
                                .date(rs.getTimestamp("date"))
                                .userId(rs.getString("userId"))
                                .build());
            }

        } catch (Exception e) {
            System.out.println("DB 연동 실패 : " + e.getMessage());
        }

        return order;
    }

    @Override
    public List<OrdersVO> ordersSearchDate(String date) {
        List<OrdersVO> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "select * from orders where date > ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            // date는 SimpleDateFormat을 이용한 날짜 형식의 문자열로
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(OrdersVO.builder()
                        .id(rs.getLong("id"))
                        .orderList(rs.getString("orderList"))
                        .orderNum(rs.getInt("orderNum"))
                        .price(rs.getInt("price"))
                        .date(rs.getTimestamp("date"))
                        .userId(rs.getString("userId"))
                        .build());
            }

        } catch (Exception e) {
            System.out.println("DB 연동 실패 : " + e.getMessage());
        }

        return list;
    }

}
