package com.example.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "demo")
public class Demo {
    @Id
    private String id;
    private String token;
    private String  username;

}
