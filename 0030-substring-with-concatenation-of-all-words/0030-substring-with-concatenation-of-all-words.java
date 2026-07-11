import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int wordLength = words[0].length();
        int wordCount = words.length;
        int totalLength = wordLength * wordCount;

        if (s.length() < totalLength) {
            return result;
        }

        Map<String, Integer> required = new HashMap<>();
        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        // Check each possible alignment of word-sized blocks
        for (int offset = 0; offset < wordLength; offset++) {
            Map<String, Integer> window = new HashMap<>();
            int left = offset;
            int matchedWords = 0;

            for (int right = offset; right + wordLength <= s.length(); right += wordLength) {
                String word = s.substring(right, right + wordLength);

                if (!required.containsKey(word)) {
                    window.clear();
                    matchedWords = 0;
                    left = right + wordLength;
                    continue;
                }

                window.put(word, window.getOrDefault(word, 0) + 1);
                matchedWords++;

                // Remove words from the left until this word's count is valid
                while (window.get(word) > required.get(word)) {
                    String leftWord = s.substring(left, left + wordLength);
                    window.put(leftWord, window.get(leftWord) - 1);
                    matchedWords--;
                    left += wordLength;
                }

                // Found a valid concatenation
                if (matchedWords == wordCount) {
                    result.add(left);

                    // Move forward one word to look for overlapping matches
                    String leftWord = s.substring(left, left + wordLength);
                    window.put(leftWord, window.get(leftWord) - 1);
                    matchedWords--;
                    left += wordLength;
                }
            }
        }

        return result;
    }
}