package com.Ecom.ProductService.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class ProductResponseListDTO{
    private List<ProductResponseDTO> products;
    public ProductResponseListDTO(){
        this.products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ProductResponseListDTO{" +
                "products=" + products +
                '}';
    }
}
