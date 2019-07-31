package com.firstproject.basicecommercewebsite.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private int user_id;

    public User() {

    }

    public User(@NotEmpty(message = "Name cannot be empty") String name, @NotEmpty(message = "Email cannot be empty") @Email(message = "Email should be valid") String email, @Size(min = 10, max = 10, message = "Enter a valid phone no") @Pattern(regexp = "(^$|[0-9]{10})") String phoneno, @NotEmpty(message = "Address cannot be empty") String address) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.address = address;
    }

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min=10,max=10,message = "Enter a valid phone no")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneno;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
