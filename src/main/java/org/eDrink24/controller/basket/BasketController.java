package org.eDrink24.controller.basket;

import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.basket.BasketItemDTO;
import org.eDrink24.service.basket.BasketService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BasketController {

    BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

   /*
    BasketDTO에 BaksetItemDTO가 List로 구현되어있기 때문에, 데이터를 넘길 때 배열형태인 []형식으로 넘겨줘야함.

      {
  "userId":4,
  "items":
   [
   {
    "productId":81,
    "defaultImage":"https://sitem.ssgcdn.com/01/22/58/item/1000048582201_i1_290.jpg",
    "productName":"[레드와인] 도스코파스 까베르네 소비뇽",
    "price":4900,
    "basketQuantity":4
   }
  ]
}

    */
    @PostMapping(value = {"/saveProductToBasket"})
    public void saveProductToBasket(@RequestBody BasketDTO basketDTO) {
        basketService.saveProductToBasket(basketDTO);
    }

    @GetMapping(value = {"/showProductInBasket/{userId}"})
    public List<BasketDTO> showProductInBasket(@PathVariable Integer userId) {
        return basketService.showProductInBasket(userId);
    }

    @GetMapping(value = {"/getBasketItems/{basketId}"})
    public List<BasketItemDTO> getBasketItems(@PathVariable Integer basketId) {
        return basketService.getBasketItems(basketId);
    }

    // 장바구니에 있는 모든 목록 삭제하기
    @DeleteMapping(value = {"/deleteAllProductInBasket/{userId}"})
    public void deleteAllProductInBasket(@PathVariable Integer userId){
        basketService.deleteAllProductInBasket(userId);
    }

    // 장바구니에 있는 목록 선택해서 삭제하기
    @DeleteMapping(value = {"/deleteProductByBasketIdInBasket/{userId}/{basketId}"})
    public void deleteProductByBasketIdInBasket(@PathVariable Integer userId,
                                                @PathVariable Integer basketId){
        basketService.deleteProductByBasketIdInBasket(userId,basketId);
    }

    // 장바구니에서 수량 변경하면 DB에 basketQuantitiy가 변경
    @PutMapping(value = {"/updateBasketQuantity2"})
    public void updateBasketQuantity2(@RequestBody BasketItemDTO basketItemDTO) {
        basketService.updateBasketQuantity2(basketItemDTO.getProductId(),basketItemDTO.getBasketId(), basketItemDTO.getBasketQuantity());
    }

    @GetMapping("/checkInventory/{storeId}/{productId}")
    public boolean checkInventory(@PathVariable Integer storeId, @PathVariable Integer productId) {
        return basketService.existsProductInInventory(storeId, productId);
    }

}
