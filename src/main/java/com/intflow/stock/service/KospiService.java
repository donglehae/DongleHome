package com.intflow.stock.service;

import com.intflow.stock.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KospiService {
    @Autowired
    private TestMapper testMapper;

    public List<Map<String, Object>> selectTest() {
        return testMapper.selectKospi();
    }
}
