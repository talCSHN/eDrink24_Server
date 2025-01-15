package org.eDrink24.dto.basket;

import lombok.*;
import org.apache.ibatis.type.Alias;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasketItemDTO {

    private Integer itemId;
    private Integer basketId;
    private Integer productId;
    private String defaultImage;
    private String productName;
    private Integer price;
    private Integer basketQuantity;
}
