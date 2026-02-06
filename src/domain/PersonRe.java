package domain;

import java.sql.Timestamp;

// record 객체 선언 - class 대신에 record 사용. (java14 이후)
// 클래스 선언시에 없던 "()" 사용합니다. 
// 1. 간결한 객체 정의
// 2. 메서드 자동 생성
// 3. 생성자 자동 생성(Builder 객체 사용)
// 4. 불변성(***) - 객체 안에 있는 멤버변수들은 final의 속성 가지고 있다. 
public record PersonRe(
        // 멤버 변수 선언 위치
        long id, String userId, String userPw, String userName, String userEmail,
        String phone1, String phone2, byte age, String address1, String address2,
        Timestamp regDate, Timestamp modifyDate) {

    // 이하 Builder 클래스 사용 코드.
    public Builder builder() {
        return new Builder();
    }

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
        public PersonRe build() {
            return new PersonRe(id, userId, userPw, userName, userEmail, phone1,
                    phone2, age, address1, address2, regDate, modifyDate);
        }
    }

}
