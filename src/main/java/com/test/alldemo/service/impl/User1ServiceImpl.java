package com.test.alldemo.service.impl;

import com.test.alldemo.entity.Transactional.User1DO;
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

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void addRequired(User1DO user1DO) {
        user1Mapper.insert(user1DO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1DO user){
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public void addNested(User1DO user){
        user1Mapper.insert(user);
    }
}
