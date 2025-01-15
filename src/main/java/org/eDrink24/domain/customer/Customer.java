package org.eDrink24.domain.customer;

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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "userName", nullable = false, length = 20)
    private String userName;

    @Column(name = "loginId", nullable = false, length = 50)
    private String loginId;

    @Column(name = "pw", nullable = false, length = 100)
    private String pw;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "phoneNum", nullable = false, length = 20)
    private String phoneNum;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "postalCode", nullable = false, length = 20)
    private String postalCode;

    @Column(name = "address1", nullable = false, length = 50)
    private String address1;

    @Column(name = "address2", nullable = false, length = 30)
    private String address2;

    @Column(name = "currentLocation", length = 50)
    private String currentLocation;

    @Column(name = "currentStoreId", length = 50)
    private Integer currentStoreId;

    @Column(name = "totalPoint", nullable = false)
    private Integer totalPoint = 0;

    @Column(name = "role", nullable = false, length = 20)
    private String role = "일반회읜";

    @Column(name = "linkedId", nullable = true, length = 100)
    private Long linkedId;

    @Column(name="brNum", nullable = false)
    private Long brNum;

}
