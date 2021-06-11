package cn.sicnu.activity.service;

import cn.sicnu.common.model.domain.University;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.input.ReaderInputStream;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UniversityService {

    private List<University> list;

    private final ResourceLoader resourceLoader;

    @PostConstruct
    public void init() throws IOException {
        val inputStream = resourceLoader.getResource("classpath:university.txt").getInputStream();
        val bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        list = bufferedReader.lines().map(line -> {
            val strs = line.trim().split(" ");
            return new University(strs[0], strs[1]);
        }).collect(Collectors.toList());
    }


    public List<University> getUniversity() {
        return list;
    }

}
