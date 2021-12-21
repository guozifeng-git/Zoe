package com.test.alldemo.service;


import com.test.alldemo.entity.seckill.StockDO;

public interface SeckillService {
    int createWrongOrder(int sid);

    StockDO checkStock(int sid);

    void saleStock(StockDO stock);

    int createOrder(StockDO stock);

    int createOptimisticOrder(int sid);

    void saleOptimisticStock(StockDO stock);

}
