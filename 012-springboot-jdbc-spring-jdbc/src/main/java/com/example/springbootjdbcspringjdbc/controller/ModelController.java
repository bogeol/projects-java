package com.example.springbootjdbcspringjdbc.controller;

import com.example.springbootjdbcspringjdbc.model.Model;
import com.example.springbootjdbcspringjdbc.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("model")
public class ModelController {

    @Autowired
    @Qualifier("modelServiceImpl")
    ModelService modelService;

    @PostMapping
    public Object save(@RequestBody Model model) {
        return modelService.save(model);
    }

    @GetMapping
    public Object getAll() {
        return modelService.getAll();
    }

    @DeleteMapping
    public Object deleteAll() {
        return modelService.deleteAll();
    }

    @GetMapping("{id}")
    public Object getById(@PathVariable int id) {
        return modelService.getById(id);
    }

    @PutMapping("{id}")
    public Object updateById(@PathVariable int id, @RequestBody Model model) {
        return modelService.updateById(id, model);
    }

    @DeleteMapping("{id}")
    public Object deleteById(@PathVariable int id) {
        return modelService.deleteById(id);
    }
}
