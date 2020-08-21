package com.example.log_parser_demo.controller;

import com.example.log_parser_demo.model.Log;
import com.example.log_parser_demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@Controller
public class LogParseController {
    Logger logger= LoggerFactory.getLogger(LogParseController.class);

    private final LogService logService;

    @Autowired
    public LogParseController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/loggers")
    public String findAll(Model model){
        List<Log> logs = logService.findAll();
        model.addAttribute("logs", logs);
        logger.info("Вывод вызван FindAll");
        return "LoggerList";
    }

    @GetMapping("/Logger-Create")
    public String createLoggerForm(Log log){
        return "Logger-Create";
    }

    @PostMapping("/Logger-Create")
    public String createLogger(Log log){
        logService.saveLogger(log);
        return "redirect:/loggers";
    }

    @RequestMapping(value="/upload-file", method= RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file){
        logService.saveLogsFromFile(file, name);
        return "redirect:/loggers";
    }
}
