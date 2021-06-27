package com.example.springbootlucenev1.config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;


@Component
public class LuceneConfig {

    @Value("${LUCENE_INDEX_PATH}")
    String LUCENE_INDEX_PATH;

    @Bean
    public Directory directory() {
        Directory directory = null;
        try {
            directory = FSDirectory.open(Paths.get(LUCENE_INDEX_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory;
    }

    @Bean
    public Analyzer smartChineseAnalyzer() {
        return new SmartChineseAnalyzer();
    }

    @Bean
    public Analyzer ikAnalyzer() {
        return new IKAnalyzer();
    }

    @Bean
    public Analyzer standardAnalyzer() {
        return new StandardAnalyzer();
    }

    @Bean
    public IndexWriterConfig indexWriterConfig(@Qualifier("smartChineseAnalyzer") Analyzer analyzer) {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        indexWriterConfig.setRAMBufferSizeMB(256.0);
        return indexWriterConfig;
    }

    @Bean
    public IndexWriter indexWriter(Directory directory, IndexWriterConfig indexWriterConfig) {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexWriter.deleteAll();
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    @Bean
    @Scope("prototype") // TODO: re-read > for get updated index commit
    public IndexReader indexReader(Directory directory) {
        IndexReader indexReader = null;
        try {
            indexReader = DirectoryReader.open(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexReader;
    }

}
