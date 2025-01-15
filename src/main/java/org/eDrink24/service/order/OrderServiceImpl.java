package org.eDrink24.service.order;

import org.eDrink24.config.BasketMapper;
import org.eDrink24.config.InventoryMapper;
import org.eDrink24.config.OrderMapper;
import org.eDrink24.dto.Inventory.InventoryDTO;
import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.basket.BasketItemDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.eDrink24.excpetion.NotEnoughStock.NotEnoughStockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    InventoryMapper inventoryMapper;
    OrderMapper orderMapper;
    BasketMapper basketMapper;

    public OrderServiceImpl(OrderMapper orderMapper, BasketMapper basketMapper, InventoryMapper inventoryMapper) {
        this.orderMapper = orderMapper;
        this.basketMapper = basketMapper;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public List<BasketDTO> showAllBasket(String loginId) {
        return orderMapper.showAllBasket(loginId);
    }

    @Override
    public int buyProduct(List<OrderTransactionDTO> orderTransactionDTO) {
        return orderMapper.buyProduct(orderTransactionDTO);
    }

    @Override
    public int saveBuyHistory(List<OrderTransactionDTO> orderTransactionDTO) {
        return orderMapper.saveBuyHistory(orderTransactionDTO);
    }

    @Override
    public void deleteBasketItems( HashMap<String , Object> map) {
        orderMapper.deleteBasketItems(map);
    }

    @Override
    public void deleteBasket( HashMap<String , Object> map) {
        orderMapper.deleteBasket(map);
    }

    @Transactional
    public void buyProductAndSaveHistory(List<OrderTransactionDTO> orderTransactionDTO, Integer userId, Integer couponId) {
        if (orderTransactionDTO != null && !orderTransactionDTO.isEmpty()) {
            try {
                for (OrderTransactionDTO orderTransaction : orderTransactionDTO) {
                    // 재고감소는 오늘픽업일때만, 예약픽업은 점주의 발주처리가 완료되어야 재고가 생김
                    String pickupType = orderTransaction.getPickupType();
                    if (pickupType.equals("TODAY")) {
                        int storeId = orderTransaction.getStoreId();
                        int productId = orderTransaction.getProductId();
                        int quantity = orderTransaction.getOrderQuantity();

                        Map<String, Integer> map = new HashMap<>();
                        map.put("storeId", storeId);
                        map.put("productId", productId);
                        map.put("quantity", quantity);

                        InventoryDTO inventory = inventoryMapper.findInventoryForUpdate(map);

                        if (inventory == null || inventory.getQuantity() < quantity) {
                            System.out.println("!error!");
                            throw new NotEnoughStockException("매장재고부족");
                        }
                        inventoryMapper.updateInventory(map);
                    }
                }

                // 주문 저장
                orderMapper.buyProduct(orderTransactionDTO);

                // 주문 내역 저장
                orderMapper.saveBuyHistory(orderTransactionDTO);

                // pointDetails 테이블에 저장
                orderMapper.savePointDetails(orderTransactionDTO);

                // 포인트 적립
                HashMap<String, Integer> map = new HashMap<>();
                map.put("userId", userId);
                map.put("addedPoint", orderTransactionDTO.get(0).getTotalPoint());
                orderMapper.addTotalPoint(map);

                // 포인트 차감
                HashMap<String, Integer> map1 = new HashMap<>();
                map1.put("userId", userId);
                map1.put("pointAmount", orderTransactionDTO.get(0).getPointAmount());
                orderMapper.reduceTotalPoint(map1);

                couponId = orderTransactionDTO.get(0).getCouponId();

                // 쿠폰이 사용된 경우에만 업데이트
                if (couponId != null) {
                    HashMap<String, Integer> map2 = new HashMap<>();
                    map2.put("couponId", couponId);
                    map2.put("userId", userId);
                    orderMapper.deleteUsedCoupon(map2);
                }
            } catch (NotEnoughStockException e) {
                throw e; // GlobalExceptionHandler로 처리이임
            }
        }
    }

    @Transactional
    public void deleteBasketAndItem(List<OrderTransactionDTO> orderTransactionDTO, Integer userId) {
        // basketId 목록 수집
        List<Integer> basketIds = orderTransactionDTO.stream()
                .map(OrderTransactionDTO::getBasketId)
                .distinct()
                .collect(Collectors.toList());

        HashMap<String , Object> map = new HashMap<>();
        map.put("userid", userId);
        map.put("basketIds", basketIds);

            // basketItem에서 구매한 상품 삭제
            orderMapper.deleteBasketItems(map);
            // basket에서 구매한 상품 삭제
            orderMapper.deleteBasket(map);

    }

    @Override
    public void saveNotPurchasedBasket(BasketDTO basketDTO) {
        orderMapper.saveNotPurchasedBasket(basketDTO);
    }

    @Override
    public void saveNotPurchasedBasketItem(Integer basketId, BasketItemDTO items) {
        // Set basketId in item before saving
        items.setBasketId(basketId);
        orderMapper.saveNotPurchasedBasketItem(basketId, items);
    }

    @Transactional
    public void saveNotPurchasedBasketAndItems(Integer userId, List<BasketDTO> basketDTOList) {
        List<Integer> purchasedBasketIds = new ArrayList<>();

        // 구매한 basketId 목록 수집
        for (BasketDTO dto : basketDTOList) {
            Integer basketId = dto.getBasketId();
            if (basketId != null && !purchasedBasketIds.contains(basketId)) {
                purchasedBasketIds.add(basketId);
            }
        }

        List<BasketDTO> allBaskets = basketMapper.showProductInBasket(userId);
        List<BasketDTO> notPurchasedBaskets = allBaskets.stream()
                .filter(basket -> !purchasedBasketIds.contains(basket.getBasketId()))
                .collect(Collectors.toList());

        for (BasketDTO basket : notPurchasedBaskets) {
            // basket에 저장
            orderMapper.saveNotPurchasedBasket(basket);

            // basketItem에 저장
            List<BasketItemDTO> basketItems = basket.getItems();
            if (basketItems != null) {
                for (BasketItemDTO item : basketItems) {
                    orderMapper.saveNotPurchasedBasketItem(basket.getBasketId(), item);
                }
            }
        }
    }

    @Override
    public void addTotalPoint(HashMap<String , Integer> map) {
        orderMapper.addTotalPoint(map);
    }

    @Override
    public void reduceTotalPoint(HashMap<String, Integer> map1) {
        orderMapper.reduceTotalPoint(map1);
    }

    @Override
    public void deleteUsedCoupon(HashMap<String, Integer> map2) {
        orderMapper.deleteUsedCoupon(map2);
    }


}