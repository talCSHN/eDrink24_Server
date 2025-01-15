package org.eDrink24.domain.product;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")  // Database column name
    private Integer productId;

    @Column(name = "productName", nullable = false)  // Database column name
    private String productName;

    @Column(name = "category1", nullable = false)  // Database column name
    private String category1;

    @Column(name = "category2", nullable = false)  // Database column name
    private String category2;

    @Column(name = "promotionId")  // Database column name
    private Integer promotionId;

    @Column(name = "price", nullable = false)  // Database column name
    private Integer price;

    @Column(name = "defaultImage")  // Database column name
    private String defaultImage;

    @Column(name = "countDibs")  // Database column name
    private Integer countDibs;

    @Column(name = "isCoupon")  // Database column name
    private Boolean isCoupon;

    @Column(name = "isPoint")  // Database column name
    private Boolean isPoint;

    @Column(name = "enrollDate")  // Database column name
    private LocalDate enrollDate;
}
