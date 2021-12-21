package com.test.alldemo.service.impl;

import com.test.alldemo.entity.Transactional.User2DO;
import com.test.alldemo.mapper.User2Mapper;
import com.test.alldemo.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User2ServiceImpl implements User2Service {
    @Autowired
    User2Mapper user2Mapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void addRequired(User2DO user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void addRequiredException(User2DO user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User2DO user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(User2DO user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public void addNested(User2DO user){
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public void addNestedException(User2DO user){
        user2Mapper.insert(user);
        throw new RuntimeException();
    }
}
