package com.test.alldemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User2 {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String name;
}
