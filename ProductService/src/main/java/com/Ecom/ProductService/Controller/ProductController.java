package com.Ecom.ProductService.Controller;

import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;
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

    // since there are two implementation of productService so we can choose which we want to inject with qualifier
    public ProductController(@Qualifier("fakeStoreService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("v1/products")
    public ResponseEntity<ProductResponseListDTO> getAllProducts(){
        ProductResponseListDTO productResponse = productService.getAllProducts();
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("v1/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id){
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
