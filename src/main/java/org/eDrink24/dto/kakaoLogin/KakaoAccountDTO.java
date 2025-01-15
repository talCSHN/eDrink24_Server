package org.eDrink24.dto.kakaoLogin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// 카카오로부터 받는 데이터
@Data
public class KakaoAccountDTO {
    private Long id;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Data
    public static class KakaoAccount {
        private Profile profile;
        private String email;

        @Data
        public static class Profile {
            private String nickname;
        }
    }
}