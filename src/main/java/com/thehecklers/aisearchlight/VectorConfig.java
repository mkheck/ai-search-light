package com.thehecklers.aisearchlight;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class VectorConfig {
    /*@Bean
    public SearchIndexClient searchIndexClient() {
        return new SearchIndexClientBuilder().endpoint(System.getenv("AZURE_AI_SEARCH_ENDPOINT"))
                .credential(new AzureKeyCredential(System.getenv("AZURE_AI_SEARCH_API_KEY")))
                .buildClient();
    }

    @Bean
    public VectorStore vectorStore(SearchIndexClient searchIndexClient, EmbeddingClient embeddingClient) {
        return new AzureVectorStore(searchIndexClient, embeddingClient);
    }*/

    // PostgreSQL / PGvector version
    @Bean
    public VectorStore vectorStore(JdbcTemplate jdbcTemplate, EmbeddingClient embeddingClient) {
        return new PgVectorStore(jdbcTemplate, embeddingClient);
    }
}
