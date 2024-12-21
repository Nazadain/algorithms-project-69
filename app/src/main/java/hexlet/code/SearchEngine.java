package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngine {

    public static List<String> search(List<Map<String, String>> list, String candidate) {
        List<String> result = new ArrayList<>();

        if (list == null || list.isEmpty() || candidate == null || candidate.isEmpty()) {
            return result;
        }

        for (Map<String, String> map : list) {
            String id = map.get("id");
            String text = map.get("text");

            if (isTextContainsWord(text, candidate)) {
                result.add(id);
            }
        }
        return result;
    }

    private static boolean isTextContainsWord(String text, String candidate) {
        String[] words = text.split(" ");


        for (String word : words) {

            if (word.contains(candidate)) {
                return true;
            } else {
                String candidateTerm = createTerm(candidate);
                String wordTerm = createTerm(word);

                if (wordTerm.equals(candidateTerm)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String createTerm(String word) {
        return Pattern.compile("\\w+")
                .matcher(word)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.joining());
    }
}
