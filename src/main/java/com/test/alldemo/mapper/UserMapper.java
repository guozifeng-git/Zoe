package com.test.alldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.alldemo.entity.seckill.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
