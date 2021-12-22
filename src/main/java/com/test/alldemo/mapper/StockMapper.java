package com.test.alldemo.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.alldemo.entity.seckill.StockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author admin
 */
@Mapper
public interface StockMapper extends BaseMapper<StockDO> {
    @Update("update stock set sale = #{sale},version = #{version}+1 where id = #{id} and version = #{version}")
    Integer updateSale(@Param("id") Integer id, @Param("sale") Integer sale, @Param("version") Integer version);
}
