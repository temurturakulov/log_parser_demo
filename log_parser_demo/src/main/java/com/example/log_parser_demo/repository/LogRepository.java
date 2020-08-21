package com.example.log_parser_demo.repository;

import com.example.log_parser_demo.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
