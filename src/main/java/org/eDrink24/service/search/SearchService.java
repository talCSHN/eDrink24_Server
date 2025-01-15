package org.eDrink24.service.search;

import org.eDrink24.dto.product.ProductDTO;
import java.util.List;

public interface SearchService {
    public List<ProductDTO> searchProducts(String keyword);
}
