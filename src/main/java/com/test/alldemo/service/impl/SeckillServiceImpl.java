package com.test.alldemo.service.impl;

import com.test.alldemo.entity.ErrorCodeEnum;
import com.test.alldemo.entity.seckill.StockDO;
import com.test.alldemo.entity.seckill.StockOrderDO;
import com.test.alldemo.exception.CustomException;
import com.test.alldemo.mapper.SeckillMapper;
import com.test.alldemo.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class SeckillServiceImpl implements SeckillService {

    @Autowired
    SeckillMapper seckillMapper;

    @Override
    public int createWrongOrder(int sid) {
        //校验库存
        StockDO stock = checkStock(sid);
        //扣库存
        saleStock(stock);
        //创建订单
        int id = createOrder(stock);
        return id;
    }

    @Override
    public StockDO checkStock(int sid) {
        StockDO stockDO = seckillMapper.selectById(sid);
        if (stockDO.getCount().equals(stockDO.getSale())){
            throw new CustomException(ErrorCodeEnum.STOCK_NOT_ENOUGH);
        }
        return stockDO;
    }

    @Override
    public int saleStock(StockDO stock) {
        stock.setSale(stock.getSale()+1);
        seckillMapper.updateById(stock);
    }

    @Override
    public int createOrder(StockDO stock) {
        StockOrderDO stockOrderDO = new StockOrderDO();
        stockOrderDO.setSid(stock.getId());
        stockOrderDO.setName(stock.getName(););
        stockOrderDO.setCreateTime(new Date());
    }
}
