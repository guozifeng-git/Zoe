package com.test.alldemo.service.impl;

import com.test.alldemo.entity.User1;
import com.test.alldemo.mapper.User1Mapper;
import com.test.alldemo.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1ServiceImpl implements User1Service {

    @Autowired
    User1Mapper user1Mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addRequired(User1 user1) {
        user1Mapper.insert(user1);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1 user){
        user1Mapper.insert(user);
    }
}
