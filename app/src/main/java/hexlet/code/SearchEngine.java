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

        List<Match> matches = new ArrayList<>();

        for (Map<String, String> map : list) {
            String id = map.get("id");
            String text = map.get("text");

            Match match = createMatchIfTextContainsCandidate(id, text, candidate);

            if (match != null) {
                matches.add(match);
            }
        }

        matches.sort(Match::compareTo);

        for (Match match : matches) {
            result.add(match.getId());
        }

        return result;
    }

    private static Match createMatchIfTextContainsCandidate(String id, String text, String candidate) {

        int exactMatches = calculateWordRepeatsInText(text, candidate);
        int inexactMatches = 0;

        String[] words = candidate.split(" ");

        if (words.length < 2) {
            if (exactMatches != 0) {
                return new Match(id, exactMatches, 0);
            } else {
                return null;
            }
        }

        for (String word : words) {
            inexactMatches += calculateWordRepeatsInText(text, word);
        }

        if (exactMatches == 0 && inexactMatches == 0) {
            return null;
        }

        return new Match(id, exactMatches, inexactMatches);
    }

    private static int calculateWordRepeatsInText(String text, String candidate) {
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

    public static class Match implements Comparable<Match> {
        private final String id;
        private final int exactCount;
        private final int inexactCount;

        public Match(String id, int exactCount, int inexactCount) {
            this.id = id;
            this.exactCount = exactCount;
            this.inexactCount = inexactCount;
        }

        public String getId() {
            return id;
        }

        @Override
        public int compareTo(Match o) {
            int exactCompare = Integer.compare(o.exactCount, this.exactCount);
            return exactCompare != 0 ? exactCompare : Integer.compare(o.inexactCount, this.inexactCount);
        }

        @Override
        public String toString() {
            return "Match{" +
                    "id='" + id + '\'' +
                    ", exactCount=" + exactCount +
                    ", inexactCount=" + inexactCount +
                    '}';
        }
    }
}