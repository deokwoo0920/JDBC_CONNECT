package dbutil.test;

import java.util.List;
import java.util.Optional;

import domain.users.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

public class RepositoryTest {

    private static Users repository = new UsersDAOImpl();

    public static void main(String[] args) {

        System.out.println("====".repeat(5) + "추가 테스트" + "====".repeat(5));
        // test data
        UserVO testData = UserVO.builder()
                .userId("test111").userName("test111")
                .userPw("test11").userEmail("test@test22.com")
                .build();

        if (repository.userAdd(testData) != 0) {
            System.out.println("userAdd 성공");
        } else {
            System.out.println("userAdd 실패");
        }
        System.out.println("====".repeat(5) + "전체 출력 테스트" + "====".repeat(5));
        // userAll()
        List<UserVO> list = repository.userAll();
        list.stream().forEach(System.out::println);

        // userMod()
        System.out.println("====".repeat(5) + "수정 테스트" + "====".repeat(5));
        UserVO mod = UserVO.builder()
                .userId("test111-mod").userName("test111-mod")
                .userPw("test11-mod").userEmail("test@test22.com")
                .age((byte) 30).phone1("02").phone2("989-2334")
                .build();
        if (repository.userMod(repository.userSearch("test@test22.com").get(0), mod) != 0)
            System.out.println("수정 성공");
        else
            System.out.println("수정 실패");
        System.out.println("====".repeat(5) + "검색 테스트" + "====".repeat(5));
        list = repository.userSearch("test111-mod", "test111-mod");
        Optional<List<UserVO>> test = Optional.of(list);
        if (test.isEmpty())
            System.out.println("결과 없음");
        else
            System.out.println("찾기 결과 : " + test.get());

        if (repository.userDel(repository.userSearch("test@test22.com").get(0)) != 0)
            System.out.println("삭제 성공");
        else
            System.out.println("삭제 실패");

    }

}
