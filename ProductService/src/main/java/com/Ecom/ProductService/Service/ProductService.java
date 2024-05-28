package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;
import com.Ecom.ProductService.Exceptions.ProductNotFoundException;

public interface ProductService {
    ProductResponseDTO getProductById(String id) throws ProductNotFoundException;
    ProductResponseListDTO getAllProducts();
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    boolean deleteProductById(String id);
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException;
    }
