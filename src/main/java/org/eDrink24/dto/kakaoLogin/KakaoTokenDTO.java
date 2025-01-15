package org.eDrink24.dto.kakaoLogin;

import lombok.Data;

@Data
public class KakaoTokenDTO {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String id_token;
    private int expires_in;
    private int refresh_token_expires_in;
    private String scope;
}
