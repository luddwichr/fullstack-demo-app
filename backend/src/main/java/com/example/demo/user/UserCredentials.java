package com.example.demo.user;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserCredentials {

    @Id
    private String username;

    private String password;

    @ElementCollection
    @JoinTable(
            name = "authorities",
            joinColumns = {@JoinColumn(name = "username")})
    @Column(name = "authority")
    private Set<String> roles;

    UserCredentials() {
        // required by JPA
    }

    UserCredentials(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }
}