package org.eDrink24.controller.login.kakaoLogin;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.dto.kakaoLogin.KakaoTokenDTO;
import org.eDrink24.service.customer.KakaoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class KakaoLoginController {
    @Autowired
    KakaoLoginService kakaoLoginService;

    @PostMapping("/login/oauth2/kakao")
    public ResponseEntity<Map<String, Object>> kakaoLogin(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        KakaoTokenDTO kakaoTokenDTO = kakaoLoginService.getKakaoAccessToken(code);
        return kakaoLoginService.kakaoLogin(kakaoTokenDTO.getAccess_token());
    }
}
