package org.eDrink24.service.order;

import org.apache.ibatis.annotations.Param;
import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.basket.BasketItemDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;

import java.util.HashMap;
import java.util.List;

public interface OrderService {

    // 장바구니에 담긴 상품 불러오기
    public List<BasketDTO> showAllBasket(String loginId);

    // 결제하기(ORDERS 테이블에 저장)
    public int buyProduct(List<OrderTransactionDTO> orderTransactionDTO);

    // 결제내역 저장(ORDERSHISTORY 테이블에 저장)
    public int saveBuyHistory(List<OrderTransactionDTO> orderTransactionDTO);

    // buyProduct, saveBuyHistory 동시에 트랙잭션으로 처리
    public void buyProductAndSaveHistory(List<OrderTransactionDTO> orderTransactionDTO, Integer userId, Integer couponId);

    // 결제된 상품 장바구니에서 제거(basketItem 테이블에서 삭제)
    public void deleteBasketItems(HashMap<String , Object> map);

    // 결제된 상품 장바구니에서 제거(basketItem 테이블에서 삭제)
    public void deleteBasket(HashMap<String , Object> map);

    public void deleteBasketAndItem(List<OrderTransactionDTO> orderTransactionDTO, Integer userId);

    public void saveNotPurchasedBasket(BasketDTO basketDTO);

    public void saveNotPurchasedBasketItem(Integer basketId, BasketItemDTO items);

    public void saveNotPurchasedBasketAndItems(Integer userId, List<BasketDTO> basketDTOList);

    // 결제금액 1%만큼 customer테이블 totalPoint에 적립
    public void addTotalPoint(HashMap<String , Integer> map);

    // 사용한 포인트 차감
    public void reduceTotalPoint(HashMap<String , Integer> map1);

    // 사용된 쿠폰 처리
    public void deleteUsedCoupon(HashMap<String , Integer> map2);
}
