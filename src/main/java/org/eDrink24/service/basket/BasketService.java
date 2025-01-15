package org.eDrink24.service.basket;

import org.apache.ibatis.annotations.Param;
import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.basket.BasketItemDTO;

import java.util.List;
import java.util.Map;

public interface BasketService {

    public void saveProductToBasket(BasketDTO basketDTO);
    public List<BasketDTO> showProductInBasket(Integer userId);
    public void deleteAllProductInBasket(Integer userId);
    public void deleteProductByBasketIdInBasket(Integer userId,Integer basketId);
    public List<BasketItemDTO> getBasketItems(Integer basketId);
    public void updateBasketQuantity2(Integer productId, Integer basketId,Integer basketQuantity);

    public boolean existsProductInInventory(Integer storeId, Integer productId);
}
