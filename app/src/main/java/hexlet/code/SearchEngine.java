package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchEngine {

    public static List<String> search(List<Map<String, String>> list, String candidate) {
        List<String> result = new ArrayList<>();

        if(list == null || list.isEmpty() || candidate.isEmpty()) {
            return result;
        }

        for(Map<String, String> map : list) {
            String id = map.get("id");
            String text = map.get("text");

            String[] words = text.split("[ ,.]");

            if(isArrayContainsWord(words, candidate)) {
                result.add(id);
            }
        }
        return result;
    }

    private static boolean isArrayContainsWord(String[] words, String candidate) {
        for(String word : words) {
            word = word.trim();
            if(word.equals(candidate)) {
                return true;
            }
        }
        return false;
    }
}
