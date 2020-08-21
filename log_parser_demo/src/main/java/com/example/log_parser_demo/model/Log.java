package com.example.log_parser_demo.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="created")
    private Timestamp created;
    @Column(name = "provider")
    private String provider;
    @Column(name = "level")
    private String level;
    @Column(name = "message")
    private String message;

    public void setId(Long _id){
        id=_id;
    }

    public void setCreated(Timestamp _created){
        created= _created;
    }

    public void setProvider(String _provider){
        provider= _provider;
    }
    public void  setLevel(String _level){
        level=_level;
    }
    public void setMessage(String _message){
        message=_message;
    }

    public java.lang.Long getId() {
        return id;
    }
    public Timestamp getCreated(){
        return created;
    }

    public String getProvider(){
        return  provider;
    }

    public  String getLevel(){
        return  level;
    }

    public  String getMessage(){
        return message;
    }
}
