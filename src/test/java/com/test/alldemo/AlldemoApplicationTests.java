package com.test.alldemo;

import com.test.alldemo.entity.User1;
import com.test.alldemo.entity.User2;
import com.test.alldemo.service.User1Service;
import com.test.alldemo.service.User2Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@SpringBootTest
class AlldemoApplicationTests {

    @Autowired
    User1Service user1Service;

    @Autowired
    User2Service user2Service;

    /**
     * "22"，"33"都成功插入
     * 不开启外围事务时，插入"22"，"33"在自己的事务中独立运行，外围方法异常不影响内部插入数据的方法。
     * 下面呢的所有方法中 User1ServiceImpl，User2ServiceImpl中都开启了事务
     */
    @Test
    void notransaction_exception_required_required() {
        User1 user1 = new User1();
        user1.setName("22");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("33");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * "zs"插入成功，"ls"插入失败
     * 不开启外围事务，插入"zs","ls"在自己的事务中独立运行，插入"ls"报错自己回滚，不会影响"zs"的插入
     */
    @Test
    void notransaction_required_required_exception() {
        User1 user1 = new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("ls");
        user2Service.addRequiredException(user2);
    }

    /**
     *Propagation_REQUIRED:如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务
     * 开启外围事务(最常用的)，外围方法开启事务，内部方法加入外围方法事务，外围抛出RuntimeException回滚，内部也回滚
     * "zs"插入失败，"ls"插入失败
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required(){
        User1 user1=new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("ls");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * Propagation_REQUIRED:如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务
     * 开启外围事务，内部方法加入外围方法事务
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception(){
        User1 user1=new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("ls");
        user2Service.addRequiredException(user2);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception_try(){
        User1 user1=new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("ls");
        try {
            user2Service.addRequiredException(user2);
        } catch (Exception e) {
            log.info("方法回滚");
        }
    }



}
