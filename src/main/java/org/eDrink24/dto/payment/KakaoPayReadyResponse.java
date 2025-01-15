package org.eDrink24.dto.payment;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class KakaoPayReadyResponse {
        private String tid;
        private boolean tms_result;
        private String created_at;
        private String next_redirect_pc_url;
        private String next_redirect_mobile_url;
        private String next_redirect_app_url;
        private String android_app_scheme;
        private String ios_app_scheme;
}
