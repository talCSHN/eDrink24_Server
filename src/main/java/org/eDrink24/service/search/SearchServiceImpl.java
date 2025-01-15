package org.eDrink24.service.search;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.domain.product.Product;
import org.eDrink24.dto.product.ProductDTO;
import org.eDrink24.repository.product.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    ProductRepository productRepository;
    ModelMapper modelMapper;
    public SearchServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        String serachKeyword = "%"+keyword+"%";
        List<Product> products = productRepository.searchByKeyword(serachKeyword);
        List<ProductDTO> productsDTO = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return productsDTO;
    }
}
