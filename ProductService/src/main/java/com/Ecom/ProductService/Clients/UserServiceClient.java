package com.Ecom.ProductService.Clients;

import com.Ecom.ProductService.Dtos.ValidateTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Class to talk to Auth Server(UserService MS) to validate the user using Auth-server
 */
@Component
public class  UserServiceClient{
    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${userservice.api.url}")
    private  String AuthServerBaseUrl;
    @Value("${userservice.api.path.validate}")
    private String validateUser;

    UserServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public  String validateToken(ValidateTokenDTO validateTokenDTO) {
        String validateTokenAPI = AuthServerBaseUrl + validateUser;
        RestTemplate restTemplate = restTemplateBuilder.build();
        // jvm can not figure out which type it is, that's why we mention the response type class
        ResponseEntity<String> validateTokenResponse = restTemplate.postForEntity(validateTokenAPI, validateTokenDTO, String.class);
        return validateTokenResponse.getBody();
    }
}
