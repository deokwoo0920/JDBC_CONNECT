package dbutil.test;

import java.util.Optional;

import domain.users.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

public class RepositoryTest {

    // 단위 테스트
    // 기능 정의된 인터페이스를 통한 기능 호출.
    private static Users repository = new UsersDAOImpl();

    public static void main(String[] args) {

        // 추가 테스트
        int testresult = repository.userAdd(
                UserVO.builder()
                        .userId("user202602")
                        .userPw("password")
                        .userName("testuser")
                        .userEmail("user202602@test.com")
                        .age((byte) 32)
                        .phone1("02")
                        .phone2("888-8888")
                        .address1("testuser의 주소1")
                        .address2("testuser의 주소2")
                        .build());
        if (testresult != 0) {
            System.out.println("데이터 추가 성공!!!");
        } else {
            System.out.println("데이터 추가 실패!!");
        }

        // 레코드 불러오는 search 기능 테스트
        Optional<UserVO> searchResult = repository.userSearch("user202602@test.com");
        if (searchResult.isPresent())
            System.out.println("search(email) 결과 성공");
        else
            System.out.println("search(email) 결과 성공");

        // 레코드 수정 (userMod)
        searchResult.get().setAddress2("수정된 주소"); // 내용 수정.

        testresult = repository.userMod(searchResult.get());
        if (testresult != 0)
            System.out.println("userMod() - 수정 성공!!!");
        else
            System.out.println("userMod() - 수정 실패!!!");

        // 레코드 검색(userId, username)
        UserVO search2 = repository.userSearch(
                "user202602",
                "testuser").get(0);

        if (search2 != null)
            System.out.println("search(userId, username) 결과 성공");
        else
            System.out.println("search(userId, username) 결과 성공");

        // 레코드 삭제
        testresult = repository.userDel(searchResult.get());
        if (testresult != 0)
            System.out.println("userDel() - 삭제 성공!!!");
        else
            System.out.println("userDel() - 삭제 실패!!!");

        // 레코드 전체 출력
        repository.userAll().stream().forEach(System.out::println);
    }

}
