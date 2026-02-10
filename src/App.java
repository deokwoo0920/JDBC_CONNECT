import java.util.List;
import java.util.Scanner;

import controller.OrderProgramController;
import dto.OrderDTO;
import dto.UserDTO;

public class App {

    // 사용자 입력을 위한 도구
    private static Scanner scanner = new Scanner(System.in, "cp949");
    // Controller 레이어
    private static OrderProgramController controller = new OrderProgramController();

    // 로그인 정보를 저장한 변수
    private static UserDTO userInfo = null; // 로그인하면 정보를 추가, 로그아웃하면 null로 변경.

    public static void main(String[] args) throws Exception {
        System.out.println("[고객 주문 관리 프로그램]");
        menu();
    }

    public static void menu() throws Exception { // 메인 메뉴(View)
        while (true) {
            System.out.println("1. 회원 관리");
            System.out.println("2. 주문 관리");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 가입, 로그인 정보를 출력하는 메뉴 메서드 호출
                    System.out.println("회원 관리 하위 메뉴");
                    userManageMenu();
                    break;
                case '2':
                    // 주문 처리(회원), 주문처리(비회원)
                    System.out.println("주문 관리 하위 메뉴");
                    userOrder();
                    break;
                case '0':
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return; // 프로세스 종료
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해주세요.");
                    break;
            }
        }

    }

    public static void userManageMenu() {

        while (true) {
            System.out.println("1) 회원 가입");
            System.out.println("2) 로그인");
            System.out.println("0) 메인으로 이동");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 가입 정보 입력 메서드 호출
                    System.out.println("회원 가입 정보 처리 메서드");
                    joinUser();
                    break;
                case '2':
                    // 로그인 처리 메서드 호출
                    System.out.println("로그인 처리 메서드");
                    login();
                    break;
                case '0':
                    System.out.println("메인으로 이동합니다. ");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    public static void joinUser() {

        while (true) {
            System.out.println("[회원 가입 정보 처리]");
            System.out.print("사용자 ID를 입력하세요 : ");
            String userId = scanner.next();
            System.out.print("사용자 PW를 입력하세요 : ");
            String userPd = scanner.next();
            scanner.nextLine(); // scanner 버퍼 정리
            System.out.print("사용자 이름를 입력하세요 : ");
            String userName = scanner.nextLine();
            System.out.print("사용자 이메일을 입력하세요 : ");
            String userEmail = scanner.next();
            scanner.nextLine();
            System.out.print("사용자 전화번호를 입력하세요 : ");
            String userPhone = scanner.nextLine();
            System.out.print("사용자 나이를 입력하세요 : ");
            int userAge = scanner.nextInt();
            scanner.nextLine();
            System.out.print("사용자 주소1를 입력하세요(번지) : ");
            String userAddress1 = scanner.nextLine();
            System.out.print("사용자 주소2를 입력하세요(상세주소) : ");
            String userAddress2 = scanner.nextLine();
            System.out.println("[입력한 정보를 확인]");
            System.out.println("사용자 ID : " + userId);
            System.out.println("사용자 PW : " + userPd);
            System.out.println("사용자 이름 : " + userName);
            System.out.println("사용자 이메일 : " + userEmail);
            System.out.println("사용자 전화번호 : " + userPhone);
            System.out.println("사용자 나이 : " + userAge);
            System.out.println("사용자 주소1 : " + userAddress1);
            System.out.println("사용자 주소2 : " + userAddress2);
            System.out.print("입력한 정보를 회원 가입하시겠습니까?(y/n)");
            char done = scanner.nextLine().toLowerCase().charAt(0);
            System.out.println(done);
            if (done == 'y') {
                // 회원 가입 처리(controller)
                boolean status = controller.join(userId, userPd, userName, userEmail,
                        userPhone, userAge, userAddress1, userAddress2);
                if (status)
                    return; // 회원 가입 메뉴 나가고, 실패면, 다시 while문으로
                else
                    System.out.println("회원 가입 실패했습니다.");
            }
        }
    }

    public static void login() {
        while (true) {
            System.out.println("[회원 로그인 처리]");
            System.out.print("사용자 ID를 입력하세요 : ");
            String userId = scanner.next();
            System.out.print("사용자 PW를 입력하세요 : ");
            String userPw = scanner.next();
            System.out.print("로그인 하시겠습니까? y/n ");
            scanner.nextLine();
            char done = scanner.nextLine().toLowerCase().charAt(0);
            if (done == 'y') {
                // controller를 통한 login 작업
                // 로그인 성공시 : 정보 확인, 수정, 탈퇴 메뉴를 연결 - 메서드
                // 로그인 실패시 : 아이디 또는 패스워드가 다릅니다.
                // 다시 입력 반복(계속여부확인)
                userInfo = controller.login(userId, userPw);
                // userInfo에 로그인 정보를 저장하고, userInfo의 내용이 있는지 검증
                // userInfo는 로그인 상태 정보를 저장.
                if (!userInfo.getUserId().isEmpty()) {
                    userManage();
                } else {
                    System.out.println("아이디 또는 패스워드가 다릅니다.");
                }
            }
            System.out.print("이전 메뉴로 이동하겠습니까? (y/n) ");
            done = scanner.nextLine().toLowerCase().charAt(0);
            if (done == 'y') {
                return;
            }

        }
    }

    public static void userManage() {
        while (true) {
            // controller를 통해서 정보를 입력
            System.out.println("1) 로그인 정보 확인");
            System.out.println("2) 로그인 정보 수정");
            System.out.println("3) 회원 탈퇴");
            System.out.println("0) 이전 메뉴로 이동");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 정보 출력
                    System.out.println("[회원 정보 확인]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    System.out.println("사용자 PW : " + "*******");
                    System.out.println("사용자 이름 : " + userInfo.getUserName());
                    System.out.println("사용자 이메일 : " + userInfo.getUserEmail());
                    System.out.println("사용자 전화번호 : "
                            + userInfo.getPhone1() + "-" + userInfo.getPhone2());
                    System.out.println("사용자 나이 : " + userInfo.getAge());
                    System.out.println("사용자 주소1 : " + userInfo.getAddress1());
                    System.out.println("사용자 주소2 : " + userInfo.getAddress2());
                    break;
                case '2':
                    // 회원 정보 출력 후 수정
                    System.out.println("[회원 정보 확인 수정]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    // 원래 패스워드 수정은 별로의 로직으로 구성해야 합니다.
                    System.out.printf("사용자 PW(%s) : ", userInfo.getUserPw());
                    String userPw = scanner.next();
                    scanner.nextLine();
                    System.out.printf("사용자 이름(%s) : ", userInfo.getUserName());
                    String userName = scanner.nextLine();
                    System.out.printf("사용자 이메일(%s) : ", userInfo.getUserEmail());
                    String userEmail = scanner.nextLine();
                    System.out.printf("사용자 전화번호(%s-%s) : ",
                            userInfo.getPhone1(), userInfo.getPhone2());
                    String userPhone = scanner.nextLine();
                    System.out.printf("사용자 나이(%d) : ", userInfo.getAge());
                    int userAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("사용자 주소1(%s) : ", userInfo.getAddress1());
                    String userAddress1 = scanner.nextLine();
                    System.out.printf("사용자 주소2(%s) : ", userInfo.getAddress2());
                    String userAddress2 = scanner.nextLine();
                    boolean status = controller.userModify(userInfo.getId(), userInfo.getUserId(), userPw, userName,
                            userEmail, userPhone, userAge, userAddress1, userAddress2);
                    if (status) {
                        System.out.println("회원 정보가 수정되었습니다.");
                        // 회원 정보 갱신 처리
                        userInfo = controller.userInfo(userEmail);
                    } else
                        System.out.println("회원 정보 수정 실패했습니다.");
                    break;
                case '3':
                    // 회원 정보 출력 후 삭제
                    System.out.println("[회원 정보 확인]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    System.out.print("회원 탈퇴하시겠습니까?(y/n)");
                    char done = scanner.nextLine().toLowerCase().charAt(0);
                    if (done == 'y') {
                        System.out.print("사용자 PW() : ");
                        String pw = scanner.next();
                        scanner.nextLine(); // 버퍼문제... 위에서 choice에 에러는 여기
                        // 회원 정보 넣어서 보낼 Pw
                        // 회원 정보 삭제를 위한 확인 처리할 패스워드. 변경해서
                        System.out.println("입력한 패스워드 : " + pw);
                        userInfo.setUserPw(pw);
                        System.out.println("userInfo의 패스워드 : " + userInfo.getUserPw());
                        status = controller.revokeUser(userInfo);
                        if (status) {// 회원 탈퇴
                            System.out.println("회원 탈퇴 성공했습니다.");
                            // 1. userInfo을 정리 => null
                            userInfo = null;
                            // 2. 이전 메뉴로 이동.
                            return;
                        } else {// 회원 탈퇴 실패
                            System.out.println("회원 탈퇴 실패했습니다.");
                        }
                    }
                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    public static void userOrder() {
        while (true) {
            System.out.println("1) 주문처리(회원)");
            System.out.println("2) 주문처리(비회원-X)");
            System.out.println("0) 이전 메뉴");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    // 주문 생성, 조회, 수정, 삭제
                    System.out.println("[회원 주문 작업]");
                    orderManage();
                    break;
                case '2':
                    System.out.println("작업X");
                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    public static void orderManage() {
        if (userInfo == null) {
            System.out.println("로그인한 사용자만 사용할 수 있어요. ");
            return;
        }
        boolean status = false;
        List<OrderDTO> list = null;
        // 주문 생성, 조회, 수정, 삭제 (회원인 경우 작업)
        while (true) {
            System.out.println("1) 주문 생성");
            System.out.println("2) 주문 조회");
            System.out.println("3) 주문 수정/삭제");
            System.out.println("0) 이전 메뉴로");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println("[주문 생성]");
                    System.out.print("메뉴 입력(list) :");
                    String orderList = scanner.nextLine();
                    System.out.print("가격 :");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    // controller에서 메뉴 처리...
                    status = controller.createOrder(userInfo, orderList, price);
                    if (status)
                        System.out.println("주문 생성 성공");
                    else
                        System.out.println("주문 생성 실패");
                    break;
                case '2':
                    // 주문정보 읽어오기
                    // 반복문으로 처리. stream 또는 for문
                    System.out.println("주문 내역 읽어오기");
                    list = controller.getOrders(userInfo);
                    list.stream().forEach(System.out::println);
                    break;
                case '3':
                    // 주문 리스트를 확인 인덱스 번호 입력
                    System.out.println("주문 삭제하기 ");
                    System.out.println("=============== 주문한 리스트 출력 =============");
                    list = controller.getOrders(userInfo);
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ":" + list.get(i));
                    }
                    // 주문 삭제 처리
                    System.out.print("삭제할 번호(index)를 입력하세요 : ");
                    int idx = scanner.nextInt() - 1;
                    scanner.nextLine();
                    // 삭제 작업 진행.
                    status = controller.removeOrder(userInfo, list.get(idx));
                    if (status) {
                        System.out.println("삭제 성공");
                    } else {
                        System.out.println("삭제 실패");
                    }
                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 선택하세요.");
                    break;
            }

        }
    }

}
