package org.eDrink24.controller.login.findId;

import org.eDrink24.service.customer.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FindIdController {

    private final CustomerService customerService;

    public FindIdController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/findId")
    public ResponseEntity<String> findId(@RequestBody Map<String, String> request) {
        String userName = request.get("userName");
        String email = request.get("email");

        System.out.println("Received userName: " + userName);
        System.out.println("Received email: " + email);

        String loginId = customerService.findByUserNameAndEmail(userName, email);
        System.out.println(loginId);
        if (loginId != null) {
            return ResponseEntity.ok().body(loginId);
        } else {
            return ResponseEntity.status(404).body("가입된 회원이 아닙니다");
        }
    }
}
