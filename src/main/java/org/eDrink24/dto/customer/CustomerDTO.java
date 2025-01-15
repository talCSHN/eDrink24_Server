package org.eDrink24.dto.customer;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Alias("CustomerDTO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CustomerDTO {
    private Integer userId;
    @NotBlank(message = "username 필드는 필수 입력")
    private String userName;
    @NotBlank(message = "userid 필드는 필수 입력")
    private String loginId;
    @Size(min = 4, max = 12 ,message = "password 8~12 크기 입력")
    private String pw;
    private String gender;
    private LocalDate birthdate;
    @NotBlank(message = "전화번호는 필수 입력")
    private String phoneNum;
    private String email;
    private String postalCode;
    private String address1;
    private String address2;
    private String currentLocation;
    private Integer currentStoreId=0;
    private Integer totalPoint;
    private String role;
    private Long linkedId;
    private Long brNum;
}
