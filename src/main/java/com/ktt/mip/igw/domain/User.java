package com.ktt.mip.igw.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Collection;

@Alias("User")
public class User implements Serializable {

    private int seq;
    private String userId;
    private String username;
    private String phone;


    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    private Collection<Role> roles;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "seq=" + seq +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                '}';
    }
}
