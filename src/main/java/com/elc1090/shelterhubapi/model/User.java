package com.elc1090.shelterhubapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.List.of;

@Entity(name = "tb_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String cpf;
    @JsonIgnore
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority roleFunctionary = new SimpleGrantedAuthority("ROLE_FUNCTIONARY");
        SimpleGrantedAuthority roleVolunteer = new SimpleGrantedAuthority("ROLE_VOLUNTEER");

        if (this.role == UserRole.ADMIN) return of(roleAdmin, roleFunctionary, roleVolunteer);
        if (this.role == UserRole.FUNCTIONARY) return of(roleFunctionary, roleVolunteer);
        return of(roleVolunteer);
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return cpf;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}