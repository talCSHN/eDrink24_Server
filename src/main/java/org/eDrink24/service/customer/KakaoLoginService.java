package org.eDrink24.service.customer;

import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.dto.kakaoLogin.KakaoTokenDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface KakaoLoginService {
    public KakaoTokenDTO getKakaoAccessToken(String code);
    public CustomerDTO getKakaoInfo(String kakaoAccessToken);
    public ResponseEntity<Map<String, Object>> kakaoLogin(String kakaoAccessToken);

}
