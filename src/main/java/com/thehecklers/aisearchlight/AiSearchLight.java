package com.thehecklers.aisearchlight;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Component
public class AiSearchLight {
    private final VectorStore vectorStore;

    public AiSearchLight(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void search() {
        vectorStore.similaritySearch(SearchRequest.query("Piper")
                        .withTopK(50)
//                        .withFilterExpression("wing_span_ft lt 38"))
                .forEach(System.out::println);
    }
}
