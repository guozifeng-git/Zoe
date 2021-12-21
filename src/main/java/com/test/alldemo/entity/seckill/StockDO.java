package com.test.alldemo.entity.seckill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author admin
 */
@Data
@TableName("stock")
public class StockDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer count;
    private Integer sale;
    private Integer version;
}
