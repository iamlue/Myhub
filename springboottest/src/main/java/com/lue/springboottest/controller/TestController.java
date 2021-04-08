package com.lue.springboottest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lue
 * @date 2021/4/2 --21:08
 */
@Slf4j
@Controller
public class TestController {
    @ResponseBody
    @RequestMapping("run")
    public String run(){
        log.info("Hello world");
        return "Hello World";
    }
    @GetMapping("/car/{id}")
    public String car(@PathVariable("id") Integer id){
        return String.valueOf(id);
    }
    @RequestMapping("header")
    public HashMap<String, String>  header(@RequestHeader Map<String,String> map){
        HashMap<String, String> map1 = new HashMap<String, String>();
        return (HashMap<String, String>) map;
    }
    @GetMapping("/write")
    public String write(HttpServletRequest request) {
        request.setAttribute("msg", "导入msg");
        request.setAttribute("name","lue");
        return "forward:/success";
    }
    @ResponseBody
    @GetMapping("/success")
    public String read(@RequestAttribute("msg") String msg,@RequestAttribute("name") String name){
        return name;
    }
}
