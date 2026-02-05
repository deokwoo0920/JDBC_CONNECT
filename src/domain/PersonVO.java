package domain;

import java.sql.Timestamp;

public class PersonVO {

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
    public PersonVO() {
    }

    // 빌드 생성자
    public PersonVO(String userId, String userPw, String userName, String userEmail, String phone1, String phone2,
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
