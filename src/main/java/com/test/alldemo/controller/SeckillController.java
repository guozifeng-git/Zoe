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
     * 只能单机限流，分布式限流用redis+lua脚本
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

    /**
     * 获取验证值
     * @return
     */
    @GetMapping("/getVerifyHash")
    public String getVerifyHash(@RequestParam("sid") Integer sid,
                                @RequestParam("userId") Integer userId) {
        String hash;
        try {
            hash = userService.getVerifyHash(sid, userId);
        } catch (Exception e) {
            log.error("Failed to get validation hash, reason ：[{}]", e.getMessage());
            return "Failed to get validation hash";
        }
        return String.format("The hash value of the request for rush purchase verification is：%s", hash);

    }
    /**
     * 要求验证的抢购接口
     * @param sid
     * @return
     */
    @GetMapping("/createOrderWithVerifiedUrl")
    public String createOrderWithVerifiedUrl (@RequestParam("sid") Integer sid,
                                              @RequestParam("userId") Integer userId,
                                              @RequestParam("verifyHash") String verifyHash){
        int stockLeft;
        try {
            stockLeft = seckillService.createVerifiedOrder(sid, userId, verifyHash);
            log.info("Purchase succeeded, remaining inventory is: [{}]", stockLeft);
        } catch (Exception e) {
            log.error("Purchase failed：[{}]", e.getMessage());
            return e.getMessage();
        }
        return String.format("Purchase succeeded, remaining inventory is：%d", stockLeft);
    }

    /**
     * 要求验证的抢购接口 + 单用户限制访问频率
     * @param sid
     * @return
     */
    @GetMapping("/createOrderWithVerifiedUrlAndLimit")
    public String createOrderWithVerifiedUrlAndLimit(@RequestParam(value = "sid") Integer sid,
                                                     @RequestParam(value = "userId") Integer userId,
                                                     @RequestParam(value = "verifyHash") String verifyHash) {
        int stockLeft;
        try {
            int count = userService.addUserCount(userId);
            log.info("Number of user visits: [{}]", count);
            boolean isBanned = userService.getUserIsBanned(userId);
            if (isBanned) {
                return "Purchase failed, frequency limit exceeded";
            }
            stockLeft = seckillService.createVerifiedOrder(sid, userId, verifyHash);
            log.info("Purchase succeeded, remaining inventory is: [{}]", stockLeft);
        } catch (Exception e) {
            log.error("Purchase failed：[{}]", e.getMessage());
            return e.getMessage();
        }
        return String.format("Purchase succeeded, remaining inventory is：%d", stockLeft);
    }

}
