package com.test.alldemo.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.test.alldemo.service.SeckillService;
import com.test.alldemo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    @Autowired
    UserServiceImpl userService;

    /**
     * Guava令牌桶：每秒放行10个请求
     */
    RateLimiter rateLimiter = RateLimiter.create(33);

    /**
     * 下单接口：导致超卖的错误示范
     *
     * @param sid
     * @return
     */
    @GetMapping("/createWrongOrder")
    public String createWrongOrder(@RequestParam("sid") int sid) {
        int id = 0;
        try {
            id = seckillService.createWrongOrder(sid);
            log.info("Create orderId: [{}]", id);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存 + 令牌桶限流
     *
     * @param sid
     * @return
     */
    @GetMapping("/createOptimisticOrder")
    public String createOptimisticOrder(@RequestParam("sid") int sid) {
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            log.warn("Current limit, return failure");
            return "Purchase failure, insufficient inventory";
        }
        int surplusOrder;
        try {
            surplusOrder = seckillService.createOptimisticOrder(sid);
            log.info("Purchase succeeded, remaining inventory is: [{}]", surplusOrder);
        } catch (Exception e) {
            log.error("Purchase failed：[{}]", e.getMessage());
            return "Purchase failure, insufficient inventory";
        }
        return String.format("Purchase succeeded, remaining inventory is：%d", surplusOrder);
    }


    @GetMapping("/getVerifyHash")
    public String getVerifyHash(@RequestParam("sid") Integer sid,
                                @RequestParam("userId") Integer userId) {
        String hash;
        try {
            hash = userService.getVerifyHash(sid, userId);
        } catch (Exception e) {
            log.error("获取验证hash失败，原因：[{}]", e.getMessage());
            return "获取验证hash失败";
        }
        return String.format("请求抢购验证hash值为：%s", hash);

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
