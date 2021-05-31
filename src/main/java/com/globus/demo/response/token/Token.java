package com.globus.demo.response.token;

public class Token {
    private Integer id;

    private String token;

    public Token(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
