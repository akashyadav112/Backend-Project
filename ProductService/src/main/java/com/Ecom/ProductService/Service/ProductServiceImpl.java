package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService{
    @Override
    public ProductResponseDTO getProductById(String id) {
        return null;
    }

    @Override
    public ProductResponseListDTO getAllProducts() {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public boolean deleteProductById(String id) {
        return true;
    }
}
