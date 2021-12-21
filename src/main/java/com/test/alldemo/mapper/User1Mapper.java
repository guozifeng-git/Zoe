package com.test.alldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.alldemo.entity.User1DO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface User1Mapper extends BaseMapper<User1DO> {
}
