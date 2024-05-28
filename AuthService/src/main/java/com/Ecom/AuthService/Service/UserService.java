package com.Ecom.AuthService.Service;

import com.Ecom.AuthService.Models.Role;
import com.Ecom.AuthService.Models.User;

import java.util.List;

public interface UserService {
    public User getUserDetails(String id);
    public void setUseRoles(List<Role> roles);
}
