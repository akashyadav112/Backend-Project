package com.Ecom.ProductService.Mapper;

import com.Ecom.ProductService.Dtos.*;
import com.Ecom.ProductService.Models.Product;

import java.util.List;

public class ProductMapper {

    public static FakeStoreRequestDTO productRequestToFakeStoreRequest(ProductRequestDTO productRequestDTO){
        FakeStoreRequestDTO fakeStoreRequestDTO = new FakeStoreRequestDTO();
        fakeStoreRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreRequestDTO.setImage(productRequestDTO.getImage());
        fakeStoreRequestDTO.setTitle(productRequestDTO.getTitle());
        fakeStoreRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreRequestDTO.setDescription(productRequestDTO.getDescription());
        return fakeStoreRequestDTO;
    }

    public static ProductResponseDTO fakeStoreRequestToProductRequest(FakeStoreResponseDTO fakeStoreResponseDTO){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(fakeStoreResponseDTO.getId());
        productResponseDTO.setCategory(fakeStoreResponseDTO.getCategory());
        productResponseDTO.setImage(fakeStoreResponseDTO.getImage());
        productResponseDTO.setTitle(fakeStoreResponseDTO.getTitle());
        productResponseDTO.setPrice(fakeStoreResponseDTO.getPrice());
        productResponseDTO.setDescription(fakeStoreResponseDTO.getDescription());
        return productResponseDTO;
    }

    public static ProductResponseListDTO convertProductsToProductResponseListDTO(List<Product> products){
        ProductResponseListDTO productResponseListDTO = new ProductResponseListDTO();
        for(Product p : products){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(p.getId());
            productResponseDTO.setImage(p.getImage());
            productResponseDTO.setTitle(p.getTitle());
            productResponseDTO.setPrice(p.getPrice().getAmount());
            productResponseDTO.setDescription(p.getDescription());
            productResponseDTO.setCategory(p.getCategory().getCategoryName());
            productResponseListDTO.getProducts().add(productResponseDTO);
        }
        return productResponseListDTO;
    }

    public static ProductResponseDTO convertProductToProductResponseDTO(Product p){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(p.getId());
        productResponseDTO.setImage(p.getImage());
        productResponseDTO.setTitle(p.getTitle());
        productResponseDTO.setPrice(p.getPrice().getAmount());
        productResponseDTO.setDescription(p.getDescription());
        productResponseDTO.setCategory(p.getCategory().getCategoryName());
        return productResponseDTO;
    }
}
