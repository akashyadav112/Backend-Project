package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;

public interface ProductService {
    ProductResponseDTO getProductById(String id);
    ProductResponseListDTO getAllProducts();
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    boolean deleteProductById(String id);
}
