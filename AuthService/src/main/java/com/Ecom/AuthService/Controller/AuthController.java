package com.Ecom.AuthService.Controller;

import com.Ecom.AuthService.DTO.LoginRequestDTO;
import com.Ecom.AuthService.DTO.SignUpRequestDTO;
import com.Ecom.AuthService.DTO.UserDTO;
import com.Ecom.AuthService.DTO.ValidateTokenRequestDTO;
import com.Ecom.AuthService.Enums.SessionStatus;
import com.Ecom.AuthService.Models.Session;
import com.Ecom.AuthService.Models.User;
import com.Ecom.AuthService.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO signUp){
        UserDTO userDTO = authService.signUp(signUp);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO request) {
        return authService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable("id") Long userId, @RequestHeader("token") String token) {
        return authService.logOut(token, userId);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDTO request) {
        SessionStatus sessionStatus = authService.validate(request.getToken(), request.getUserId());

        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }

    //below APIs are only for learning purposes, should not be present in actual systems
    @GetMapping("/session")
    public ResponseEntity<List<Session>> getAllSession(){
        return authService.getAllSession();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return authService.getAllUsers();
    }

}
