package com.test.alldemo.service;

import com.test.alldemo.entity.User1DO;

public interface User1Service {
    void addRequired(User1DO user1DO);

    void addRequiresNew(User1DO user);

    void addNested(User1DO user);
}
