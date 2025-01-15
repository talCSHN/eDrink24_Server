package org.eDrink24.service.customer;

import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;

public interface CustomerService {
    public CustomerDTO saveCustomer(CustomerDTO customer);
    public CustomerDTO selectCustomerByLoginId(String loginId);
    public CustomerDTO findByUserId(Integer userId);

    public void addSignupCoupon(Integer userId);

    public int updateCustomerToMyPage(CustomerDTO customerDTO);

    // 회원가입할때 아이디 중복체크
    public CustomerDTO customerIdCheck(String loginId);

    // 아이디 찾기
    public String findByUserNameAndEmail(String userName, String email);
    // 비밀번호 찾기
    public CustomerDTO findByLoginIdAndEmail(String loginId, String email);
    // id->userName
    public String findByUserNameById(int userId);

    // 사업자등록번호 일치 시 brNum저장, role "점주"로 업데이트
    public void updateToManager(CustomerDTO customerDTO);

    // 점주 -> 본인매장id찾기
    public int findMyStore(long brNum);

}
