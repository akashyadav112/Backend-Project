package com.Ecom.AuthService.Repository;

import com.Ecom.AuthService.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    Optional<Session> findByTokenAndUser_Id(String token, Long id); // User_Id means from User Table, its id(as User is in Session Table)
    @Query(value = CustomQueries.FIND_USER_LATEST_SESSION, nativeQuery = true)
    Optional<Session> findUserLatestSession(Long id); // User_Id means from User Table, its id(as User is in Session Table)
}
