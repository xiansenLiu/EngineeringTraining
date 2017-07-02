package com.example.server.domain;

import javax.persistence.*;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:39 PM
 */
@Entity
@Table(name = "engineering_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String account;
    private String username;

    private String password;

    public User() {
    }

    public User(String account, String username, String password) {
        this.account = account;
        this.username = username;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
