package com.test.alldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.alldemo.entity.seckill.StockOrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
public interface StockOrderMapper extends BaseMapper<StockOrderDO> {
}
