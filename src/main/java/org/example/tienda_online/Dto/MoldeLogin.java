package org.example.tienda_online.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class MoldeLogin {

    private String nickname;

    private String password;


    public MoldeLogin() {
    }

    public MoldeLogin(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MoldeLogin{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
