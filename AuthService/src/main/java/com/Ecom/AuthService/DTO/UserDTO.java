package com.Ecom.AuthService.DTO;

import com.Ecom.AuthService.Models.Role;
import com.Ecom.AuthService.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String emailId;
    private Set<Role> roles = new HashSet<>();

    public static UserDTO from(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setEmailId(user.getEmailId());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
