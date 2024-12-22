package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {

    private List<Map<String, String>> maps;

    String doc1 = "I can't shoot straight unless I've had a pint!";
    String doc2 = "Don't shoot shoot shoot that thing at me.";
    String doc3 = "I'm your shooter.";

    @BeforeEach
    public void beforeEach() {
        maps = List.of(
                Map.of("id", "doc1", "text", doc1),
                Map.of("id", "doc2", "text", doc2),
                Map.of("id", "doc3", "text", doc3)
        );
    }

    @Test
    public void searchTest() {

        assertNotNull(SearchEngine.search(maps, "shoot"));
        assertNotNull(SearchEngine.search(new ArrayList<>(), "shoot"));
        assertNotNull(SearchEngine.search(maps, ""));

        assertLinesMatch(
                SearchEngine.search(maps, "shoot"),
                List.of("doc1", "doc2")
        );

        assertLinesMatch(
                SearchEngine.search(maps, "pint"),
                List.of("doc1")
        );

        assertLinesMatch(
                SearchEngine.search(maps, "pint!"),
                List.of("doc1")
        );
    }
}
