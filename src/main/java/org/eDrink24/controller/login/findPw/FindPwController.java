package org.eDrink24.controller.login.findPw;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Map;

@RestController
@Slf4j
public class FindPwController {
    @Autowired
    CustomerService customerService;
    @Autowired
    EntityManagerFactory emf;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/findPw")
    public ResponseEntity<String> findPw(@RequestBody Map<String, String> map) {
        try {
            String loginId = map.get("loginId");
            String email = map.get("email");
            CustomerDTO customerDTO = customerService.findByLoginIdAndEmail(loginId, email);
            return ResponseEntity.ok().body("회원정보가 일치합니다. 비밀번호를 변경해주세요.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("회원정보를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/findPw/updatePw")
    public ResponseEntity<String> updatePw(@RequestBody Map<String, String> map) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        String loginId = map.get("loginId");
        String email = map.get("email");
        CustomerDTO customerDTO = customerService.findByLoginIdAndEmail(loginId, email);

        try {
            Customer existingCustomer = em.find(Customer.class, customerDTO.getUserId());
            String newPw = passwordEncoder.encode(map.get("newPw"));
            existingCustomer.setPw(newPw);

            em.merge(existingCustomer);
            et.commit();

            return ResponseEntity.ok().body("변경된 비밀번호로 로그인 해주세요.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
