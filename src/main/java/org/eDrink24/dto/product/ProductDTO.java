package org.eDrink24.dto.product;

import org.apache.ibatis.type.Alias;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.eDrink24.dto.review.ReviewDTO;

import java.time.LocalDate;


@Alias("ProductDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ProductDTO {

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
    private Double rating;


}
