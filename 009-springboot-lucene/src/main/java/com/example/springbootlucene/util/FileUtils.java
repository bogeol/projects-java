package com.example.springbootlucene.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileUtils {

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


    public static void testCurrentWorkingDirPath() {
        System.out.println(System.getProperty("user.dir"));
    }

    public static void testFileMetaData() {
        Path path = null;
        try {
            path = Paths.get(ResourceUtils.getFile("classpath:lucene/files").getAbsolutePath());
            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println(basicFileAttributes.creationTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * reference:
     * - https://www.journaldev.com/875/java-read-file-to-string
     * - 对比NIO Files操作
     */
    public static String testReadFileToString(String filePath) {
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String separator = System.getProperty("line.separator"); // 文件行分隔符号【根据系统来】
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line = bufferedReader.readLine();
            int count = 0;
            while (line != null) {
                count++;
                String temp = count + "\t" + line;
                stringBuffer.append(temp);
                stringBuffer.append(separator);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }

    public static void v1() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:lucene/files");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String filePath = file.getAbsolutePath();
        List<Map> files = lsWithoutDir(filePath);
        files.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        testCurrentWorkingDirPath();
        testFileMetaData();
        v1();
        try {
            System.out.println(ResourceUtils.getFile("classpath:lucene/files").getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
