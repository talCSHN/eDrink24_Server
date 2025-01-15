package org.eDrink24.service.product;

import org.eDrink24.config.ProductMapper;
import org.eDrink24.dto.product.DetailProductDTO;
import org.eDrink24.dto.product.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements  ProductService {

   ProductMapper productMapper;

   public ProductServiceImpl(ProductMapper productMapper) {
      this.productMapper = productMapper;
   }

    @Override
	  public List<ProductDTO> productFilter(String sortProduct, String sortDirection) {
        Map<String, Object> params = new HashMap<>();
        params.put("sortProduct", sortProduct);
        params.put("sortDirection", sortDirection);
        return productMapper.productFilter(params);
    }
    
    @Override
    public List<ProductDTO> showAllProduct() {
        return productMapper.showAllProduct();
    }

    @Override
    public List<ProductDTO> showProductByCategory1(String category1) {
        return productMapper.showProductByCategory1(category1);
    }

    @Override
    public List<ProductDTO> showProductByCategory2(String category2) {
        return productMapper.showProductByCategory2(category2);
    }

    @Override
    public DetailProductDTO showDetailProduct(String category1,String category2,Integer productId) {
        return productMapper.showDetailProduct(category1,category2,productId);
    }

    @Override
    public String findProductNameByProductId(int productId) {
        return productMapper.findProductNameByProductId(productId);
    }

}
