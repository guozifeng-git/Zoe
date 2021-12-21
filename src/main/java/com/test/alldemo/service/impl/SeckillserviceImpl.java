package com.test.alldemo.service.impl;

import com.test.alldemo.entity.seckill.StockDO;
import com.test.alldemo.service.SeckillService;

public class SeckillserviceImpl implements SeckillService {
    @Override
    public int createWrongOrder(int sid) {
        return 0;
    }

    @Override
    public StockDO checkStock(int sid) {
        return null;
    }

    @Override
    public int saleStock(StockDO stock) {
        return 0;
    }

    @Override
    public int createOrder(StockDO stock) {
        return 0;
    }
}
