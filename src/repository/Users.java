package repository;

import java.util.List;
import java.util.Optional;

import domain.users.UserVO;

public interface Users {

    // 레코드 추가
    int userAdd(UserVO user);

    // 레코드 수정
    int userMod(UserVO userVO);

    // 레코드 삭제
    int userDel(UserVO user);

    // 레코드 조회
    // 1. 전체 조회
    List<UserVO> userAll();

    // 2. 조건 조회 - (userid(unique), name),
    // login 처리를 위한 값으로 진행
    Optional<UserVO> login(String userId, String userPw);

    // email(unique처리 안해도 unique)
    Optional<UserVO> userSearch(String userEmail);

}
