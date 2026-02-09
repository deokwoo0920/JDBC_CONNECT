package domain.orders;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersVO {

    private long id;
    private String orderList;
    private int orderNum;
    private int price;
    private Timestamp date;
    private String userId;

}
