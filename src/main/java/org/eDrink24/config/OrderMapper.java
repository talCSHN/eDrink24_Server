package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.eDrink24.dto.basket.BasketDTO;

import org.eDrink24.dto.basket.BasketItemDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface OrderMapper {

    // 장바구니에 담긴 상품 불러오기
    public List<BasketDTO> showAllBasket(String loginId);

    // 결제하기(ORDERS 테이블에 저장)
    public int buyProduct(List<OrderTransactionDTO> orderTransactionDTO);

    // 결제내역 저장(ORDERSHISTORY 테이블에 저장)
    public int saveBuyHistory(List<OrderTransactionDTO> orderTransactionDTO);

    // 결제된 상품 장바구니에서 제거(basketItem 테이블에서 삭제)
    public void deleteBasketItems( HashMap<String , Object> map);

    // 결제된 상품 장바구니에서 제거(basketItem 테이블에서 삭제)
    public void deleteBasket( HashMap<String , Object> map);

    // buyProduct, saveBuyHistory 동시에 트랙잭션으로 처리
    public void buyProductAndSaveHistory(List<OrderTransactionDTO> orderTransactionDTO);

    public void deleteBasketAndItem(List<OrderTransactionDTO> orderTransactionDTO);

    public void saveNotPurchasedBasket(BasketDTO basketDTO);

    public void saveNotPurchasedBasketItem(Integer basketId, BasketItemDTO items);

    // 결제금액 1%만큼 customer테이블 totalPoint에 적립
    public void addTotalPoint(HashMap<String , Integer> map);

    // 사용한 포인트 차감
    public void reduceTotalPoint(HashMap<String , Integer> map1);

    // 사용된 쿠폰 처리
    public void deleteUsedCoupon(HashMap<String , Integer> map2);

    // 주문내역 조회
    public List<OrderTransactionDTO> showOrderHistory(Integer userId);

    // 주문상세내역 조회
    public List<OrderTransactionDTO> showOrderHistoryDetails(Integer userId, LocalDateTime orderDate);

    // 포인트 상세 테이블에 저장
    public void savePointDetails(List<OrderTransactionDTO> orderTransactionDTO);


}
