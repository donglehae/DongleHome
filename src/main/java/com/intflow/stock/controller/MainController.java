package com.intflow.stock.controller;

import com.intflow.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public String index(Model model) throws IOException {
        List<Map<String, Object>> datasKospi = stockService.selectKospi();
        List<Map<String, Object>> datasKosdaq = stockService.selectKosdaq();
        List<Map<String, Object>> datasFut = stockService.selectFut();

        for(int i = 0; i < datasKospi.size(); i++) {
            model.addAttribute("label_" + (i+1), datasKospi.get(i).get("time"));
            model.addAttribute("kospi_" + (i+1), Double.parseDouble(datasKospi.get(i).get("quote").toString().replaceAll(",", "")));
            model.addAttribute("kosdaq_" + (i+1), Double.parseDouble(datasKosdaq.get(i).get("quote").toString().replaceAll(",", "")));
            model.addAttribute("fut_" + (i+1), Double.parseDouble(datasFut.get(i).get("quote").toString().replaceAll(",", "")));
        }

        return "index";
    }
}