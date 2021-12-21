package com.test.alldemo.controller;

import com.test.alldemo.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    /**
     * 下单接口：导致超卖的错误示范
     * @param sid
     * @return
     */
    @PostMapping("/createWrongOrder")
    public String createWrongOrder(@RequestParam("sid") int sid) {
        int id = 0;
        try {
            id = seckillService.createWrongOrder(sid);
            log.info("创建订单id: [{}]", id);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return String.valueOf(id);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
