package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {

    private List<Map<String, String>> docs;

    private static final String DOC_1 = "I can't shoot straight unless I've had a pint!";
    private static final String DOC_2 = "Don't shoot shoot shoot that thing at me.";
    private static final String DOC_3 = "I'm your shooter.";

    @BeforeEach
    public void beforeEach() {
        docs = List.of(
                Map.of("id", "doc1", "text", DOC_1),
                Map.of("id", "doc2", "text", DOC_2),
                Map.of("id", "doc3", "text", DOC_3)
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

        assertLinesMatch(
                SearchEngine.search(docs, "shoot at me"),
                List.of("doc2", "doc1")
        );
    }
}
