package org.eDrink24.dto.product;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("DetailProductDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailProductDTO {

    private Integer productId;
    private String productName;
    private String category1;
    private String category2;
    private Integer price;
    private String defaultImage;
    private String countDibs;
    private String detailImage;

}
