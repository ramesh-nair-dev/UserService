package com.example.userauthservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseClass{
    private String tokenVal;
    private Date expirationDate;
    @ManyToOne
    private User user;
}
