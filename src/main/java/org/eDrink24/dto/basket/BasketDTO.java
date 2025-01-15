package org.eDrink24.dto.basket;

import lombok.*;
import org.apache.ibatis.type.Alias;
import java.util.List;


@Alias("BasketDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BasketDTO {

    private Integer basketId;
    private Integer userId;
    private List<BasketItemDTO> items;

}