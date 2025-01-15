package org.eDrink24.dto.dibs;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;


@Alias("DibDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class DibDTO {

    private Integer userId;
    private Integer productId;
    private String productName;
    private String category1;
    private String category2;
    private Integer promotionId;
    private Integer price;
    private String defaultImage;
    private String countDibs;
    private Boolean isCoupon;
    private Boolean isPoint;
    private LocalDate enrollDate;


    /*
    productId, productName, category1, category2, price, defaultImage, countDibs, enrollDate
);
     */

}
