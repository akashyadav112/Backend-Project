package com.Ecom.ProductService.Repository;

import com.Ecom.ProductService.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitle(String title);
    Product findByTitleAndDescription(String title, String description); // select * from Product where title = ? and description = ?
    Product findByTitleOrDescription(String title, String description); // select * from Product where title = ? or description = ?
/**
    Product findByPriceLessThanEqual(double price);
    Product findByPriceGreaterThanEqual(double price);
    Product findByPriceBetweenStartPriceAndEndPrice(double startPrice, double endPrice);

    we are getting error in this because price is an object but we are passing double,
    so we should do Price_amount : this means amount attribute of Price of object.
**/

    @Query(value = CustomQueries.FIND_PRODUCT_BY_TITLE, nativeQuery = true)
    Product findProductByTitleLike(String title);

    @Query(value = "select * from Products", nativeQuery = true)
    Product findAllProducts(String title, UUID id);

}
