package com.Ecom.ProductService.Mapper;

import com.Ecom.ProductService.Dtos.FakeStoreRequestDTO;
import com.Ecom.ProductService.Dtos.FakeStoreResponseDTO;
import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;

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
}
