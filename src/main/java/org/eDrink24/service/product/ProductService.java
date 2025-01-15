package org.eDrink24.service.product;

import org.eDrink24.dto.product.DetailProductDTO;
import org.eDrink24.dto.product.ProductDTO;

import java.util.List;

import java.util.Map;

public interface ProductService {

    // 상품 필터링
    public List<ProductDTO> productFilter(String sortProduct, String sortDirection);

    public List<ProductDTO> showAllProduct();

    // category1에 해당하는 제품만 보여줌.
    public List<ProductDTO> showProductByCategory1(String category1);

    // category2에 해당하는 제품만 보여줌.
    public List<ProductDTO> showProductByCategory2(String category2);

    // 제품 사진 누르면 해당 제품 상세 설명 보여줌.
    public DetailProductDTO showDetailProduct(String category1,String category2,Integer productId);

    public String findProductNameByProductId(int productId);
}
