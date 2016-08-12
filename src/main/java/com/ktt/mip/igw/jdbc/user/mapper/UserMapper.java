package com.ktt.mip.igw.jdbc.user.mapper;


import com.ktt.mip.igw.domain.User;

public interface UserMapper {

    User selectUser(String userId);
}
