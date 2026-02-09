package service.ordermanage;

import java.util.List;

import dto.OrderDTO;
import dto.UserDTO;

public interface Ordermanage {

    // 1. 회원 주문 처리
    // --1) 주문 생성
    boolean createOrder(OrderDTO order, UserDTO dto);

    // --2) 주문 수정
    boolean modifyOrder(OrderDTO order, UserDTO dto);

    // --3) 주문 삭제
    boolean deleteOrder(OrderDTO order, UserDTO dto);

    // --4) 주문 정보 확인
    // 회원 주문 정보 확인(자신의 userId를 활용해서 주문 확인)
    List<OrderDTO> findList(UserDTO dto);

    // 관리자 주문 정보 확인(모든 주문 목록을 확인, 특정 userId, 날짜...)
    List<OrderDTO> findAll();

    List<OrderDTO> findUserId(String userId);

    List<OrderDTO> findDate(String dateString);

    // 2. 비회원 주문 처리 (나중에 여러분이 생각해보세용. )

}
