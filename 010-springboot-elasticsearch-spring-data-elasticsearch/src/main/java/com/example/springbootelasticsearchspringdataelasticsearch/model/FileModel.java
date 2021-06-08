package com.example.springbootelasticsearchspringdataelasticsearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@Document(indexName = "file_model", createIndex = false)
public class FileModel {
    @Id
    String id;

    @Field(type = FieldType.Text, name = "fileName")
    String fileName;

    @Field(type = FieldType.Keyword, name = "fileType")
    String fileType;

    @Field(type = FieldType.Date, name = "fileCreationTime")
    String fileCreationTime;

    @Field(type = FieldType.Date, name = "fileLastAccessTime")
    String fileLastAccessTime;

    @Field(type = FieldType.Date, name = "fileLastModifiedTime")
    String fileLastModifiedTime;

    @Field(type = FieldType.Long, name = "fileSize")
    Long fileSize;

    @Field(type = FieldType.Text, name = "fileContent")
    String fileContent;


}
