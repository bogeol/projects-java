package com.example.springbootlucenev1.repository;


import com.example.springbootlucenev1.model.FileModel;
import com.example.springbootlucenev1.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FileRepository {


    @Value("${FILE_PATH}")
    String FILE_PATH;

    public List<FileModel> findAllFileModels() {
        //--------------------------基于classpath路径的
        String path = null;
        try {
            path = ResourceUtils.getFile(FILE_PATH).getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //--------------------------
        List<Map> files = FileUtils.lsWithoutDir(path);
        return files.stream().map(file -> {
            File fileObj = (File) file.get("fileObj");
            BasicFileAttributes basicFileAttributes = (BasicFileAttributes) file.get("basicFileAttributes");
            String fileName = fileObj.getName();
            String fileCreationTime = basicFileAttributes.creationTime().toString();
            String fileLastModifiedTime = basicFileAttributes.lastModifiedTime().toString();
            String fileLastAccessTime = basicFileAttributes.lastAccessTime().toString();
            long fileSize = basicFileAttributes.size();
            String fileContent = null;
            try {
                fileContent = Files
                        .readAllLines(Paths.get(fileObj.getAbsolutePath()), StandardCharsets.UTF_8)
                        .stream()
                        .collect(Collectors.joining(System.getProperty("line.separator")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return FileModel.builder()
                    .fileName(fileName)
                    .fileCreationTime(fileCreationTime)
                    .fileLastAccessTime(fileLastAccessTime)
                    .fileLastModifiedTime(fileLastModifiedTime)
                    .fileSize(fileSize)
                    .fileContent(fileContent)
                    .build();
        }).collect(Collectors.toList());
    }

    public List<FileModel> findAllFileModelsByAbsPath(String absPath) {
        List<Map> files = FileUtils.lsWithoutDir(Paths.get(absPath).toAbsolutePath().toString());
        return files.stream().map(file -> {
            File fileObj = (File) file.get("fileObj");
            BasicFileAttributes basicFileAttributes = (BasicFileAttributes) file.get("basicFileAttributes");
            String fileName = fileObj.getName();
            String fileCreationTime = basicFileAttributes.creationTime().toString();
            String fileLastModifiedTime = basicFileAttributes.lastModifiedTime().toString();
            String fileLastAccessTime = basicFileAttributes.lastAccessTime().toString();
            long fileSize = basicFileAttributes.size();
            String fileContent = null;
            try {
                fileContent = Files
                        .readAllLines(Paths.get(fileObj.getAbsolutePath()), StandardCharsets.UTF_8)
                        .stream()
                        .collect(Collectors.joining(System.getProperty("line.separator")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return FileModel.builder()
                    .fileName(fileName)
                    .fileCreationTime(fileCreationTime)
                    .fileLastAccessTime(fileLastAccessTime)
                    .fileLastModifiedTime(fileLastModifiedTime)
                    .fileSize(fileSize)
                    .fileContent(fileContent)
                    .build();
        }).collect(Collectors.toList());
    }


}