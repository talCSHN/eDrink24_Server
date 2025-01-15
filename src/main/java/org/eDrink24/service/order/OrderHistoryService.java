package org.eDrink24.service.order;

import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.basket.BasketItemDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface OrderHistoryService {

    // 주문내역 조회
    public List<OrderTransactionDTO> showOrderHistory(Integer userId);

    // 주문상세내역 조회
    public List<OrderTransactionDTO> showOrderHistoryDetails(Integer userId, LocalDateTime orderDate);

}
