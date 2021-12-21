package com.test.alldemo.entity.seckill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("stock_order")
public class StockOrderDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sid;
    private String name;
    private Date createTime;
}
