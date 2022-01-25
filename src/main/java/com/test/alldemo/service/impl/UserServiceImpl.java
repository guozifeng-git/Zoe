package com.test.alldemo.service.impl;

import com.test.alldemo.constants.Cachekey;
import com.test.alldemo.constants.SeckillConstants;
import com.test.alldemo.common.ErrorCodeEnum;
import com.test.alldemo.entity.seckill.StockDO;
import com.test.alldemo.entity.seckill.UserDO;
import com.test.alldemo.exception.CustomException;
import com.test.alldemo.mapper.StockMapper;
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




    @Override
    public String getVerifyHash(Integer sid, Integer userId) {
        UserDO userDO = userMapper.selectById(userId);
        if (userDO == null) {
            throw new CustomException(ErrorCodeEnum.USER_DOES_NOT_EXIST,"不存在");
        }
        log.info("User information：[{}]", userDO.toString());
        StockDO stockDO = stockMapper.selectById(sid);
        if (stockDO == null) {
            throw new CustomException(ErrorCodeEnum.ITEM_DOES_NOT_EXIST);
        }
        log.info("Commodity information：[{}]", stockDO.toString());

        String salt = SeckillConstants.SALT+ sid + userId;
        String key = Cachekey.SECKILL + sid + ":" + userId;
        String value = DigestUtils.md5DigestAsHex(salt.getBytes(StandardCharsets.UTF_8));
        redisTemplate.opsForValue().set(key, value, 3600, TimeUnit.SECONDS);
        log.info("Redis write in：[{}] [{}]", key, value);
        return value;
    }

    @Override
    public int addUserCount(Integer userId) {
        String key = Cachekey.ACCESS_FREQUENCY + userId;
        String value = redisTemplate.opsForValue().get(key);
        int count = 0;
        if (value == null) {
            redisTemplate.opsForValue().set(key, "0", 3600, TimeUnit.SECONDS);
        } else {
            count = Integer.parseInt(value) + 1;
            redisTemplate.opsForValue().set(key, String.valueOf(count),3600,TimeUnit.SECONDS);
        }
        return count;
    }

    @Override
    public boolean getUserIsBanned(Integer userId) {
        String key = Cachekey.ACCESS_FREQUENCY+userId;
        String accessNum = redisTemplate.opsForValue().get(key);
        if (accessNum == null) {
            log.error("The user did not access the request authentication value record");
            return true;
        }
        return Integer.parseInt(accessNum) > SeckillConstants.MAX_NUM;
    }
}
