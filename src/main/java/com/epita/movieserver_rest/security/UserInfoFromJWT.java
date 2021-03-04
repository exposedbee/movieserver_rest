package com.epita.movieserver_rest.security;

public class UserInfoFromJWT {
    private String username;
    private int id;

    public UserInfoFromJWT(int jti, String sub) {
        this.username =  sub;
        this.id = jti;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
