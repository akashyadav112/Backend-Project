package com.Ecom.ProductService.Dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class JwtPayloadDTO {
    private long expiryAt;
    private String[] roles;
    private long userId;
    private long createdAt;

    public JwtPayloadDTO(){}
    @JsonCreator
    public JwtPayloadDTO(@JsonProperty("createdAt") long createdAt,
                         @JsonProperty("roles") String[] roles,
                         @JsonProperty("expiryAt") long expiryAt,
                         @JsonProperty("userId") long userId) {
        this.createdAt = createdAt;
        this.roles = roles;
        this.expiryAt = expiryAt;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JwtPayloadDTO{" +
                "expiryAt=" + expiryAt +
                ", roles=" + Arrays.toString(roles) +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                '}';
    }
}
