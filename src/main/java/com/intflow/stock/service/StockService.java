package com.intflow.stock.service;

import com.intflow.stock.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockService {
    @Autowired
    private StockMapper stockMapper;

    public List<Map<String, Object>> selectKospi() {
        return stockMapper.selectKospi();
    }

    public List<Map<String, Object>> selectKosdaq() {
        return stockMapper.selectKosdaq();
    }

    public List<Map<String, Object>> selectFut() {
        return stockMapper.selectFut();
    }
}
