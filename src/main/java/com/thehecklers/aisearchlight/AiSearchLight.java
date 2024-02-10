package com.thehecklers.aisearchlight;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Component
public class AiSearchLight {
    private final VectorStore vectorStore;

    public AiSearchLight(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

//    @PostConstruct
    public void funRun() {
        //searchWithParameters(5, "engineType == 'Piston'");
        search("Piper", 5, "engineType == 'Piston'").forEach(System.out::println);
    }

    //    public Iterable<Document> search() {
//        return vectorStore.similaritySearch(SearchRequest.query("Piper"));
//    }
//
    public Iterable<Document> search(String query, int limit, String filter) {
        return vectorStore.similaritySearch(SearchRequest.query(query)
                .withTopK(limit)
                .withFilterExpression(filter));
    }
}
