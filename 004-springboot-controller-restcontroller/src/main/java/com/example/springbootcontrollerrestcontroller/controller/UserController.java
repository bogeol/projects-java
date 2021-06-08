package com.example.springbootcontrollerrestcontroller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.IntStream;

@Controller
@RequestMapping("user")
public class UserController {

    public static List randomNumbers() {
        List randomNumbers = new ArrayList();
        IntStream.range(1, 10).forEach(x -> {
            randomNumbers.add(Math.random());
        });
        return randomNumbers;
    }

    /**
     * 关注于：结果写入HTTP响应体
     */
    @GetMapping("rest") // focus on rest
    @ResponseBody
    public Object rest(@RequestParam(value = "name", defaultValue = "spring") String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("data", new Date());
            put("randomNumbers", randomNumbers());
        }};
    }

    /**
     * 关注于：模型视图
     * - model + return "view"
     * - map + return "view"
     * - HttpServletRequest + return "view"
     * - ModelAndView("view")
     */
    @GetMapping("model_return_view") // focus on view
    public String model_return_view(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("date", new Date());
        model.addAttribute("randomNumbers", randomNumbers());
        return "model_return_view";
    }

    @GetMapping("map_return_view")
    public String map_return_view(@RequestParam("name") String name, Map<String, Object> map) {
        map.put("name", name);
        map.put("date", new Date());
        map.put("randomNumbers", randomNumbers());
        return "map_return_view";
    }

    @GetMapping("HttpServletRequest_return_view")
    public String HttpServletRequest_return_view(@RequestParam("name") String name, HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute("name", name);
        httpServletRequest.setAttribute("date", new Date());
        httpServletRequest.setAttribute("randomNumbers", randomNumbers());

        return "HttpServletRequest_return_view";
    }

    @GetMapping("modelandview")
    public ModelAndView modelandview(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView("modelandview");
        modelAndView.addObject("name", name);
        modelAndView.addObject("date", new Date());
        modelAndView.addObject("randomNumbers", randomNumbers());

        return modelAndView;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
