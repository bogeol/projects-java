package com.example.springbootlucenev1;

import com.example.springbootlucenev1.model.FileModel;
import com.example.springbootlucenev1.repository.FileRepository;
import com.example.springbootlucenev1.service.FileService;
import com.example.springbootlucenev1.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    FileService fileService;

    @Autowired
    LuceneService luceneService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<FileModel> fileModelList = fileService.findAllFileModels();
        luceneService.saveAll(fileModelList);

        String absPath1 = "U:\\projects\\test-projects\\springboot-lucene-v1\\src\\main\\java\\com\\example\\springbootlucenev1\\model";
        luceneService.saveAll(fileService.findAllFileModelsByAbsPath(absPath1));

        String absPath2 = "U:\\projects\\test-projects\\springboot-lucene-v1\\src\\main\\java\\com\\example\\springbootlucenev1\\repository";
        luceneService.saveAll(fileService.findAllFileModelsByAbsPath(absPath2));

        String absPath3 = "U:\\projects\\test-projects\\springboot-lucene-v1\\src\\main\\java\\com\\example\\springbootlucenev1\\service";
        luceneService.saveAll(fileService.findAllFileModelsByAbsPath(absPath3));


    }
}
