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
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
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
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
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
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception_try(){
        User1 user1=new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("ls");
        try {
            user2Service.addRequiredException(user2);
        } catch (Exception e) {
            log.info("RollBACK");
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
     * "zs_new"插入成功，"ls_new_exception"插入失败
     * Propagation_REQUIRES_NEW:创建新事务，无论当前存不存在事务，都创建新事务。
     * 外围不开启事务，2个插入方法分别开启自己的事务，插入"ls_new"方法抛出异常不会影响别的事务
     */
    @Test
    public void notransaction_requiresNew_requiresNew_exception(){
        User1 user1=new User1();
        user1.setName("zs_new");
        user1Service.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("ls_new_exception");
        user2Service.addRequiresNewException(user2);
    }

    /**
     * "zs"插入失败，其他两个插入成功
     * Propagation_REQUIRES_NEW:创建新事务，无论当前存不存在事务，都创建新事务。
     *外围开启方法，插入"zs"的内部方法加入外围事务（他的事务传播是Propagation.REQUIRED)，另外2个内部方法是自己的事务。
     */
    @Test
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew(){
        User1 user1=new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("ls_new");
        user2Service.addRequiresNew(user2);

        User2 user3=new User2();
        user3.setName("ww_new");
        user2Service.addRequiresNew(user3);
        throw new RuntimeException();
    }

    /**
     * "ls_new"插入成功，其他两个插入失败
     *Propagation_REQUIRES_NEW:创建新事务，无论当前存不存在事务，都创建新事务。
     * 外围开启事务，插入"ww_new_exception"的方法抛出异常回滚，回滚抛出异常外围方法感知到也会回滚，所以插入"zs"也回滚
     */
    @Test
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception(){
        User1 user1=new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("ls_new");
        user2Service.addRequiresNew(user2);

        User2 user3=new User2();
        user3.setName("ww_new_exception");
        user2Service.addRequiresNewException(user3);
    }

    /**
     * 网上说："ww_new_exception"插入失败，其他两插入成功  实际情况："ls_new"插入成功其他失败
     *按理说应该是插入"ww_new_exception"的失败以后不会影响外围事务，但是实际上是会影响的
     */
    @Test
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try(){
        User1 user1=new User1();
        user1.setName("zs");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("ls_new");
        user2Service.addRequiresNew(user2);
        User2 user3=new User2();
        user3.setName("ww_new_exception");
        try {
            user2Service.addRequiresNewException(user3);
        } catch (Exception e) {
            log.info("RollBACK");
        }
    }

    /**
     * "zs_nested","ls_nested"都成功插入
     * Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
     * 在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
     */
    @Test
    public void notransaction_exception_nested_nested(){
        User1 user1=new User1();
        user1.setName("zs_nested");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("ls_nested");
        user2Service.addNested(user2);
        throw new RuntimeException();
    }

    /**
     * "zs_nested"插入成功，"ls_nested_exception"插入失败
     * Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
     * 在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
     */
    @Test
    public void notransaction_nested_nested_exception(){
        User1 user1=new User1();
        user1.setName("zs_nested");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("ls_nested_exception");
        user2Service.addNestedException(user2);
    }

    /**
     * 都插入失败，两个内部方法会变成外围方法的子事务，外围方法回滚都回滚
     */
    @Test
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void transaction_exception_nested_nested(){
        User1 user1=new User1();
        user1.setName("zs_nested");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("ls_nested");
        user2Service.addNested(user2);
        throw new RuntimeException();
    }


    /**
     * 都会失败，内部抛出异常被外围方法感知，外围方法回滚所有都回滚
     */
    @Test
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception(){
        User1 user1=new User1();
        user1.setName("zs_nested");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("ls_nested_exception");
        user2Service.addNestedException(user2);
    }

    /**
     * 这里也全部回滚了，按道理只应该回滚插入"ls_nested_exception_try"
     */
    @Test
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception_try(){
        User1 user1=new User1();
        user1.setName("zs_nested");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("ls_nested_exception_try");
        try {
            user2Service.addNestedException(user2);
        } catch (Exception e) {
            log.info("RollBACK");
        }
    }

}
