package com.example.log_parser_demo.service;

import com.example.log_parser_demo.model.Log;
import com.example.log_parser_demo.repository.LogRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
class LogServiceTest {

    private LogRepository logRepository;

    @Autowired
    private LogService service;


    @Test
    void saveLogger() {
        Log log=new Log();
        log.setCreated(Timestamp.valueOf("2019-08-30 20:43:37.407"));
        log.setProvider("main");
        log.setLevel("INFO");
        log.setMessage("ru.javastudy.examples.User - This is some message");

        Log savedLog = service.saveLogger(log);


        Assert.assertNotNull(savedLog);
    }

}