package org.eDrink24.service.order;

import org.eDrink24.config.BasketMapper;
import org.eDrink24.config.OrderMapper;
import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.basket.BasketItemDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    OrderMapper orderMapper;

    public OrderHistoryServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderTransactionDTO> showOrderHistory(Integer userId) {
        return orderMapper.showOrderHistory(userId);
    }

    @Override
    public List<OrderTransactionDTO> showOrderHistoryDetails(Integer userId, LocalDateTime orderDate) {
        return orderMapper.showOrderHistoryDetails(userId, orderDate);
    }
}