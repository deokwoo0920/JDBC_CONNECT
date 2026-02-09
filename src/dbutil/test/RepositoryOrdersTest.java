package dbutil.test;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import domain.orders.OrdersVO;
import repository.Orders;
import repository.OrdersDAOImpl;

public class RepositoryOrdersTest {

    // 테스트할 객체
    private static Orders ordersRepo = new OrdersDAOImpl();

    public static void main(String[] args) {
        OrdersVO order = OrdersVO.builder()
                .orderList("복음밥")
                .price(10000).orderNum(1)
                .userId("testuser")
                .build();

        if (insertTest(order))
            System.out.println("추가 성공");
        else
            System.out.println("추가 실패");

        OrdersVO searchResult = searchOrdersVO(1);
        System.out.println(searchResult);
        // 수정 처리
        searchResult.setOrderList("짬짜면");
        searchResult.setPrice(12000);
        if (modifyTest(searchResult))
            System.out.println("수정 성공!!");
        else
            System.out.println("수정 실패!");

        // 날짜 생성
        Calendar cal = Calendar.getInstance(); // Calender 객체 생성
        // Calender 객체에 날짜를 수정
        cal.set(2026, 1, 9, 0, 0, 0);
        // 전달할 날짜문자 형식을 지정
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 전달할 날짜 문자열 출력해서 확인
        System.out.println(sf.format(cal.getTime()));
        // 메서드에 날짜 문자열을 전달.
        List<OrdersVO> list = searchDate(sf.format(cal.getTime()));
        list.stream().forEach(System.out::println);
    }

    public static boolean insertTest(OrdersVO order) {
        return ordersRepo.insertOrder(order);
    }

    public static OrdersVO searchOrdersVO(int orderNum) {
        return ordersRepo.ordersSearch(orderNum).get();
    }

    // 수정은 먼저 select를 통한 객체를 얻어온 다음에 수정.
    public static boolean modifyTest(OrdersVO modify) {
        return ordersRepo.modifyOrder(modify);
    }

    // 날짜를 이용한 처리
    public static List<OrdersVO> searchDate(String date) {
        return ordersRepo.ordersSearchDate(date);
    }
}
