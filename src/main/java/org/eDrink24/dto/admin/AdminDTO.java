package org.eDrink24.dto.admin;

import lombok.*;
import org.apache.ibatis.type.Alias;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Alias("AdminDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AdminDTO {

    private Integer ordersId;
    private Integer storeId;
    private Integer userId;
    private Integer productId;
    private LocalDateTime orderDate;
    private Boolean isCompleted;
    private String changeStatus;
    private LocalDateTime changeDate;
    private Integer orderQuantity;
    private String pickupType;

    private String userName;
    private String productName;

}
