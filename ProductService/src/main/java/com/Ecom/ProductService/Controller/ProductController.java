package com.Ecom.ProductService.Controller;

import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;
import com.Ecom.ProductService.Exceptions.ProductNotFoundException;
import com.Ecom.ProductService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    /** Field injection
     * @Autowired
     * @Qualifier("fakeStoreService")
     * private ProductService productService;
     */
    private final ProductService productService;// since constructor injection is imutable so its good practice to make it final

    // since there are two implementation of productService(productSevice & fakeStoreService --bean names of impl classes) so we can choose which we want to inject with qualifier
    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("v1/products")
    public ResponseEntity<ProductResponseListDTO> getAllProducts(){
        ProductResponseListDTO productResponse = productService.getAllProducts();
        System.out.println(productResponse);
        return ResponseEntity.ok(productResponse);
    }
/*
    when I am simply throwing error ProductNotFoundException, its needs to handled as
     I am throwing on controller layer so it will directly throw exception in console but in client it will not show in desired way
     so to handle that we will do global exceptional handling of ProductNotFoundException
*/
    @GetMapping("v1/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) throws ProductNotFoundException {
        ProductResponseDTO productResponse = productService.getProductById(id);
            return ResponseEntity.ok(productResponse);
    }

    @PostMapping("v1/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponse = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("v1/products/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable String id){
        boolean productResponse = productService.deleteProductById(id);
        return ResponseEntity.ok(productResponse);
    }


}
