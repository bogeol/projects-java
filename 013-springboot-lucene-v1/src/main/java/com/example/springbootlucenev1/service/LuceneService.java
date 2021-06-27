package com.example.springbootlucenev1.service;

import com.example.springbootlucenev1.model.FileModel;
import com.example.springbootlucenev1.repository.LuceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuceneService {
    @Autowired
    LuceneRepository luceneRepository;

    public void saveAll(List<FileModel> fileModelList) {
        luceneRepository.saveAll(fileModelList);
    }
    
}
