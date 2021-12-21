package com.test.alldemo.entity.seckill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("stock_order")
public class StockOrderDO {
    //雪花
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private Integer sid;
    private String name;
    private Date createTime;
}
