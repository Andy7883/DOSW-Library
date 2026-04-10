package edu.eci.dosw.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                "mongodb+srv://Andy:1000099457@librarydb.ibw4bn1.mongodb.net/librarydb"
        );
    }
}