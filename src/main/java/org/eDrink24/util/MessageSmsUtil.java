package org.eDrink24.util;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class MessageSmsUtil {
    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecretKey;
    @Value("${coolsms.api.fromNumber}")
    private String fromNumber;

    @Autowired
    Random rand;

    private DefaultMessageService defaultMessageService;

    @PostConstruct
    private void init(){
        this.defaultMessageService
                = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    public String createCertificateCode() {
//        String createCertificateCode = new Random().ints(6, 0, 10)
//                .mapToObj(Integer::toString)
//                .collect(Collectors.joining());
        // 원시타입을 사용하는 경우 => stack 메모리에 할당되는데 참조(래퍼타입)+데이터크기가 클때(병렬 처리가 효과적일 때 )
        StringBuilder createCertificateCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            createCertificateCode.append(rand.nextInt(10));
        }
        return createCertificateCode.toString();
    };

    // SingleMessageSentResponse
    public SingleMessageSentResponse sendMessage(String phoneNum, String certificateCode) {
        try {
            Message message = new Message();
            message.setFrom(fromNumber);
            message.setTo(phoneNum);
            message.setText("[eDrink24] 인증번호 [" + certificateCode + "]");
            SingleMessageSentResponse response = this.defaultMessageService.sendOne(new SingleMessageSendingRequest(message));
            return response;
        } catch (Exception e) {
            throw new RuntimeException("메시지 전송 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}
