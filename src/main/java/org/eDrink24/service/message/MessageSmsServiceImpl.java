package org.eDrink24.service.message;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.dto.message.MessageSmsDTO;
import org.eDrink24.excpetion.message.SmsMismatchException;
import org.eDrink24.repository.customer.CustomerRepository;
import org.eDrink24.repository.message.MessageSmsRepository;
import org.eDrink24.util.MessageSmsUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class MessageSmsServiceImpl implements MessageSmsService {

    private final MessageSmsRepository messageSmsRepository;
    private MessageSmsUtil messageSmsUtil;
    private CustomerRepository customerRepository;

    public MessageSmsServiceImpl(MessageSmsUtil messageSmsUtil, MessageSmsRepository messageSmsRepository,
                                 CustomerRepository customerRepository) {
        this.messageSmsUtil = messageSmsUtil;
        this.messageSmsRepository = messageSmsRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void sendMessageSms(MessageSmsDTO messageSmsDTO) {
        String phoneNum = messageSmsDTO.getPhoneNum();
        String certificateCode = messageSmsUtil.createCertificateCode();
        messageSmsUtil.sendMessage(phoneNum, certificateCode);
        messageSmsRepository.createSmsCertification(phoneNum, certificateCode);
    }

    @Override
    public void verifyMessageSms(MessageSmsDTO messageSmsDTO) {
        if (!isVerified(messageSmsDTO)) {
            throw new SmsMismatchException("인증번호가 일치하지 않습니다.");
        }
        messageSmsRepository.removeSmsCertification(messageSmsDTO.getPhoneNum());
    }

    @Override
    public boolean isVerified(MessageSmsDTO messageSmsDTO) {
        String storedSmsCertification = messageSmsRepository.getSmsCertification(messageSmsDTO.getPhoneNum());
        return storedSmsCertification != null && storedSmsCertification.equals(messageSmsDTO.getCertificateCode());
    }

    @Override
    public boolean isUsedPhoneNum(String phoneNum) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = customerRepository.findByPhoneNum(phoneNum);
        if (customer == null) {
            return false;
        } else {
            return true;
        }
    }
}
