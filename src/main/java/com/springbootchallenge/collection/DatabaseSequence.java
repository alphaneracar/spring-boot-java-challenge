package com.springbootchallenge.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@Builder
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;
    private long seq;

}
