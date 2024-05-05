package com.Ecom.ProductService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @OneToOne
    private Price price;

    @ManyToOne
    private Category category;

}
/*
 * Product : Price
 * 1       : 1
 ***************************************************************************
 * Product : Category  ( it should not be bidirectional, we do not need products in category
 * 1       :  1
 * M        : 1
 * after cross product -> M:1
 ***************************************************************************
 * Product : Orders
 * 1       :  M
 * M       :  1
 * after cross product ->  M : M (many to many can also be unidirectional)
 * we do not need orders for this product so we will mention all products in order
 *
 */