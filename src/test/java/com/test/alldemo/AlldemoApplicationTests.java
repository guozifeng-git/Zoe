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
     * Propagation_REQUIRED:如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务
     * 不开启外围事务时，插入"22"，"33"在自己的事务中独立运行，外围方法异常不影响内部插入数据的方法。
     * 下面的方法中 User1ServiceImpl，User2ServiceImpl中都开启了Propagation_REQUIRED事务
     */
    @Test
    public void notransaction_exception_required_required() {
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
    public void notransaction_required_required_exception() {
        User1 user1 = new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("ls");
        user2Service.addRequiredException(user2);
    }

    /**
     * "zs","ls"都插入失败
     *Propagation_REQUIRED:如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务
     * 开启外围事务(最常用的)，外围方法开启事务，内部方法加入外围方法事务，外围抛出RuntimeException回滚，内部也回滚
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
     * "zs","ls"都插入失败
     * Propagation_REQUIRED:如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务
     * 开启外围事务，内部方法加入外围方法事务,内部方法抛出异常后被外围方法感知，整体回滚
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

    /**
     * "zs","ls"都插入失败
     * Propagation_REQUIRED:如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务
     * 外围方法开启事务，内部事务抛出异常后即使被catch不被外围事务感知，但仍然会回滚
     */
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

    /**
     * "zs_new","ls_new"都插入成功
     * 下面的所有方法中 User1ServiceImpl，User2ServiceImpl中都开启了Propagation_REQUIRES_NEW事务
     * Propagation_REQUIRES_NEW:创建新事务，无论当前存不存在事务，都创建新事务。
     * 不开启外围事务时，插入"zs_new"，"ls_new"在自己的事务中独立运行，外围方法异常不影响内部插入数据的方法。
     */
    @Test
    public void notransaction_exception_requiresNew_requiresNew(){
        User1 user1=new User1();
        user1.setName("zs_new");
        user1Service.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("ls_new");
        user2Service.addRequiresNew(user2);
        throw new RuntimeException();

    }

    /**
     * "zs_new"插入成功，"ls_new"插入失败
     * Propagation_REQUIRES_NEW:创建新事务，无论当前存不存在事务，都创建新事务。
     * 外围不开启事务，2个插入方法分别开启自己的事务，插入"ls_new"方法抛出异常不会影响别的事务
     */
    @Test
    public void notransaction_requiresNew_requiresNew_exception(){
        User1 user1=new User1();
        user1.setName("zs_new");
        user1Service.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("ls_new");
        user2Service.addRequiresNewException(user2);
    }

}
