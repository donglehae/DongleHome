package com.intflow.stock.controller;

import com.intflow.stock.service.KospiService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private KospiService kospiService;

    @GetMapping("/")
    public String index(Model model) throws IOException {
        List<Map<String, Object>> datas = kospiService.selectTest();

        System.out.println(datas);


        //model.addAttribute("kospi", kospi_quote);
        /*
        model.addAttribute("kosdaq", kosdaq_quote);
        model.addAttribute("fut", fut_quote);*/
        return "index";
    }
}
