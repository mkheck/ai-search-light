package com.thehecklers.aisearchlight;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.search.documents.indexes.SearchIndexClient;
import com.azure.search.documents.indexes.SearchIndexClientBuilder;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.azure.AzureVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorConfig {
    @Bean
    public SearchIndexClient searchIndexClient() {
        return new SearchIndexClientBuilder().endpoint(System.getenv("AZURE_AI_SEARCH_ENDPOINT"))
                .credential(new AzureKeyCredential(System.getenv("AZURE_AI_SEARCH_API_KEY")))
                .buildClient();
    }

    @Bean
    public VectorStore vectorStore(SearchIndexClient searchIndexClient, EmbeddingClient embeddingClient) {
        return new AzureVectorStore(searchIndexClient, embeddingClient);
    }
}
