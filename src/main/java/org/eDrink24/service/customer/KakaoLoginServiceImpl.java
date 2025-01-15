package org.eDrink24.service.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.eDrink24.config.kakaoConfig.KakaoConfig;
import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.dto.kakaoLogin.KakaoAccountDTO;
import org.eDrink24.dto.kakaoLogin.KakaoTokenDTO;
import org.eDrink24.repository.customer.CustomerRepository;
import org.eDrink24.security.JwtTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class KakaoLoginServiceImpl implements KakaoLoginService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenService jwtTokenService;
    private KakaoConfig kakaoConfig;

    public KakaoLoginServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper,
                                 JwtTokenService jwtTokenService, KakaoConfig kakaoConfig) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenService = jwtTokenService;
        this.kakaoConfig = kakaoConfig;
    }

    // 카카오로부터 회원 토큰 받아오기
    @Override
    @Transactional
    public KakaoTokenDTO getKakaoAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Http Response Body 객체 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code"); //카카오 공식문서 기준 authorization_code 로 고정
        params.add("client_id", kakaoConfig.getClientId()); // 카카오 Dev 앱 REST API 키
        params.add("redirect_uri", kakaoConfig.getRedirectUri()); // 카카오 Dev redirect uri
        params.add("code", code); // 프론트에서 인가코드 요청시 받은 인가코드값
        params.add("client_secret", kakaoConfig.getClientSecret()); // 카카오 Dev 카카오 로그인 Client Secret

        // 헤더와 바디 합치기 위해 Http Entity 객체 생성
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // JSON Parsing (-> KakaoTokenDTO)
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // 시간 설정 => LocalDate 등으로 반환가능하게 해줌
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // json에 정의되지 않은 필드가 있으면 무시
        KakaoTokenDTO kakaoTokenDTO = null;
        try {
            kakaoTokenDTO = objectMapper.readValue(accessTokenResponse.getBody(), KakaoTokenDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return kakaoTokenDTO;
    }

    @Override
    public CustomerDTO getKakaoInfo(String kakaoAccessToken) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer " + kakaoAccessToken);

        HttpEntity<MultiValueMap<String, String>> accountInfoRequest = new HttpEntity<>(headers);

        // POST로 카카오 API 서버에 요청 후 response를 받아옴
        ResponseEntity<String> accountInfoResponse = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                accountInfoRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        KakaoAccountDTO kakaoAccountDTO = null; // 카카오에서 받아오는 데이터 담을 DTO
        try {
            kakaoAccountDTO = objectMapper.readValue(accountInfoResponse.getBody(), KakaoAccountDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (kakaoAccountDTO == null || kakaoAccountDTO.getKakaoAccount() == null) {
            throw new RuntimeException("카카오 계정 정보를 가져오는데 실패했습니다.");
        }

        // 카카오 회원인지를 구분하기 위해 linkedId에서 동일한 회원을 찾음
        Long kakaoId = kakaoAccountDTO.getId();
        Customer existCustomer = customerRepository.findByLinkedId(kakaoId);
        if (existCustomer != null) {
            CustomerDTO existCustomerDTO = modelMapper.map(existCustomer, CustomerDTO.class);
            return existCustomerDTO;
        } else {
            // 새 사용자로 판단하고 새로운 회원정보에 데이터 담아 넘김
            CustomerDTO newCustomerDTO = CustomerDTO.builder()
                    .userName(kakaoAccountDTO.getKakaoAccount().getProfile().getNickname())
                    .loginId(kakaoAccountDTO.getKakaoAccount().getEmail())
                    .email(kakaoAccountDTO.getKakaoAccount().getEmail())
                    .linkedId(kakaoId).build();
            return newCustomerDTO;
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> kakaoLogin(String kakaoAccessToken) {
        CustomerDTO customerDTO = getKakaoInfo(kakaoAccessToken);
        log.info(customerDTO.toString());
        // 회원가입되어있으면 userId가 있을테니 로그인처리
        if (customerDTO.getUserId() != null) {
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("일반회원"));
            CustomerDTO authCustomer =
                    CustomerDTO.builder()
                            .loginId(customerDTO.getLoginId())
                            .pw(customerDTO.getPw())
                            .userName(customerDTO.getUserName())
                            .role(customerDTO.getRole()).build();
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authCustomer, null, roles);

            String token = jwtTokenService.generateToken(authenticationToken);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("loginId", customerDTO.getLoginId());
            response.put("userId", customerDTO.getUserId());
            // 위치정보 업데이트
            response.put("currentLocation", customerDTO.getAddress1());
            response.put("currentStoreId", customerDTO.getCurrentStoreId());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("customerDTO", customerDTO);
            return ResponseEntity.status(401).body(response);
        } // 로그인 처리
    }
}

