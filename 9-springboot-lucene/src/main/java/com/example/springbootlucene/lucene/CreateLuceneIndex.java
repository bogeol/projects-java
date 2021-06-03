package com.example.springbootlucene.lucene;

import com.example.springbootlucene.model.FileModel;
import com.example.springbootlucene.repository.FileRepository;
import com.example.springbootlucene.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

@Component
public class CreateLuceneIndex {

    @Value("${LUCENE_INDEX_PATH}")
    String LUCENE_INDEX_PATH;


    @Value("${ABS_LUCENE_INDEX_PATH}")
    String ABS_LUCENE_INDEX_PATH;


    @Autowired
    FileRepository fileRepository;

    public void createLuceneIndex() {
        /**
         * 写索引流程：
         * 1. Directory 【索引目录】
         * 2. IndexWriterConfig(Analyzer)【索引配置】
         * 3. IndexWriter(index_dir, index_config)【★★★】
         * 4. Document(s)
         * 5. commit
         * 6. close
         *
         */
        try {
            // 1. index dir
//            String path = ResourceUtils.getFile(LUCENE_INDEX_PATH).getAbsolutePath();
            Directory directory = FSDirectory.open(Paths.get(ABS_LUCENE_INDEX_PATH));

            // 2. index config
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            // 3. index writer
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

            // 4. add document(s) to index
            List<FileModel> fileModelList = fileRepository.findAllFileModels();
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
            // 5.commit / 6.close
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
//        createLuceneIndex();
    }

}
