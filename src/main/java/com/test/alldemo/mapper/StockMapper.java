package com.test.alldemo.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.alldemo.entity.seckill.StockDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
public interface StockMapper extends BaseMapper<StockDO> {
}
