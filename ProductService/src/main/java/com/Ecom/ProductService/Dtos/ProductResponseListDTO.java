package com.Ecom.ProductService.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ProductResponseListDTO{
    private List<ProductResponseDTO> products;
    public ProductResponseListDTO(){
        this.products = new ArrayList<>();
    }

    public List<ProductResponseDTO> getProducts() {
        return this.products;
    }
}
