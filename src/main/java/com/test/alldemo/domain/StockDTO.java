package com.test.alldemo.domain;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class StockDTO {
    private Integer id;
    private String name;
    private Integer count;
    private Integer sale;
    private Integer version;
}
