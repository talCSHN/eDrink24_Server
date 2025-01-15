package org.eDrink24.service.customer;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.config.CustomerMapper;
import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.repository.customer.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
        public CustomerDTO saveCustomer(CustomerDTO customer) {
        int n = customerMapper.saveCustomer(customer);
        return customer;
    }

    @Override
    public CustomerDTO selectCustomerByLoginId(String loginId) {
        return customerMapper.selectCustomerMyPage(loginId);
    }

    @Override
    public CustomerDTO findByUserId(Integer userId) {
        Customer customer = customerRepository.findByUserId(userId);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    @Override
    public void addSignupCoupon(Integer userId) {
        customerMapper.addSignupCoupon(userId);
    }

    @Override
    public int updateCustomerToMyPage(CustomerDTO customerDTO) {
        int n = customerMapper.updateCustomerToMyPage(customerDTO);
        return n;
    }

    @Override
    public CustomerDTO customerIdCheck(String loginId) {
        return customerMapper.customerIdCheck(loginId);
    }

    // 아이디 찾기
    @Override
    public String findByUserNameAndEmail(String userName, String email) {
        Customer customer = customerRepository.findByUserNameAndEmail(userName, email);
        System.out.println(customer);
        String loginId = customer.getLoginId();
        return loginId;
    }

    // 비밀번호 찾기
    @Override
    public CustomerDTO findByLoginIdAndEmail(String loginId, String email) {
        Customer customer = customerRepository.findByLoginIdAndEmail(loginId, email);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    @Override
    public String findByUserNameById(int userId) {
        return customerMapper.findUserNameByUserId(userId);
    }

    @Override
    public void updateToManager(CustomerDTO customerDTO) {
        customerMapper.saveBrNum(customerDTO);

        Long brNum = customerDTO.getBrNum();
        if (brNum != null) {
            customerMapper.updateRole(brNum);
        } else {
            throw new RuntimeException("사업자 등록번호가 설정되지 않았습니다.");
        }
    }

    @Override
    public int findMyStore(long brNum) {
        return customerMapper.findMyStore(brNum);
    }
}
