package com.example.springbootkafka.service;

import com.example.springbootkafka.model.PageView;
import com.example.springbootkafka.util.MockDataUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MockDataService {

    List<Map> mockData;
    int mockDataSize;

    public MockDataService() {
        this.mockData = MockDataUtils.mockData();
        this.mockDataSize = this.mockData.size();
    }

    public PageView mockDataPageView() {
        Map randomItem = this.getRandomItem();
        return PageView.builder()
                .id(Long.valueOf(String.valueOf(randomItem.get("id"))))
                .firstName((String) randomItem.get("first_name"))
                .lastName((String) randomItem.get("last_name"))
                .email((String) randomItem.get("email"))
                .nothing((String) randomItem.get("gender"))
                .ipAddress((String) randomItem.get("ip_address"))
                .viewDate(new Date())
                .build();
    }

    public List<PageView> mockDataPageViewList(int num) {
        return IntStream.range(0, num).mapToObj(x -> {
            Map randomItem = this.getRandomItem();
            return PageView.builder()
                    .id(Long.valueOf(String.valueOf(randomItem.get("id"))))
                    .firstName((String) randomItem.get("first_name"))
                    .lastName((String) randomItem.get("last_name"))
                    .email((String) randomItem.get("email"))
                    .nothing((String) randomItem.get("gender"))
                    .ipAddress((String) randomItem.get("ip_address"))
                    .viewDate(new Date())
                    .build();
        }).collect(Collectors.toList());
    }

    public Map getRandomItem() {
        return this.mockData.get(new Random().nextInt(this.mockDataSize));
    }
}
