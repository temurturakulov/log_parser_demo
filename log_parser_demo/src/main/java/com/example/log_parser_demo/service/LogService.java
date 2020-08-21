package com.example.log_parser_demo.service;

import com.example.log_parser_demo.model.Log;
import com.example.log_parser_demo.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogService {
    private final LogRepository loggerRepository;
    Logger logger = LoggerFactory.getLogger(LogRepository.class);

    public LogService(LogRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    public Log findById(Long id) {
        return loggerRepository.getOne(id);
    }

    public List<Log> findAll() {
        return loggerRepository.findAll();
    }

    public Log saveLogger(Log log) {
        return loggerRepository.save(log);
    }

    public void deleteById(Long id) {
        loggerRepository.deleteById(id);
    }

    public List<Log> saveLogsFromFile(MultipartFile file, String name) {

        if (!file.isEmpty()) {
            BufferedReader br = null;
            ArrayList<Log> logList = new ArrayList<Log>();

            try {
                byte[] bytes = file.getBytes();

                String fileString = new String(bytes, StandardCharsets.UTF_8);
                br = new BufferedReader(new StringReader(fileString));

                String line;

                try {
                    while ((line = br.readLine()) != null) {
                        Log log = getLogFromLine(line);
                        if (log != null) {
                            logList.add(log);
                        }
                    }
                } finally {
                    br.close();
                }
            } catch (Exception e) {
                logger.error("Ошибка при обработке файла " + e.getMessage());
            }

            return loggerRepository.saveAll(logList);
        } else {
            return null;
        }
    }

    private Log getLogFromLine(String line) {
        String logEntryPattern = "(\\d+-\\d+-\\d+\\s\\d+:\\d+:\\d+.\\d+)(\\s\\[\\w+\\]\\s|\\s\\[\\w+-\\d+\\]\\s)(ERROR|INFO|DEBUG|FATAL|TRACE)(\\s-\\s|\\s)(\\w+\\D+\\d*|\\w+\\d*)";

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(line);
        if (!matcher.find()) {
            return null;
        }
        Log log = new Log();

        log.setCreated(Timestamp.valueOf(matcher.group(1)));
        log.setProvider(matcher.group(2));
        log.setLevel(matcher.group(3));
        log.setMessage(matcher.group(5));
        return log;
    }

}
