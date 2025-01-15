package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eDrink24.dto.customer.CustomerDTO;

@Mapper
public interface CustomerMapper {
    public int saveCustomer(CustomerDTO customerDTO);
    public CustomerDTO selectCustomerMyPage(String loginId);
    public int updateCustomerToMyPage(CustomerDTO customerDTO);
    public void addSignupCoupon(Integer userId);

    // 회원가입할 때 아이디 중복체크
    public CustomerDTO customerIdCheck(String loginId);

    public int saveBrNum(CustomerDTO customerDTO);
    public int updateRole(@Param("brNum") Long brNum);

    // id-> userName
    public String findUserNameByUserId(int userId);

    // 점주 : 본인매장반환
    public int findMyStore(long brNum);
}
