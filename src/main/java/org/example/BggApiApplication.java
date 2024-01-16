package org.example;

import org.example.bggapi.BGGApiService;
import org.example.bggapi.BGGItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BggApiApplication implements CommandLineRunner {

    private final BGGApiService bggApiService;

    public BggApiApplication(BGGApiService bggApiService) {
        this.bggApiService = bggApiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BggApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Example: Search for a board game with the query "Catan"
        String query = "Kingdomino";
        String searchResult = bggApiService.searchBoardGameandExtractId(query);
//        System.out.println(searchResult);

        String specificGameSearch = bggApiService.searchGameById(searchResult);
        System.out.println(specificGameSearch);
    }
}