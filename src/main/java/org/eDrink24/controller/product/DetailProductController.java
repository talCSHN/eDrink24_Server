package org.eDrink24.controller.product;

import org.eDrink24.dto.product.DetailProductDTO;
import org.eDrink24.service.product.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailProductController {

    ProductService productService;

    public DetailProductController(ProductService productService) {
        this.productService = productService;
    }

    // 제품 사진 누르면 해당 제품 상세 설명 보여줌.
    @GetMapping(value = {"/showDetailProduct/{category1}/{category2}/{productId}"})
    public DetailProductDTO showDetailProduct(@PathVariable String category1,@PathVariable String category2,@PathVariable Integer productId) {
        return productService.showDetailProduct(category1,category2,productId);
    }

}
