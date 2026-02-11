package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;
import domain.orders.OrdersVO;

public class OrdersDAOImplOracle implements Orders {

    @Override
    public boolean deleteOrder(long id) {
        boolean result = false;
        // 주문 삭제 (1. 삭제할 주문을 읽어서, 2. id값을 넘겨주세요.)
        try (Connection conn = DBUtil.getConnection(DBUtil.ORACLE)) {
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

        boolean result = false;
        try (Connection conn = DBUtil.getConnection(DBUtil.ORACLE)) {
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
        try (Connection conn = DBUtil.getConnection(DBUtil.ORACLE)) {
            String sql = "update orders set orderList=?, orderNum=?, price=?, orderDate=sysdate "
                    + "where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderList());
            pstmt.setInt(2, order.getOrderNum());
            pstmt.setInt(3, order.getPrice());
            // pstmt.setTimestamp(4,
            // new Timestamp(System.currentTimeMillis()));
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
        try (Connection conn = DBUtil.getConnection(DBUtil.ORACLE)) {
            String sql = "select * from orders";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(OrdersVO.builder()
                        .id(rs.getLong("id"))
                        .orderList(rs.getString("orderList"))
                        .orderNum(rs.getInt("orderNum"))
                        .price(rs.getInt("price"))
                        .date(rs.getTimestamp("orderDate"))
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
        try (Connection conn = DBUtil.getConnection(DBUtil.ORACLE)) {
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
                        .date(rs.getTimestamp("orderDate"))
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

        try (Connection conn = DBUtil.getConnection(DBUtil.ORACLE)) {
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
                                .date(rs.getTimestamp("orderDate"))
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

        try (Connection conn = DBUtil.getConnection(DBUtil.ORACLE)) {
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
                        .date(rs.getTimestamp("orderDate"))
                        .userId(rs.getString("userId"))
                        .build());
            }

        } catch (Exception e) {
            System.out.println("DB 연동 실패 : " + e.getMessage());
        }

        return list;
    }

}
