package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {

    private List<Map<String, String>> docs;

    String doc1 = "I can't shoot straight unless I've had a pint!";
    String doc2 = "Don't shoot shoot shoot that thing at me.";
    String doc3 = "I'm your shooter.";

    @BeforeEach
    public void beforeEach() {
        docs = List.of(
                Map.of("id", "doc1", "text", doc1),
                Map.of("id", "doc2", "text", doc2),
                Map.of("id", "doc3", "text", doc3)
        );
    }

    @Test
    public void searchTest() {

        assertNotNull(SearchEngine.search(docs, "shoot"));
        assertNotNull(SearchEngine.search(new ArrayList<>(), "shoot"));
        assertNotNull(SearchEngine.search(docs, ""));

        assertLinesMatch(
                SearchEngine.search(docs, "shoot"),
                List.of("doc2", "doc1")
        );

        assertLinesMatch(
                SearchEngine.search(docs, "pint"),
                List.of("doc1")
        );

        assertLinesMatch(
                SearchEngine.search(docs, "pint!"),
                List.of("doc1")
        );
    }
}
