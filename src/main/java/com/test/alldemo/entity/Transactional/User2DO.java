package com.test.alldemo.entity.Transactional;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User2DO {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String name;
}
