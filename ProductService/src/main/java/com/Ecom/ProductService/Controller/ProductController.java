package com.Ecom.ProductService.Controller;

import com.Ecom.ProductService.Clients.UserServiceClient;
import com.Ecom.ProductService.Dtos.*;
import com.Ecom.ProductService.Exceptions.InvalidTokenException;
import com.Ecom.ProductService.Exceptions.ProductNotFoundException;
import com.Ecom.ProductService.Service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
public class ProductController {

    /** Field injection
     * @Autowired
     * @Qualifier("fakeStoreService")
     * private ProductService productService;
     */
    private final ProductService productService;// since constructor injection is imutable so its good practice to make it final

    private final UserServiceClient userServiceClient;
    // since there are two implementation of productService(productSevice & fakeStoreService --bean names of impl classes) so we can choose which we want to inject with qualifier
    public ProductController(@Qualifier("productService") ProductService productService, UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
    }

    @GetMapping("v1/products")
    public ResponseEntity<ProductResponseListDTO> getAllProducts(@RequestHeader("token") String token){
        try{
            validateToken(token);
            ProductResponseListDTO productResponse = productService.getAllProducts();
            System.out.println(productResponse);
            return ResponseEntity.ok(productResponse);
        }catch (JsonProcessingException e){
            throw new RuntimeException("System Error");
        }
    }
/*
    when I am simply throwing error ProductNotFoundException, its needs to handled as
     I am throwing on controller layer so it will directly throw exception in console but in client it will not show in desired way
     so to handle that we will do global exceptional handling of ProductNotFoundException
*/
    @GetMapping("v1/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id,@RequestHeader("token") String token) throws ProductNotFoundException, JsonProcessingException {
        validateToken(token);
        ProductResponseDTO productResponse = productService.getProductById(id);
            return ResponseEntity.ok(productResponse);
    }

    @PostMapping("v1/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO,
                                                            @RequestHeader("token") String token) throws JsonProcessingException {
        validateToken(token);
        ProductResponseDTO productResponse = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("v1/products/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable String id,@RequestHeader("token") String token) throws JsonProcessingException {
        validateToken(token);
        boolean productResponse = productService.deleteProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    /**
     * Method to validate the token
     * @param token
     */
    public void validateToken(String token) throws JsonProcessingException {
        // decoding jwk token's payload where we can get the user id that we saved while generating in Auth server
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper objectMapper = new ObjectMapper();
        JwtPayloadDTO jwtPayloadDTO = objectMapper.readValue(payload,JwtPayloadDTO.class);
        ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO(jwtPayloadDTO.getUserId(),token);
        String result = userServiceClient.validateToken(validateTokenDTO);
        if(!SessionStatus.ACTIVE.name().equals(result)){
            throw new InvalidTokenException("Token is not valid");
        }
    }

}
