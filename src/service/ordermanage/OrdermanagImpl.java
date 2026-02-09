package service.ordermanage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.orders.OrdersVO;
import domain.users.UserVO;
import dto.OrderDTO;
import dto.UserDTO;
import repository.Orders;
import repository.OrdersDAOImpl;
import repository.Users;
import repository.UsersDAOImpl;

public class OrdermanagImpl implements Ordermanage {

    // 서비스 계층에 작업을 위해 필요한 객체들...
    Users userRepository = new UsersDAOImpl();
    Orders orderRepository = new OrdersDAOImpl();

    @Override
    public boolean createOrder(OrderDTO order, UserDTO userDTO) {
        // 주문 생성
        OrdersVO newOrder = OrdersVO.builder()
                .orderList(order.getOrderList())
                .orderNum(order.getOrderNum())
                .price(order.getPrice())
                .userId(userDTO.getUserId())
                .build();

        return orderRepository.insertOrder(newOrder);
    }

    @Override
    public boolean deleteOrder(OrderDTO order, UserDTO userDTO) {
        // 삭제 작업은 Orders 테이블의 id로 삭제를 진행.
        // 사용자 확인 작업...
        Optional<UserVO> userInfo = userRepository.userSearch(userDTO.getUserEmail());
        if (userInfo.isPresent()) {
            // userDTO.getUserPw() 는 삭제를 위해 입력한 패스워드를 저장.
            // userInfo.get().getUSerPw() 는 DB에 있는 사용자의 패스워드
            if (userDTO.getUserPw().equals(userInfo.get().getUserPw())) {
                return orderRepository.deleteOrder(order.getId());
            } else
                return false;
        } else
            return false; // 등록된 사용자가 없는 경우.

    }

    @Override
    public List<OrderDTO> findList(UserDTO dto) {
        // 사용자가 주문한 주문 리스트 출력
        // 1. 사용자 정보 : userId를 불러서.
        // 2. orderRepository.ordersSearch(userId)
        List<OrdersVO> ordersVOList = orderRepository.ordersSearch(dto.getUserId());
        List<OrderDTO> ordersList = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OrdersVO vo : ordersVOList) {
            ordersList.add(OrderDTO.builder()
                    .id(vo.getId())
                    .orderList(vo.getOrderList())
                    .orderNum(vo.getOrderNum())
                    .price(vo.getPrice())
                    .date(sf.format(vo.getDate()))
                    .userId(vo.getUserId())
                    .build());
        }
        return ordersList;
    }

    @Override
    public List<OrderDTO> findAll() {
        // 관리자 입장에서 모든 주문 내역을 가져오기
        return null;
    }

    @Override
    public List<OrderDTO> findDate(String dateString) {
        // 관리자 입장에서 날짜를 통한 주문 내역
        return null;
    }

    @Override
    public List<OrderDTO> findUserId(String userId) {
        // 특정 사용자의 주문 내역.
        return null;
    }

    @Override
    public boolean modifyOrder(OrderDTO order, UserDTO userDTO) {
        // 회원정보와 order 정보를 통한 수정 처리...
        Optional<UserVO> user = userRepository.userSearch(userDTO.getUserEmail());

        if (user.isPresent()) {
            // OrderDTO -> OrderVO
            OrdersVO orderVO = OrdersVO.builder()
                    .id(order.getId())
                    .userId(order.getUserId())
                    .orderList(order.getOrderList())
                    .orderNum(order.getOrderNum())
                    .price(order.getPrice())
                    .build();
            return orderRepository.modifyOrder(orderVO);
        }
        return false;
    }

}
