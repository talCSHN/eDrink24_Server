package org.eDrink24.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.service.customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 회원가입
    @PostMapping(value={"/signup"})
    public ResponseEntity<CustomerDTO> signup(@Valid @RequestBody CustomerDTO customer) {
        String ecrptPW = new BCryptPasswordEncoder().encode(customer.getPw());
        customer.setPw(ecrptPW);

        CustomerDTO saveCustomer = customerService.saveCustomer(customer);
        customerService.addSignupCoupon(customer.getUserId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(saveCustomer.getLoginId()) //위의 {userid}를 치환시킴.
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //회원가입 할 때 아이디 중복체크
    @GetMapping(value = {"/customerIdCheck/{loginId}"})
    public ResponseEntity<String> customerIdCheck(@PathVariable String loginId) {
        CustomerDTO customerDTO = customerService.customerIdCheck(loginId);
        if(customerDTO != null) {
            return ResponseEntity.status(409).body("이 아이디는 사용불가합니다.");
        }
        return ResponseEntity.ok("이 아이디는 사용가능합니다.");
    }

    // 사업자등록번호 일치 시 brNum저장, role "점주"로 업데이트
    @PostMapping(value = "/api/updateToManager")
    public ResponseEntity<String> updateToManager(@RequestBody CustomerDTO customerDTO) {
        try {
        customerService.updateToManager(customerDTO);
        return new ResponseEntity<>("점주로 계정전환 성공", HttpStatus.CREATED);
        }catch (Exception e) {
        return new ResponseEntity<>("점주로 계정전환 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/findMyStore")
    public ResponseEntity<Integer> findMyStore(@RequestBody Long brNum) {
        try {
            return ResponseEntity.ok(customerService.findMyStore(brNum));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
