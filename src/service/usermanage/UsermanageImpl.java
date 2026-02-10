package service.usermanage;

import java.util.ArrayList;
import java.util.List;

import domain.users.UserVO;
import dto.UserDTO;
import repository.Users;
import repository.UsersDAOImpl;

public class UsermanageImpl implements Usermanage {

    // DB 작업을 할 수 있는 객체를 호출 작업 진행...
    // 인터페이스를 통한 객체 호출...
    Users userRepository = new UsersDAOImpl();

    @Override
    public List<UserDTO> searchAll() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserVO> userVOList = userRepository.userAll();
        for (UserVO vo : userVOList) {
            UserDTO dto = UserDTO.toUserDTO(vo);
            userDTOList.add(dto);
        }
        return userDTOList;
    }

    @Override
    public UserDTO searchOne(String userEmail) {
        UserVO vo = userRepository.userSearch(userEmail).get();
        UserDTO dto = UserDTO.toUserDTO(vo);
        return dto;
    }

    @Override
    public boolean userDelete(UserDTO userDTO) {
        // UserDTO -> UserVO
        UserVO userVO = UserDTO.toUserVO(userDTO);

        if (userRepository.userDel(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userModify(UserDTO userDTO) {
        UserVO userVO = UserDTO.toUserVO(userDTO);
        // System.out.println("서비스(userModify) : " + userVO);
        if (userRepository.userMod(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userRegister(UserDTO userDTO) {
        UserVO userVO = UserDTO.toUserVO(userDTO);
        if (userRepository.userAdd(userVO) != 0)
            return true;
        else
            return false;

    }

    @Override
    public UserDTO login(String userId, String userPw) {
        UserDTO userDTO = UserDTO.toUserDTO(
                userRepository.login(userId, userPw).get());
        return userDTO;
    }

}
