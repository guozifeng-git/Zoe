package com.test.alldemo.service.impl;

import com.test.alldemo.constants.Cachekey;
import com.test.alldemo.entity.ErrorCodeEnum;
import com.test.alldemo.entity.seckill.StockDO;
import com.test.alldemo.entity.seckill.UserDO;
import com.test.alldemo.exception.CustomException;
import com.test.alldemo.mapper.StockMapper;
import com.test.alldemo.mapper.StockOrderMapper;
import com.test.alldemo.mapper.UserMapper;
import com.test.alldemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StockMapper stockMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static final String SALT = "randomString";

    @Override
    public String getVerifyHash(Integer sid, Integer userId) {
        UserDO userDO = userMapper.selectById(userId);
        if (userDO == null) {
            throw new CustomException(ErrorCodeEnum.USER_DOES_NOT_EXIST);
        }
        log.info("用户信息：[{}]", userDO.toString());
        StockDO stockDO = stockMapper.selectById(sid);
        if (stockDO == null) {
            throw new CustomException(ErrorCodeEnum.STOCK_DOES_NOT_EXIST);
        }
        log.info("商品信息：[{}]", stockDO.toString());

        String salt = SALT + sid + userId;
        String key = Cachekey.SECKILL + sid + ":" + userId;
        String value = DigestUtils.md5DigestAsHex(salt.getBytes(StandardCharsets.UTF_8));
        redisTemplate.opsForValue().set(key, value, 3600, TimeUnit.SECONDS);
        log.info("Redis写入：[{}] [{}]", key, value);
        return value;
    }
}
