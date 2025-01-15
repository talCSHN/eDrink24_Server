package org.eDrink24.dto.coupon;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;


@Alias("CouponDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CouponDTO {

    private Integer couponId;
    private Integer userId;
    private Integer discountAmount;
    private LocalDate issueDate;
    private LocalDate endDate;
    private LocalDate useDate;
    private Boolean used;

    /*
    CREATE TABLE `coupon` (
    `couponId` INT NOT NULL AUTO_INCREMENT,
    `userId` INT NOT NULL DEFAULT 0,
    `discountAmount` INT NULL,
    `issueDate` TIMESTAMP NOT NULL,
    `endDate` TIMESTAMP NOT NULL,
    `useDate` DATETIME NULL,
    `used` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`couponId`),
    FOREIGN KEY (`userId`) REFERENCES `CUSTOMER`(`userId`)
);
     */

}
