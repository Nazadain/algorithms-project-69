package hexlet.code;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngine {

    public static List<String> search(List<Map<String, String>> list, String candidate) {
        List<String> result = new ArrayList<>();

        if (list == null || list.isEmpty() || candidate == null || candidate.isEmpty()) {
            return result;
        }

        Map<String, Integer> resultMap = new HashMap<>();

        for (Map<String, String> map : list) {
            String id = map.get("id");
            String text = map.get("text");

            int wordsCount = wordRepetitionsInText(text, candidate);

            if (wordsCount > 0) {
                resultMap.put(id, wordsCount);
            }
        }

        resultMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> result.add(e.getKey()));

        return result;
    }

    private static int wordRepetitionsInText(String text, String candidate) {
        String term = createTerm(candidate);

        String regex = "\\b" + term + "\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int wordsCount = 0;

        while (matcher.find()) {
            wordsCount++;
        }
        return wordsCount;
    }

    private static String createTerm(String word) {
        return Pattern.compile("\\w+")
                .matcher(word)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.joining());
    }
}
