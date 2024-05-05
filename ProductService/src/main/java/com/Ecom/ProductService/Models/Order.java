package com.Ecom.ProductService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ecom_orders")
public class Order extends BaseModel{
    @ManyToMany
    List<Product> productList;
}
/*
 ***************************************************************************
 * Product : Orders
 * 1       :  M
 * M       :  1
 * after cross product ->  M : M (many to many can also be unidirectional)
 * we do not need orders for this product so we will mention all products in order
 ****************************************************************************
 * Table will look like this
 * Order
 * id (only one attribute)
 */
