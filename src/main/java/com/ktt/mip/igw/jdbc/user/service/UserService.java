package com.ktt.mip.igw.jdbc.user.service;

import com.ktt.mip.igw.domain.User;
import com.ktt.mip.igw.jdbc.user.mapper.UserMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;


    @HystrixCommand(groupKey = "사용자연동", commandKey = "사용자조회", fallbackMethod = "defaultQueryUser")
    @ServiceActivator(inputChannel = "userHttpChannel")
    public User check(Message<String> message){


        logger.info(message.getPayload());



        return new User(message.getPayload());

        //userMapper.selectUser();

    }

    public User defaultQueryUser(Message<String> message){
        return new User();
    }
}
