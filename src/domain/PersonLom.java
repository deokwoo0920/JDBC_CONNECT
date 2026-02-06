package domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Lombok에서 주로 사용하는 어노테이션... 
@Setter/@Getter : Getter/Setter 설정
@ToString : toString 메서드 생성. 
@EqualsAndHashCode  : equals()와 hashCode() 메서드를 자동 구현.
@Data : getter,setter,toString, equalsAndHashCode 어노테이션 기능을 모두 포함.
@Builder : 복잡 객체 생성을 안정화는 Builder 패턴을 자동 생성.

@AllArgsConstructor : 멤버변수 전체를 사용하는 생성자를 만들어 줌. 
@NoArgsConstructor : 기본 생성자를 만들어 줌. 

주의점 : 
  1. 무분별한 어노테이션의 사용으로 다른 기능과 연결되어 의도하지 않는 
    동작을 할 수 있음. (Data 어노테이션은 자중하는 것이 좋아요.)
  2. @Builder만 사용하면 기본 생성자는 생성되지 않음. 
    기본 생성자가 필요한 경우에는 @NoArgsConstructor 사용하는 것이 일반적입니다.
    @AllArgsConstructor 는 위에 @NoArgsConstructor를 사용하는 경우에 같이 사용. 
  3. Lombok만 의존하면 문제 발생했을 때, 대처하기 어렵습니다. Lombok을 사용하지 
    못하는 경우에 대해서 대비할 필요가 있음. 

*/
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonLom {

    // 멤버 변수 선언
    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String phone1;
    private String phone2;
    private byte age;
    private String address1;
    private String address2;
    private Timestamp regDate;
    private Timestamp modifyDate;

}
