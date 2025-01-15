package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.basket.BasketItemDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface BasketMapper {

    // 장바구니 저장
    public void saveBasket(BasketDTO basketDTO);
    // 장바구니 목록에 이미 존재하는지 확인
    public BasketItemDTO checkBasketItem(Integer productId);
    // 장바구니 목록에 이미 존재하면 중복 저장하지 않고 수량만 증가
    public void updateBasketQuantity(Integer productId,Integer basketQuantity);
    // 장바구니 목록에 없으면 새로 추가
    public void saveBasketItem(@Param("basketId") Integer basketId, @Param("items") BasketItemDTO items);

    // 장바구니와 장바구니 목록 조회
    List<BasketDTO> showProductInBasket(Integer userId);
    List<BasketItemDTO> getBasketItems(Integer basketId);

    // 장바구니 목록 전체 삭제 (자식테이블 삭제)
    public void deleteAllBasketItems(Integer userId);
    // 장바구니 전체 삭제 (부모테이블 삭제)
    public void deleteAllBasket(Integer userId);


    // 장바구니 목록 선택 삭제 (자식테이블 삭제)
    public void deleteSelectedBasketItems(Integer basketId);
    // 장바구니 선택 삭제 (부모테이블 삭제)
    public void deleteSelectedBasket(Integer userId,Integer basketId);

    //장바구니에서 수량 증가나 감소시키면 DB에 basketQuantity가 업데이트 됨.
    public void updateBasketQuantity2(Integer productId, Integer basketId,Integer basketQuantity);

    public Integer existsProductInInventory(Integer storeId, Integer productId);

}
