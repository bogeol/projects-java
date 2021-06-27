package com.example.springbootlucenev1.service;

import com.example.springbootlucenev1.model.FileModel;
import com.example.springbootlucenev1.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    public List<FileModel> findAllFileModels() {
        return fileRepository.findAllFileModels();
    }

    public List<FileModel> findAllFileModelsByAbsPath(String absPath) {
        return fileRepository.findAllFileModelsByAbsPath(absPath);
    }
}
