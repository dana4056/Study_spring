package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

//@Data  // 핵심 도메인 모델에 사용하기 위험함
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;    // int가 아니라 Integer로 한 이유는 값이 없을 수도(null)있기 때문에
    private Integer quantity; // int는 null이 안들어감

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
