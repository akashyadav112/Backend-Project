package com.Ecom.ProductService.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDTO {

    private Long userId;
    ;
    private String token;

    public ValidateTokenDTO(){}
    public ValidateTokenDTO(Long id, String token){
        this.userId = id;
        this.token = token;
    }

    @Override
    public String toString() {
        return "ValidateTokenDTO{" +
                "id=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
