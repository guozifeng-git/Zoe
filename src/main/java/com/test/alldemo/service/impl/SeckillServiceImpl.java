package com.test.alldemo.service.impl;

import com.test.alldemo.entity.ErrorCodeEnum;
import com.test.alldemo.entity.seckill.StockDO;
import com.test.alldemo.entity.seckill.StockOrderDO;
import com.test.alldemo.exception.CustomException;
import com.test.alldemo.mapper.StockMapper;
import com.test.alldemo.mapper.StockOrderMapper;
import com.test.alldemo.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    StockMapper stockMapper;

    @Autowired
    StockOrderMapper stockOrderMapper;

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
        StockDO stockDO = stockMapper.selectById(sid);
        if (stockDO.getCount().equals(stockDO.getSale())) {
            throw new CustomException(ErrorCodeEnum.STOCK_NOT_ENOUGH);
        }
        return stockDO;
    }

    @Override
    public void saleStock(StockDO stock) {
        stock.setSale(stock.getSale() + 1);
        stockMapper.updateById(stock);
    }

    @Override
    public int createOrder(StockDO stock) {
        StockOrderDO stockOrderDO = new StockOrderDO();
        stockOrderDO.setSid(stock.getId());
        stockOrderDO.setName(stock.getName());
        stockOrderDO.setCreateTime(new Date());
        stockOrderMapper.insert(stockOrderDO);
        return stock.getCount() - (stock.getSale())-1;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public int createOptimisticOrder(int sid) {
        //校验库存
        StockDO stock = checkStock(sid);
        //扣库存
        saleOptimisticStock(stock);
        //创建订单
        return createOrder(stock);
    }

    @Override
    public void saleOptimisticStock(StockDO stock) {
        Integer integer = stockMapper.updateSale(stock.getId(), stock.getSale() + 1, stock.getVersion());
        if (integer != 1) {
            throw new CustomException(ErrorCodeEnum.TRY_ACQUIRE_FAIL);
        }
    }
}
