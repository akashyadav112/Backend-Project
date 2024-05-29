package com.Ecom.AuthService.Service.Impl;

import com.Ecom.AuthService.DTO.SignUpRequestDTO;
import com.Ecom.AuthService.DTO.UserDTO;
import com.Ecom.AuthService.Enums.SessionStatus;
import com.Ecom.AuthService.Exceptions.InvalidCredentialsException;
import com.Ecom.AuthService.Exceptions.InvalidTokenException;
import com.Ecom.AuthService.Exceptions.UserNotFoundException;
import com.Ecom.AuthService.Models.Session;
import com.Ecom.AuthService.Models.User;
import com.Ecom.AuthService.Repository.SessionRepository;
import com.Ecom.AuthService.Repository.UserRepository;
import com.Ecom.AuthService.Service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.MultiValueMapAdapter;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO signUp(SignUpRequestDTO signUp) {
        User user = new User();
        user.setEmailId(signUp.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signUp.getPassword()));
        userRepository.save(user);
        return UserDTO.from(user);
    }

    @Override
    public ResponseEntity<UserDTO> login(String email, String password) {
        // 1.fetch user data based on email/username
        Optional<User> optionalUser = userRepository.findByEmailId(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User Does not Exist");
        }
        User user = optionalUser.get();
        // 2.verify its password
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        // 3.generate one token
        String token = RandomStringUtils.randomAlphanumeric(30);

        // 4.check current whether user has any current active session.
        Optional<Session> optionalSession = sessionRepository.findUserLatestSession(user.getId());
        if(optionalSession.isPresent()
                && optionalSession.get().getSessionStatus().equals(SessionStatus.ACTIVE)){
            optionalSession.get().setSessionStatus(SessionStatus.ENDED);
            sessionRepository.save(optionalSession.get());
        }

        // 5.create one session and store the token in the session
        Session session = new Session();
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setLoginAt(new Date());
        sessionRepository.save(session);

        UserDTO userDTO = UserDTO.from(user);
        MultiValueMapAdapter<String,String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        // 6.set the token in the cookie headers
        headers.set(HttpHeaders.SET_COOKIE,"auth-token:"+token);
        return new ResponseEntity<>(userDTO, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> logOut(String token, Long userId){
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token,userId);
        if(sessionOptional.isEmpty()){
            throw new InvalidTokenException("token is invalid");
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return ResponseEntity.ok().build();
    }

    @Override
    public SessionStatus validate(String token, Long userId) {
        //TODO check expiry // Jwts Parser -> parse the encoded JWT token to read the claims

        //verifying from DB if session exists
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty() || sessionOptional.get().getSessionStatus().equals(SessionStatus.ENDED)) {
            throw new InvalidTokenException("token is invalid");
        }
        return SessionStatus.ACTIVE;
    }

    @Override
    public ResponseEntity<List<Session>> getAllSession(){
        List<Session> sessions = sessionRepository.findAll();
        return ResponseEntity.ok(sessions);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

}
