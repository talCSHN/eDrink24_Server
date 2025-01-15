package org.eDrink24.controller.order;

import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.eDrink24.service.order.OrderHistoryService;
import org.eDrink24.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class OrderHistoryController {

  OrderHistoryService orderHistoryService;

  public OrderHistoryController(OrderHistoryService orderHistoryService) {
      this.orderHistoryService = orderHistoryService;
  }

    // 주문내역 조회
    @GetMapping("/showOrderHistory/{userId}")
    public ResponseEntity<List<OrderTransactionDTO>> showOrderHistory(@PathVariable Integer userId) {
        List<OrderTransactionDTO> orderTransactionDTO = orderHistoryService.showOrderHistory(userId);
        return ResponseEntity.ok(orderTransactionDTO);
    }

    // 주문상세내역 조회
    @GetMapping("/showOrderHistoryDetails/{userId}/{orderDate}")
    public ResponseEntity<List<OrderTransactionDTO>> showOrderHistoryDetails(@PathVariable Integer userId, @PathVariable String orderDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedOrderDate = LocalDateTime.parse(orderDate, formatter);

        List<OrderTransactionDTO> orderHistoryDetails = orderHistoryService.showOrderHistoryDetails(userId, formattedOrderDate);
        return ResponseEntity.ok(orderHistoryDetails);
    }

}
