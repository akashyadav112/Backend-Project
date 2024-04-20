package com.Ecom.ProductService.Clients;

import com.Ecom.ProductService.Dtos.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreApiClient {
    private final RestTemplateBuilder restTemplateBuilder;

    private final String baseUrl;
    private final String endpoint;
    FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder, @Value("${fake.api.url}") String baseUrl,@Value("${fake.api.path.products}") String endpoint){
        this.restTemplateBuilder = restTemplateBuilder;
        this.baseUrl = baseUrl;
        this.endpoint = endpoint;
    }

    public FakeStoreResponseDTO getProductById(String id) {
        String url = baseUrl + endpoint + "/" + id;
        RestTemplate restTemplate =  restTemplateBuilder.build();
        // jvm can not figure out which type it is, that's why we mention the response type class
        return restTemplate.getForEntity(url, FakeStoreResponseDTO.class).getBody();
    }

    public List<FakeStoreResponseDTO> getAllProducts() {
        String url = baseUrl + endpoint;
        ResponseEntity<FakeStoreResponseDTO[]> fakeStoreResponse = restTemplateBuilder
                .build()
                .getForEntity(url,FakeStoreResponseDTO[].class);
        return List.of(fakeStoreResponse.getBody());
    }

    public FakeStoreResponseDTO createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        String url = baseUrl + endpoint;
        return restTemplateBuilder
                .build()
                .postForEntity(url, fakeStoreRequestDTO,FakeStoreResponseDTO.class)
                .getBody();
    }

    public boolean deleteProductById(String id) {
        String url = baseUrl + endpoint + "/" + id;
        restTemplateBuilder.build().delete(url); // rest-template delete return type is void so even if you are returning a object still it will be void
        return true;
    }
}
