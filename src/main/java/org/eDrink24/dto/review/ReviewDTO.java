package org.eDrink24.dto.review;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Alias("ReviewDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ReviewDTO {

    private Integer userId;
    private String userName;
    private Integer reviewsId;
    private Integer ordersId;
    private Integer productId;
    private String defaultImage;
    private String productName;
    private String content;
    private LocalDateTime enrolledDate;
    private LocalDateTime modifiedDate;
    private String reviewImage;
    private Double rating;
    private Integer sugarRating;
    private Integer acidityRating;
    private Integer throatRating;
    private Boolean isPickuped; // Mapper에서 isPickuped에 대한 setter메소드를 사용하기 위해서 정의해줌.

}
