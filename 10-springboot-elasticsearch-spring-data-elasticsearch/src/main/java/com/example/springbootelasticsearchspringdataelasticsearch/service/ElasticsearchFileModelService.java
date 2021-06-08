package com.example.springbootelasticsearchspringdataelasticsearch.service;

import com.example.springbootelasticsearchspringdataelasticsearch.model.FileModel;
import com.example.springbootelasticsearchspringdataelasticsearch.repository.ElasticsearchFileModelRepostiory;
import com.example.springbootelasticsearchspringdataelasticsearch.util.FileUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeString.search;

@Service
public class ElasticsearchFileModelService {

    private static final String INDEX_NAME = "file_model";
    @Autowired
    ElasticsearchFileModelRepostiory elasticsearchFileModelRepostiory;

    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Object saveFileModel(FileModel fileModel) {
        return elasticsearchFileModelRepostiory.save(fileModel);
    }

    public void saveAllFileModels(List<FileModel> fileModelList) {
        elasticsearchFileModelRepostiory.saveAll(fileModelList);
    }

    public Object createFileModelIndex() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(FileModel.class);
        if (indexOperations.exists()) {
            return "index already exist";
        } else {
            /**
             * 1. PUT /file_model
             *    {settings...}
             */
            indexOperations.create();
            /**
             * 2. PUT /file_model/_mapping
             *    {properties...}
             */
            Document document = indexOperations.createMapping();
            indexOperations.putMapping(document);
            indexOperations.refresh();
            return "index created";
        }
    }

    public boolean deleteFileModelIndex() {
        return elasticsearchRestTemplate.indexOps(FileModel.class).delete();
    }

    public boolean existsFileModelIndex() {
        return elasticsearchRestTemplate.indexOps(FileModel.class).exists();
    }

    public FileModel findById(String id) {
        Optional<FileModel> fileModelOptional = elasticsearchFileModelRepostiory.findById(id);
        if (fileModelOptional.isPresent()) {
            return fileModelOptional.get();
        } else {
            return null;
        }
    }

    public FileModel findByFileName(String fileName) {
        Optional<FileModel> fileModelOptional = elasticsearchFileModelRepostiory.findByFileName(fileName);
        if (fileModelOptional.isPresent()) {
            return fileModelOptional.get();
        } else {
            return null;
        }
    }

    /**
     * - es repository
     * -- findById
     * -- save
     * -- ...
     * - es rest template
     * -- string query【PASS】
     * -- native query
     * -- criteria query
     */
    public Object findByFileSizeRange(int min, int max) {
        Criteria criteria = new Criteria("fileSize")
                .greaterThan(min)
                .lessThan(max);
        Query query = new CriteriaQuery(criteria);
        SearchHits<FileModel> fileModelSearchHits = elasticsearchRestTemplate.search(query, FileModel.class);
        return fileModelSearchHits.getSearchHits()
                .stream()
                .map(item -> {
                    FileModel fileModel = item.getContent();
                    return new HashMap() {{
                        put("id", fileModel.getId());
                        put("fileSize", fileModel.getFileSize());
                    }};
                })
                .collect(Collectors.toList())
                .stream()
                //sorted可以优化的，这里写的不太好
                .sorted((a, b) -> {
                    if (Long.parseLong(String.valueOf(a.get("fileSize"))) > Long.parseLong(String.valueOf(b.get("fileSize")))) {
                        return 1;
                    } else {
                        return -1;
                    }
                })
                .collect(Collectors.toList());
    }

    public Object findByFileType(String fileType) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("fileType", "txt");
        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        return elasticsearchRestTemplate.search(query, FileModel.class).getSearchHits().stream().map(SearchHit::getId).collect(Collectors.toList());
    }

    public Object findWithMutiFieldAndFuzzy(String input) {
        QueryBuilder queryBuilder = QueryBuilders
                .multiMatchQuery(input, "fileName", "fileContent")
                .fuzziness(Fuzziness.AUTO);
        Query query = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();
        return elasticsearchRestTemplate.search(query, FileModel.class).getSearchHits().stream().limit(10).map(SearchHit::getContent).collect(Collectors.toList());
    }

    public Object findWithWildCardQuery(String input) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("fileName", "*" + input + "*");
        Query query = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 10))
                .build();
        return elasticsearchRestTemplate.search(query, FileModel.class).getSearchHits().stream().map(SearchHit::getContent).map(FileModel::getFileName).collect(Collectors.toList());
    }


}
