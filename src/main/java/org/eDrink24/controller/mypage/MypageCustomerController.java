package org.eDrink24.controller.mypage;

import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.service.customer.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MypageCustomerController {

    CustomerService customerService;
    public MypageCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = {"/selectCustomerMyPage/{loginId}"})
    public CustomerDTO selectCustomerMyPage(@PathVariable String loginId) {
        CustomerDTO customerDTO = customerService.selectCustomerByLoginId(loginId);
        customerDTO.setPw(null);
        customerDTO.setLinkedId(null);
        return customerDTO;
    }
}
