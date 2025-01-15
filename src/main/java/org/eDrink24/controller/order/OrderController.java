package org.eDrink24.controller.order;

import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.eDrink24.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

  OrderService orderService;

  public OrderController(OrderService orderService) {
      this.orderService = orderService;
  }

    // 결제하기(ORDERS, ORDERHISTORY 테이블에 저장)
    @PostMapping("/showAllBasket/userId/{userId}/buyProductAndSaveHistory")
    public ResponseEntity<String> buyProductAndSaveHistory(@RequestBody List<OrderTransactionDTO> orderTransactionDTO,
                                                           @PathVariable Integer userId, Integer couponId) {
        orderService.buyProductAndSaveHistory(orderTransactionDTO, userId, couponId);
        return ResponseEntity.ok("Purchase successful");
    }

    @DeleteMapping("/showAllBasket/userId/{userId}/deleteBasketAndItem")
    public ResponseEntity<String> deleteBasketAndItem(@RequestBody List<OrderTransactionDTO> orderTransactionDTO,
                                                           @PathVariable Integer userId) {

        try {
            orderService.deleteBasketAndItem(orderTransactionDTO, userId);
            return ResponseEntity.ok("Purchase successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing purchase: " + e.getMessage());
        }
    }

    // 구매하지 않은 아이템 저장
    @PostMapping("/showAllBasket/userId/{userId}/saveNotPurchasedItem")
    public ResponseEntity<String> saveNotPurchasedItem(@RequestBody List<BasketDTO> basketDTOList,
                                                       @PathVariable Integer userId) {

        try {
            // 구매하지 않은 아이템 장바구니에 저장
            orderService.saveNotPurchasedBasketAndItems(userId, basketDTOList);
            return ResponseEntity.ok("Non-purchased items saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving non-purchased items: " + e.getMessage());
        }
    }



}
