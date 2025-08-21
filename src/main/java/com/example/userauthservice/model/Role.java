package com.example.userauthservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@Entity
public class Role extends BaseClass {
    private String name;

}
