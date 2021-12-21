package com.test.alldemo.entity.seckill;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author admin
 */
@Data
@TableName("user")
public class UserDO {
    private Integer id;
    private String userName;
}
