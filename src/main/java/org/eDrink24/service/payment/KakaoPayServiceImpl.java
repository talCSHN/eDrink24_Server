package org.eDrink24.service.payment;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class KakaoPayServiceImpl implements  KakaoPayService {
    @Value("${kakao_pay.secret_key}")
    private String secret_key;
    @Value("${client_url}")
    private String client_url;
    @Value("${kakao_pay.cid}")
    private String cid;

    private static final String KAKAO_PAY_READY_URL = "https://open-api.kakaopay.com/online/v1/payment/ready";
    private static final String KAKAO_PAY_APPROVE_URL = "https://open-api.kakaopay.com/online/v1/payment/approve";

    @Autowired
    ProductService  productService;

    @Override
    public Map<String, Object> kakaoPayReady(Map<String, Object> orderTransaction) {
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(orderTransaction);
        String userId = (String) orderTransaction.get("userId");
        String productName = productService.findProductNameByProductId((Integer) orderTransaction.get("productId"));
        int orderQuantity = (Integer) orderTransaction.get("orderQuantity");
        int price = (Integer) orderTransaction.get("price");

        // 헤더설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY " + secret_key);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 바디설정
        Map<String, Object> params = new HashMap<>();
        params.put("cid", cid);
        // 테스트결제에선 뭘넣든 항상 같은 tid만 반환하므로 동일값 넣어줌. 실제론 주문마다 다른 id를 기입해야함
        params.put("partner_order_id", "test");
        params.put("partner_user_id", userId);
        params.put("item_name", productName);
        params.put("quantity", orderQuantity);
        params.put("total_amount", price);
        params.put("tax_free_amount", "0");
        params.put("approval_url", client_url+"/order/approval");
        params.put("cancel_url", client_url+"/order/cancelOrFail");
        params.put("fail_url", client_url+"/order/cancelOrFail");

        HttpEntity<Map<String, Object>> body = new HttpEntity<>(params, headers);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                KAKAO_PAY_READY_URL, HttpMethod.POST, body, new ParameterizedTypeReference<Map<String, Object>>() {});
        return response.getBody();
    }


    @Override
    public Map<String, Object> kakaoPayApprove(String tid, String pgToken, int userId) {
        RestTemplate restTemplate = new RestTemplate();

        String userIdStr = String.valueOf(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY " + secret_key);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> params = new HashMap<>();
        params.put("cid", cid);
        params.put("tid", tid);
        params.put("partner_order_id", "test");
        params.put("partner_user_id", userIdStr);
        params.put("pg_token", pgToken);

        HttpEntity<Map<String, Object>> body = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    KAKAO_PAY_APPROVE_URL, HttpMethod.POST, body, Map.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // 예외 처리: 테스트 결제라 tid값이 항상 동일한 문제
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST && e.getResponseBodyAsString().contains("payment is already done!")) {
                // 정상적인 결제 완료 응답처럼 처리
                Map<String, Object> successResponse = new HashMap<>();
                successResponse.put("status", "success");
                successResponse.put("message", "Payment is already completed, proceeding as successful.");
                successResponse.put("tid", tid);
                successResponse.put("pg_token", pgToken);
                successResponse.put("user_id", userIdStr);
                return successResponse;
            } else {
                // 다른 예외에 대한 처리
                throw new RuntimeException("Unexpected error during payment approval: " + e.getMessage());
            }
        }
    }
}
