package me.security.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="users", schema = "access")
@Data
public class User implements UserDetails {

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
    private Boolean isExpired;
    private Boolean isCredentialsExpired;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "access")
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getCode()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return !getIsExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getIsLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !getIsCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return !getIsLocked();
    }
}
