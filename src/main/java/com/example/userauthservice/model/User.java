package com.example.userauthservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseClass {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    @ManyToMany
    private List<Role> roleList;

}
