package com.Ecom.ProductService.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreRequestDTO {
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
