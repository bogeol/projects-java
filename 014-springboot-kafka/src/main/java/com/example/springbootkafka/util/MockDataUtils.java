package com.example.springbootkafka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MockDataUtils {

    public static List<Map> mockData() {
        List<Map> res = null;
        File file = null;
        Optional<List<Map>> optional = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            file = ResourceUtils.getFile("classpath:data/MOCK_DATA.json");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        try {
           optional = Optional.of(objectMapper.readValue(file, List.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (optional.isPresent()) {
            res = optional.get();
        }
        return res;
    }

    public static void main(String[] args) {
        mockData();
    }

}
