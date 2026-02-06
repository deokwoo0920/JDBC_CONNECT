import domain.PersonLom;

public class App {
    public static void main(String[] args) throws Exception {
        // lombok 테스트
        PersonLom lombok1 = new PersonLom(); // @NoArgsConstructor
        // @AllArgsConstructor
        PersonLom lombok2 = new PersonLom(0, "1", "1", "1", "1", "1", "1", (byte) 0, "1", "1", null, null);

        // @Setter
        lombok1.setAddress1("주소1");

        // @Getter
        lombok1.getAddress1();

        // @Builder 사용시
        PersonLom lomboktest = new PersonLom().builder()
                .address1("주소1").build();

        System.out.println("Hello, World!");
    }
}
