package org.eDrink24.controller.payment;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.service.payment.KakaoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class KakaoPayController {
    @Autowired
    private KakaoPayService kakaoPayService;

    @PostMapping("/api/kakaoPay")
    public Map<String, Object> kakaoPayReady(@RequestBody List<Map<String, Object>> orderTransactionList) {
        if (!orderTransactionList.isEmpty()) {
            Map<String, Object> orderTransaction = orderTransactionList.get(0);
            return kakaoPayService.kakaoPayReady(orderTransaction);
        } else {
            throw new IllegalArgumentException("Order transaction list is empty");
        }
    }

    @GetMapping("/api/kakaoPay/approve")
    public Map<String, Object> kakaoPayApprove(@RequestParam("pg_token") String pgToken,
                                               @RequestParam("tid") String tid,
                                               @RequestParam("userId") int userId) {
        return kakaoPayService.kakaoPayApprove(tid, pgToken, userId);
    }
}
