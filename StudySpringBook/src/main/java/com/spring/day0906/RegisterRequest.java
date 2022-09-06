package com.spring.day0906;

import lombok.Getter;

@Getter
public class RegisterRequest {

    private String email;
    private String password;
    private String confirmPassword;
    private String name;

    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setConfirmPassword(String confirmPassword){this.confirmPassword = confirmPassword;}
    public void setName(String name){this.name = name;}
    public boolean isPasswordEqualToConfirmPassword(){
        return password.equals(confirmPassword);
    }
}
