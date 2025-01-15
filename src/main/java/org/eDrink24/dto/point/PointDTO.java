package org.eDrink24.dto.point;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Alias("PointDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PointDTO {

    private Integer userId;
    private String userName;
    private Integer ordersId;
    private Integer productId;
    private String productName;
    private String defaultImage;
    private Integer price;
    private LocalDateTime saveDate;
    private Integer point;

}
