package com.test.alldemo.service;


import com.test.alldemo.entity.seckill.StockDO;

public interface SeckillService {
    int createWrongOrder(int sid);

    StockDO checkStock(int sid);

    int saleStock(StockDO stock);

    int createOrder(StockDO stock);

}
