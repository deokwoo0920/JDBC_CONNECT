package repository;

import java.util.List;
import java.util.Optional;

import domain.orders.OrdersVO;

public interface Orders {

    // 주문 추가
    boolean insertOrder(OrdersVO order);

    // 주문 삭제(취소)
    boolean deleteOrder(long id);

    // 주문 수정
    boolean modifyOrder(OrdersVO order);

    // 주문 정보 출력
    // 1. 전체 주문 출력
    List<OrdersVO> ordersList();

    // 2. 부분 주문 출력(사용자Id)
    List<OrdersVO> ordersSearch(String userId);

    // 3. 날짜를 이용한 방법
    List<OrdersVO> ordersSearchDate(String date);

    // 4. 주문 번호를 이용하는 방법
    Optional<OrdersVO> ordersSearch(int orderNum);

}
