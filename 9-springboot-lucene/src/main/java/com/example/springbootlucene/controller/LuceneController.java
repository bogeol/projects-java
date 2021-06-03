package com.example.springbootlucene.controller;

import com.example.springbootlucene.lucene.SearchLuceneIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuceneController {

    @Autowired
    SearchLuceneIndex searchLuceneIndex;


}
