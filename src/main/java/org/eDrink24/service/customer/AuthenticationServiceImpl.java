package org.eDrink24.service.customer;

import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.repository.customer.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    CustomerRepository customerRepository;
    public AuthenticationServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO findByLoginId(String loginId) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = customerRepository.findByLoginId(loginId);
        if (customer == null) {
            return null;
        }
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    // id+pw => customer 정보 가져오기
    @Override
    public CustomerDTO authenticate(String loginId, String pw) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = customerRepository.authenticate(loginId, pw);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }
}
