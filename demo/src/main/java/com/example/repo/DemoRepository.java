package com.example.repo;

import com.example.entity.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository  extends MongoRepository<Demo, String> {
}
