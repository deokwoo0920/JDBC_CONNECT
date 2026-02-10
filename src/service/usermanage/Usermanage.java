package service.usermanage;

import java.util.List;

import dto.UserDTO;

public interface Usermanage {

    // 1. 사용자 등록
    boolean userRegister(UserDTO userDTO);

    // 2. 사용자 수정
    boolean userModify(UserDTO userDTO);

    // 3. 사용자 검색(특정 사용자 검색, 전체 사용자 검색)
    // 특정 사용자
    UserDTO searchOne(String userEmail);

    // 전체 사용자
    List<UserDTO> searchAll();

    // 4. 사용자 삭제
    boolean userDelete(UserDTO userDTO);

    // 5. 사용자 로그인
    UserDTO login(String userId, String userPw);

}
