package com.test.transactional.service;

import com.test.transactional.entity.User1;

public interface User1Service {
    void addRequired(User1 user1);

    void addRequiresNew(User1 user);

    void addNested(User1 user);
}
