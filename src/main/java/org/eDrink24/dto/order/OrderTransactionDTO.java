package org.eDrink24.dto.order;

import lombok.*;
import org.apache.ibatis.type.Alias;
import java.time.LocalDateTime;


@Alias("OrderTransactionDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrderTransactionDTO {

    private Integer storeId;
    private Integer userId;
    private Integer productId;
    private LocalDateTime orderDate;
    private LocalDateTime pickupDate;
    private Boolean isCompleted;
    private String orderStatus;
    private Integer orderQuantity;
    private String pickupType;
    private Integer orderAmount;
    private Integer pointAmount;
    private Integer totalPoint;
    private Integer couponId;
    private String storeName;

    private Integer ordersId;
    private String changeStatus;
    private LocalDateTime changeDate;

    private Integer basketId;
    private Integer itemId;
    private String defaultImage;
    private String productName;
    private Integer price;
    private Integer basketQuantity;

    private Integer reviewsId;
    private String content;
    private LocalDateTime enrolledDate;
    private LocalDateTime modifiedDate;
    private String reviewImage;
    private Integer rating;
    private Integer sugarRating;
    private Integer acidityRating;
    private Integer throatRating;

    private Integer pointDetailsId;
    private LocalDateTime saveDate;
    private Integer point;

}

