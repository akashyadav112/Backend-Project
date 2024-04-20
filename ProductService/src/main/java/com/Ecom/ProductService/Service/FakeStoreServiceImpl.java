package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service("fakeStoreService")
public class FakeStoreServiceImpl implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;
    public FakeStoreServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductResponseDTO getProductById(String id) {
        String url = "https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate =  restTemplateBuilder.build();
        // jvm can not figure out which type it is
        return restTemplate.getForEntity(url,ProductResponseDTO.class).getBody();
    }

    @Override
    public ProductResponseListDTO getAllProducts() {
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<ProductResponseDTO[]> productResponse = restTemplateBuilder
                .build()
                .getForEntity(url,ProductResponseDTO[].class);

        ProductResponseListDTO productResponseListDTO = new ProductResponseListDTO();
        Arrays.stream(productResponse.getBody())
                .forEach(productResponseDTO -> productResponseListDTO.getProducts().add(productResponseDTO));

        return productResponseListDTO;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        String url = "https://fakestoreapi.com/products";
        return restTemplateBuilder
                .build()
                .postForEntity(url,productRequestDTO,ProductResponseDTO.class)
                .getBody();
    }

    @Override
    public boolean deleteProductById(String id) {
        String url = "https://fakestoreapi.com/products/"+id;
        restTemplateBuilder.build().delete(url); // restemplate delete return type is void so even if you are returning a object still it will be void
        return true;
    }
}
