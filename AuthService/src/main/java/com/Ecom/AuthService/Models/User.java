package com.Ecom.AuthService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "ECOM_USER")
public class User extends BaseModel {
    private String emailId;
    private String password; // password will not be plain text

    @ManyToMany
    private Set<Role> roles = new HashSet<>();
}
