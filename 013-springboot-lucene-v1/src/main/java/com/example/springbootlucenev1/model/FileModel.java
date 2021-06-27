package com.example.springbootlucenev1.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FileModel { // based on file system
    String fileName;
    String fileCreationTime;
    String fileLastAccessTime;
    String fileLastModifiedTime;
    long fileSize;
    String fileContent;
}