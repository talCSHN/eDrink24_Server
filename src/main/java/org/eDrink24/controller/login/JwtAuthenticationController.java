package org.eDrink24.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.security.JwtTokenResponse;
import org.eDrink24.security.JwtTokenService;
import org.eDrink24.service.customer.AuthenticationService;
import org.eDrink24.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class JwtAuthenticationController {
    @Autowired
    ServletContext ctx;

    private final JwtTokenService jwtTokenService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationController(JwtTokenService jwtTokenService,
                                       AuthenticationService authenticationService,
                                       PasswordEncoder passwordEncoder) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody Map<String, String> jwtTokenRequest) {
        log.info("logger: jwtTokenRequest: {}", jwtTokenRequest);

        CustomerDTO customerDTO = authenticationService.findByLoginId(jwtTokenRequest.get("loginId"));
        if (customerDTO == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", null);
            response.put("userId", null);
            return ResponseEntity.status(401).body(response); // Unauthorized
        }

        if (passwordEncoder.matches(jwtTokenRequest.get("pw"), customerDTO.getPw())) {
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("일반회원"));
            CustomerDTO authCustomer =
                    CustomerDTO.builder()
                            .loginId(jwtTokenRequest.get("loginId"))
                            .pw(jwtTokenRequest.get("pw"))
                            .userName(customerDTO.getUserName())
                            .role(customerDTO.getRole()).build();
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authCustomer, null, roles);

            String token = jwtTokenService.generateToken(authenticationToken);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", customerDTO.getUserId());
            response.put("loginId", customerDTO.getLoginId());
            response.put("currentLocation", customerDTO.getCurrentLocation());
            response.put("currentStoreId", customerDTO.getCurrentStoreId());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("token", null);
            response.put("userId", null);
            response.put("loginId", null);
            response.put("currentLocation", null);
            response.put("currentStoreId", null);
            return ResponseEntity.status(401).body(response); // Unauthorized
        }
    }

}
