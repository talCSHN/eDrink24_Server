package org.eDrink24.service.customer;

import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;

import java.util.Map;

public interface AuthenticationService {
    public CustomerDTO findByLoginId(String loginId);
    public CustomerDTO authenticate(String loginId, String pw);
}
