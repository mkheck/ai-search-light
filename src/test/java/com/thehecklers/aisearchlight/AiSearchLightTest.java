package com.thehecklers.aisearchlight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(value = {AiSearchLight.class})
class AiSearchLightTest {
    @MockBean
    private VectorStore vectorStore;

    @Autowired
    private AiSearchLight aiSearchLight;

    List<Document> documents;

    @BeforeEach
    void setUp() {
        documents = List.of(new Document("1cfdcd42-33ef-4841-8e1c-77d88e050b19",
                "Aircraft[manufacturer=Piper Aircraft, model=PA-31P-350 Mojave, engine_type=Piston, engine_thrust_lb_ft=350, max_speed_knots=242, cruise_speed_knots=220, ceiling_ft=26500, rate_of_climb_ft_per_min=1220, takeoff_ground_run_ft=2190, landing_ground_roll_ft=1395, gross_weight_lbs=7200, empty_weight_lbs=5070, length_ft=34.5, height_ft=13.0, wing_span_ft=44.667, range_nautical_miles=1190, takeoff_over_50ft_ft=3035, landing_over_50ft_ft=2305]",
                Map.of("engine_type", "Piston", "model", "PA-31P-350 Mojave", "manufacturer", "Piper Aircraft")));

//        Document{id='1cfdcd42-33ef-4841-8e1c-77d88e050b19',
//        metadata={engine_type=Piston, model=PA-31P-350 Mojave, manufacturer=Piper Aircraft},
//        content='Aircraft[manufacturer=Piper Aircraft, model=PA-31P-350 Mojave, engine_type=Piston, engine_thrust_lb_ft=350, max_speed_knots=242, cruise_speed_knots=220, ceiling_ft=26500, rate_of_climb_ft_per_min=1220, takeoff_ground_run_ft=2190, landing_ground_roll_ft=1395, gross_weight_lbs=7200, empty_weight_lbs=5070, length_ft=34.5, height_ft=13.0, wing_span_ft=44.667, range_nautical_miles=1190, takeoff_over_50ft_ft=3035, landing_over_50ft_ft=2305]'}
        when(vectorStore.similaritySearch(anyString())).thenReturn(documents);
        when(vectorStore.similaritySearch(any(SearchRequest.class))).thenReturn(documents);
    }

    @Test
    void search() {
        //Iterable<Document> docs = aiSearchLight.search(5, "range_nautical_miles == 1190");
        // Works ⤵️
        //Iterable<Document> docs = aiSearchLight.search(5, "id == '1cfdcd42-33ef-4841-8e1c-77d88e050b19'");
//        Iterable<Document> vsDocs = vectorStore.similaritySearch("Piper");
//        System.out.println("VS DOCS >>> ");
//        vsDocs.forEach(System.out::println);

//        Iterable<Document> docs = aiSearchLight.search();

//        var b = new FilterExpressionBuilder();
//        Filter.Expression expression = b..eq("country", "BG").build();

//        Iterable<Document> docs = aiSearchLight.search(5, "manufacturer == 'Piper Aircraft'");
//        Iterable<Document> docs = aiSearchLight.search(5, "model == 'PA-31P-350 Mojave'");
        Iterable<Document> docs = aiSearchLight.search("Piper", 5, "engineType == 'Piston'");
        System.out.println("DOCS >>> ");
        docs.forEach(System.out::println);
        assertEquals(docs.iterator().next().getId(), "1cfdcd42-33ef-4841-8e1c-77d88e050b19");
    }
}