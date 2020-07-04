package com.intflow.stock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StockMapper {
    List<Map<String, Object>> selectKospi();
    List<Map<String, Object>> selectKosdaq();
    List<Map<String, Object>> selectFut();
}
