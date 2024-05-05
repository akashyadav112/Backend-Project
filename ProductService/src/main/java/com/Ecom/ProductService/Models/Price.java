package com.Ecom.ProductService.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Price extends BaseModel{
    private String currency;
    private double amount;
    private int discount;
}
