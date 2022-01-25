package com.test.alldemo.controller;

import com.test.alldemo.common.AopTest;
import com.test.alldemo.common.ResponseResult;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 */
@RestController
@RequestMapping(value = "/aop")
public class AopController {
    @GetMapping(value = "/getTest")
    @AopTest(key = "xxx,xxx",num = 5)
    public ResponseResult aopTest() {
        return ResponseResult.success("ok");
    }

    @PostMapping(value = "/postTest")
    public ResponseResult aopTest2(@RequestParam("id") String id) {
        return ResponseResult.success("ok");
    }
}
