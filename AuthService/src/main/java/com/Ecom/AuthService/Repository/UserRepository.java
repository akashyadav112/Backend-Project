package com.Ecom.AuthService.Repository;

import com.Ecom.AuthService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailId(String email);
}
