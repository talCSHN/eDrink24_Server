package org.eDrink24.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.dto.product.ProductDTO;
import org.eDrink24.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/api/search/{keyword}")
    public ResponseEntity<List<ProductDTO>> searchProduct(@PathVariable String keyword) {
        try {
            List<ProductDTO> products = searchService.searchProducts(keyword);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
