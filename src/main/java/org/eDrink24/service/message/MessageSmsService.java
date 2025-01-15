package org.eDrink24.service.message;

import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.dto.message.MessageSmsDTO;

public interface MessageSmsService {
    public void sendMessageSms(MessageSmsDTO messageSmsDTO);
    public void verifyMessageSms(MessageSmsDTO messageSmsDTO);
    public boolean isVerified(MessageSmsDTO messageSmsDTO);

    // 회원가입 전화번호 중복확인
    public boolean isUsedPhoneNum(String phoneNum);
}
