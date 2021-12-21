package com.test.alldemo.entity.seckill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author admin
 */
@Data
public class StockDO {
    //雪花
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String name;
    private Integer count;
    private Integer sale;
    private Integer version;
}
