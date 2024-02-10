package com.thehecklers.aisearchlight;

import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AiSearchLightController {
    private final AiSearchLight aiSearchLight;

    public AiSearchLightController(AiSearchLight aiSearchLight) {
        this.aiSearchLight = aiSearchLight;
    }

    @GetMapping
    public String hello() {
        return "Hello, AI Searchlight!";
    }

    @GetMapping("/search")
    public Iterable<Document> search(@RequestParam(defaultValue = "Piper") String query,
                                     @RequestParam(defaultValue = "5") int limit,
                                     @RequestParam(required = false) String filter) {
        System.out.println("Query: " + query + ", Limit: " + limit + ", Filter: " + filter);
        return aiSearchLight.search(query, limit, filter);
    }

}
