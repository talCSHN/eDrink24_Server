package org.eDrink24.service.payment;

import java.util.List;
import java.util.Map;

public interface KakaoPayService {
    public Map<String, Object> kakaoPayReady(Map<String, Object> orderTransaction);
    public Map<String, Object> kakaoPayApprove(String tid, String pgToken, int userId);
}
