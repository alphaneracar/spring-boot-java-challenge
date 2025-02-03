package com.springbootchallenge.repository;

import com.springbootchallenge.collection.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseSequenceRepository extends MongoRepository<DatabaseSequence, String> {
}
