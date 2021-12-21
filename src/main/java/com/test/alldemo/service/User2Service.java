package com.test.alldemo.service;

import com.test.alldemo.entity.User2DO;

public interface User2Service {
    void addRequired(User2DO user);

    void addRequiredException(User2DO user);

    void addRequiresNew(User2DO user);

    void addRequiresNewException(User2DO user);

    void addNested(User2DO user);

    void addNestedException(User2DO user);
}
