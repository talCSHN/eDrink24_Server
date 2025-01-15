package org.eDrink24.dto.kakaoLogin;

import lombok.Data;
import org.eDrink24.dto.customer.CustomerDTO;

@Data
public class KakaoLoginResponseDTO {
    public boolean successLogin;
    public CustomerDTO customerDTO;
}
