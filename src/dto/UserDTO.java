package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import domain.users.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "userPw", "regDate", "modifyDate" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String phone1;
    private String phone2;
    private int age; // byte -> int
    private String address1;
    private String address2;
    // 다른 타입의 데이터를 바꿔질 수 있다는 것을 보여주기 위해서
    // Timestamp -> String
    private String regDate;
    private String modifyDate;

    public static UserVO toUserVO(UserDTO userDTO) {
        return UserVO.builder()
                .id(userDTO.id)
                .userId(userDTO.userId)
                .userPw(userDTO.userPw)
                .userPw(userDTO.userPw)
                .userEmail(userDTO.userEmail)
                .phone1(userDTO.phone1)
                .phone2(userDTO.phone2)
                .age(toChangeAge(userDTO.age))
                .address1(userDTO.address1)
                .address2(userDTO.address2)
                .regDate(toDateTimestamp(userDTO.getRegDate()))
                .modifyDate(toDateTimestamp(userDTO.getModifyDate()))
                .build();
    }

    public static UserDTO toUserDTO(UserVO userVO) {
        return UserDTO.builder()
                .id(userVO.getId())
                .userId(userVO.getUserId())
                .userPw(userVO.getUserPw())
                .userPw(userVO.getUserPw())
                .userEmail(userVO.getUserEmail())
                .phone1(userVO.getPhone1())
                .phone2(userVO.getPhone2())
                .age(toChangeAge(userVO.getAge()))
                .address1(userVO.getAddress1())
                .address2(userVO.getAddress2())
                .regDate(toTimestampDateString(userVO.getRegDate()))
                .modifyDate(toTimestampDateString(userVO.getModifyDate()))
                .build();
    }

    private static byte toChangeAge(int age) {
        if (age >= 0 && age <= 127)
            return (byte) age;
        else
            return 0;
    }

    private static Timestamp toDateTimestamp(String dataString) {
        String[] arrDayTime = dataString.split(" ");
        String[] day = arrDayTime[0].split("-");
        String[] time = arrDayTime[1].split(":");
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]),
                Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
        return new Timestamp(cal.getTimeInMillis());
    }

    private static String toTimestampDateString(Timestamp time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(time);
    }

}
