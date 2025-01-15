package org.eDrink24.controller.customer;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.domain.customer.Customer;
import org.eDrink24.dto.customer.CustomerDTO;
import org.eDrink24.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UpdateCustomerController {

    @Autowired
    EntityManagerFactory emf;
    
    // 사용자 매장, 현재위치 수정
    @PostMapping("/api/updateCustomerToStore")
    public ResponseEntity<String> updateCustomerToStore(@RequestBody Map<String, Object> data) {
        Integer userId = (Integer) data.get("userId");
        String currentLocation = (String) data.get("currentLocation");
        Integer currentStoreId = (Integer) data.get("currentStoreId");

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            Customer customer = em.find(Customer.class, userId);
            customer.setCurrentLocation(currentLocation);
            customer.setCurrentStoreId(currentStoreId);
            em.merge(customer);
            et.commit();

            return ResponseEntity.ok("success");
        } catch (Exception e) {
            et.rollback();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        } finally {
            em.close();
        }
    }
}
