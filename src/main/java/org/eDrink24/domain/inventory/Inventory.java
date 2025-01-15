package org.eDrink24.domain.inventory;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventoryId", nullable=false)
    private Integer inventoryId;

    @Column(name="storeId", nullable = false)
    private Integer storeId;

    @Column(name="productId", nullable = false)
    private Integer productId;

    @Column(name="quantity", nullable = false)
    private Integer quantity = 0;
}
