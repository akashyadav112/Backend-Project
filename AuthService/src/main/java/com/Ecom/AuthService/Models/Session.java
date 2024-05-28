package com.Ecom.AuthService.Models;

import com.Ecom.AuthService.Enums.SessionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "Session_db")
public class Session extends BaseModel{
    private String token;
    private Date expiryAt;
    private Date loginAt;


    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL) //  jpa will create one table of enums in db
    private SessionStatus sessionStatus;

}
