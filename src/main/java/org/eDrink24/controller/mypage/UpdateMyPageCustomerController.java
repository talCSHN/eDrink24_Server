package org.eDrink24.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UpdateMyPageCustomerController {

    CustomerService customerService;
    EntityManagerFactory emf;
    PasswordEncoder passwordEncoder;
    public UpdateMyPageCustomerController(CustomerService customerService, EntityManagerFactory emf,
                                          PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.emf = emf;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping(value = {"/updateMyPageCustomer/{loginId}"})
    public ResponseEntity<CustomerDTO> updateMyPageCustomer(@PathVariable String loginId,
                                                            @RequestBody CustomerDTO customerDTO) {
        // 로그인 아이디로 로그인 아이디에 해당하는 컬럼을 수정.
        customerDTO.setLoginId(loginId);

        // 수정한 패스워드를 다시 암호화해준다.
        String ecrptPW = new BCryptPasswordEncoder().encode(customerDTO.getPw());
        customerDTO.setPw(ecrptPW);

        customerService.updateCustomerToMyPage(customerDTO);
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<Map<String, String>> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            Customer existingCustomer = em.find(Customer.class, customerDTO.getUserId());

            if (existingCustomer == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Not find Customer");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            // 비밀번호 변경 요청이 있을떼
            if (customerDTO.getPw() != null && !customerDTO.getPw().isEmpty()) {
                String newPw = passwordEncoder.encode(customerDTO.getPw());
                existingCustomer.setPw(newPw);
            }
            existingCustomer.setPhoneNum(customerDTO.getPhoneNum());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setPostalCode(customerDTO.getPostalCode());
            existingCustomer.setAddress1(customerDTO.getAddress1());
            existingCustomer.setAddress2(customerDTO.getAddress2());

            em.merge(existingCustomer);
            et.commit();

            Map<String, String> response = new HashMap<>();
            response.put("message", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            et.rollback();
            Map<String, String> response = new HashMap<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } finally {
            em.close();
        }
    }
}
