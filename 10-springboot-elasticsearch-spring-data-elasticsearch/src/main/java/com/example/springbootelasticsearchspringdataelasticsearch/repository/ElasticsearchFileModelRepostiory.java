package com.example.springbootelasticsearchspringdataelasticsearch.repository;

import com.example.springbootelasticsearchspringdataelasticsearch.model.FileModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElasticsearchFileModelRepostiory extends ElasticsearchRepository<FileModel, String> {

    Optional<FileModel> findByFileName(String fileName);
}
