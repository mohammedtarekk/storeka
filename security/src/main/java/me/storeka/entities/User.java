package me.storeka.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="users", schema = "access")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String firstNameEN;
    private String lastNameEN;
    private String firstNameAR;
    private String lastNameAR;
    private String country;
    private String city;
    private String state;
    private String street;
    private Boolean isLocked;
    private Boolean isVerified;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "access")
    @JsonIgnore
    private Set<Role> roles;

}
