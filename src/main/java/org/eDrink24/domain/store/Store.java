package org.eDrink24.domain.store;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId" , nullable = false)
    private Integer storeId;
    @Column(name = "storeName", nullable = false)
    private String storeName;
    @Column(name = "storeAddress", nullable = false)
    private String storeAddress;
    @Column(name = "storePhoneNum")
    private String storePhoneNum;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
}
