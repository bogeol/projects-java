package com.example.springbootlucenev1.repository;

import com.example.springbootlucenev1.model.FileModel;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class LuceneRepository {

    @Autowired
    IndexWriter indexWriter;

    public void saveAll(List<FileModel> fileModelList) {
        fileModelList.stream().forEach(fileModel -> {
            Document document = new Document();
            Field fieldFileName = new StringField("fileName", fileModel.getFileName(), Field.Store.YES);
            Field fieldFileCreationTime = new StringField("fileCreationTime", fileModel.getFileCreationTime(), Field.Store.YES);
            Field fieldFileLastAccessTime = new StringField("fileLastAccessTime", fileModel.getFileLastAccessTime(), Field.Store.YES);
            Field fieldFileLastModifiedTime = new StringField("fileLastModifiedTime", fileModel.getFileLastModifiedTime(), Field.Store.YES);
            Field fieldFileSize = new StringField("fileSize", String.valueOf(fileModel.getFileSize()), Field.Store.YES);
            Field fieldFileContent = new TextField("fileContent", fileModel.getFileContent(), Field.Store.YES);
            document.add(fieldFileName);
            document.add(fieldFileCreationTime);
            document.add(fieldFileLastAccessTime);
            document.add(fieldFileLastModifiedTime);
            document.add(fieldFileSize);
            document.add(fieldFileContent);
            try {
                indexWriter.addDocument(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
