package domain;

import java.sql.Timestamp;

public class PersonVO2 {

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

    // 생성자
    // 기본 생성자
    public PersonVO2() {
    }

    // (추가한 내용)
    // Builder를 사용하는 생성자 구성.
    public PersonVO2(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.userPw = builder.userPw;
        this.userName = builder.userName;
        this.userEmail = builder.userEmail;
        this.phone1 = builder.phone1;
        this.phone2 = builder.phone2;
        this.age = builder.age;
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.regDate = builder.regDate;
        this.modifyDate = builder.modifyDate;
    }

    // 필드 생성자(id, regDate, modifyDate - DB가 생성)
    public PersonVO2(String userId, String userPw, String userName, String userEmail, String phone1, String phone2,
            byte age, String address1, String address2) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.age = age;
        this.address1 = address1;
        this.address2 = address2;
    }

    // (추가한 내용)
    // Builder는 static 내부 클래스
    // 빌더의 역할을 값을 받아서 PersonVO2객체 반환 작업을 객체로 사용.
    public static class Builder {
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

        // 기본 생성자
        public Builder() {
        }

        public Builder id(long id) {
            this.id = id; // 생성된 객체 id값을 매개변개 id 대입
            return this; // 객체를 넘기겠습니다.
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder userPw(String userPw) {
            this.userPw = userPw;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder phone1(String phone1) {
            this.phone1 = phone1;
            return this;
        }

        public Builder phone2(String phone2) {
            this.phone2 = phone2;
            return this;
        }

        public Builder age(int age) {
            this.age = (byte) age;
            return this;
        }

        public Builder address1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Builder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder regDate(Timestamp regDate) {
            this.regDate = regDate;
            return this;
        }

        public Builder modifyDate(Timestamp modifyDate) {
            this.modifyDate = modifyDate;
            return this;
        }

        // 메서드 : build()
        public PersonVO2 build() {
            return new PersonVO2(this); // Builder 내부클래스를 받아서 처리하는 생성자
        }

    }

    // (추가한 내용)
    // builder() - 반환 타입 Builder
    public Builder builder() {
        return new Builder();
    }

    // 메서드(Getter, Setter)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "PersonVO [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
                + ", phone1=" + phone1 + ", phone2=" + phone2 + ", age=" + age + ", address1=" + address1
                + ", address2=" + address2 + ", regDate=" + regDate + ", modifyDate=" + modifyDate + "]";
    }

}
