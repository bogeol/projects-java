package com.example.springbootelasticsearchspringdataelasticsearch.controller;

import com.example.springbootelasticsearchspringdataelasticsearch.service.ElasticsearchFileModelService;
import com.example.springbootelasticsearchspringdataelasticsearch.util.FileUtils;
import org.elasticsearch.client.ml.OpenJobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("file_model")
public class ElasticsearchFileModelController {

    @Autowired
    ElasticsearchFileModelService elasticsearchFileModelService;


    @DeleteMapping("index")
    public Object deleteFileModelIndex() {
        return elasticsearchFileModelService.deleteFileModelIndex();
    }

    @PostMapping("index")
    public Object createFileModelIndex() {
        return elasticsearchFileModelService.createFileModelIndex();
    }

    @PostMapping("mock")
    public void mockFileModel() {
        elasticsearchFileModelService.saveAllFileModels(FileUtils.findAllFileModelsByClassPath("classpath:files"));
    }

    @GetMapping("id")
    public Object findById(@RequestBody Map map) {
        return elasticsearchFileModelService.findById((String) map.get("id"));
    }

    @GetMapping("fileName")
    public Object findByFileName(@RequestBody Map map) {
        return elasticsearchFileModelService.findByFileName((String) map.get("fileName"));
    }

    @GetMapping("fileSize")
    public Object findByFileSizeRange(@RequestBody Map<String, Integer> map) {
        return elasticsearchFileModelService.findByFileSizeRange(map.get("min"), map.get("max"));
    }

    @GetMapping("fileType")
    public Object findByFileType(@RequestBody Map<String, String> map) {
        return elasticsearchFileModelService.findByFileType(map.get("fileType"));
    }

    @GetMapping("findWithMutiFieldAndFuzzy")
    public Object findWithMutiFieldAndFuzzy(@RequestBody Map<String, String> map) {
        return elasticsearchFileModelService.findWithMutiFieldAndFuzzy(map.get("text"));
    }

    @GetMapping("findWithWildCardQuery")
    public Object findWithWildCardQuery(@RequestBody Map<String, String> map) {
        return elasticsearchFileModelService.findWithWildCardQuery(map.get("text"));
    }
}
