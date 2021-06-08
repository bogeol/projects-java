package com.example.springbootlucene.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchLuceneIndex {
    @Value("${LUCENE_INDEX_PATH}")
    String LUCENE_INDEX_PATH;

    @Value("${ABS_LUCENE_INDEX_PATH}")
    String ABS_LUCENE_INDEX_PATH;

    public List<Document> searchLuceneIndex() {
        List<Document> documentList = null;

        /**
         * 读索引流程：
         * 1. Directory
         * 2. IndexReader
         * 3. IndexSearcher
         * 4. Query
         * 5. TopDocs
         */
        try {
//            String path = ResourceUtils.getFile(LUCENE_INDEX_PATH).getAbsolutePath();
            Directory directory = FSDirectory.open(Paths.get(ABS_LUCENE_INDEX_PATH));

            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            // 1. 基于QueryParser语法
            // Query query = new QueryParser(...).parse(...);

            // 2. 基于Query API
            /**
             * - TermQuery
             * - PhraseQuery
             * - BooleanQuery
             */

//            TermQuery termQuery = new TermQuery(new Term("fileContent", "recursively"));
//            TopDocs topDocs = indexSearcher.search(termQuery, 10);

//            PhraseQuery phraseQuery = new PhraseQuery.Builder()
//                    .add(new Term("fileContent", "Prevent"))
//                    .add(new Term("fileContent", "recursively"))
//                    .setSlop(2)
//                    .build();
//            TopDocs topDocs = indexSearcher.search(phraseQuery, 10);


            BooleanQuery booleanQuery = new BooleanQuery.Builder()
                    .add(new TermQuery(new Term("fileContent", "recursively")), BooleanClause.Occur.MUST)
                    .add(new TermQuery(new Term("fileContent", "prevent")), BooleanClause.Occur.MUST)
                    .add(new TermQuery(new Term("fileContent", "getcmdline")), BooleanClause.Occur.MUST)
                    .build();
            TopDocs topDocs = indexSearcher.search(booleanQuery, 10);
            
            documentList = Arrays.stream(topDocs.scoreDocs).map(scoreDoc -> {
                int docId = scoreDoc.doc;
                Document document = null;
                try {
                    document = indexSearcher.doc(docId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(document.get("fileName"));
                System.out.println(document.get("fileSize"));
                return document;
            }).collect(Collectors.toList());

            indexReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documentList;
    }

    @PostConstruct
    public void init() {
        searchLuceneIndex();
    }
}

/**
 * TODO
 * - 读写lucene 连接在一个spring组件中能否复用
 * -
 */

