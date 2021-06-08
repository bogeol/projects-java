package com.example.springbootelasticsearchspringdataelasticsearch.util;

import com.example.springbootelasticsearchspringdataelasticsearch.model.FileModel;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    public static String getFileTypeOnWindows(File file) {
        String fileName = file.getName();
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1 && lastIndexOf != fileName.length()) {
            return "txt";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    public static List<Map> lsWithoutDir(String path) {
        return Stream.of(new File(path).listFiles())
                .filter(file -> !file.isDirectory())
                .map(file -> {
                    Map map = new HashMap<>();
                    Path absPath = Paths.get(file.getAbsolutePath());
                    BasicFileAttributes basicFileAttributes = null;
                    try {
                        basicFileAttributes = Files.readAttributes(absPath, BasicFileAttributes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    map.put("fileObj", file);
                    map.put("basicFileAttributes", basicFileAttributes);
                    return map;
                })
                .collect(Collectors.toList());
    }

    public static List<FileModel> findAllFileModelsByClassPath(String classPath) {

        String path = null;
        try {
            path = ResourceUtils.getFile(classPath).getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Map> files = FileUtils.lsWithoutDir(path);
        return files
                .stream()
                .map(file -> {
                    File fileObj = (File) file.get("fileObj");
                    BasicFileAttributes basicFileAttributes = (BasicFileAttributes) file.get("basicFileAttributes");
                    String fileName = fileObj.getName();
                    String fileType = FileUtils.getFileTypeOnWindows(fileObj);
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
                            .id(fileName)
                            .fileName(fileName)
                            .fileType(fileType)
                            .fileCreationTime(fileCreationTime)
                            .fileLastAccessTime(fileLastAccessTime)
                            .fileLastModifiedTime(fileLastModifiedTime)
                            .fileSize(fileSize)
                            .fileContent(fileContent)
                            .build();
                }).collect(Collectors.toList());
    }

}
