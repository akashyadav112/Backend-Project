package com.Ecom.AuthService.Service;

import com.Ecom.AuthService.DTO.SignUpRequestDTO;
import com.Ecom.AuthService.DTO.UserDTO;
import com.Ecom.AuthService.Enums.SessionStatus;
import com.Ecom.AuthService.Models.Session;
import com.Ecom.AuthService.Models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    public UserDTO signUp(SignUpRequestDTO signUp);

    public ResponseEntity<UserDTO> login(String email, String password);
    public ResponseEntity<Void> logOut(String token, Long userId);
    public SessionStatus validate(String token, Long userId);

    /** below methods for testing purpose only **/
    public ResponseEntity<List<Session>> getAllSession();
    public ResponseEntity<List<User>> getAllUsers();



    }
