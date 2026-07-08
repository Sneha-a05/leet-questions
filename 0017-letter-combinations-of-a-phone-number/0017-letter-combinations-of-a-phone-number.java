import java.util.*;

class Solution {

    private String[] map = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return ans;
        }

        backtrack(digits, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtrack(String digits, int index, StringBuilder curr, List<String> ans) {

        // Base case
        if (index == digits.length()) {
            ans.add(curr.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            curr.append(letters.charAt(i));      // Choose
            backtrack(digits, index + 1, curr, ans); // Explore
            curr.deleteCharAt(curr.length() - 1);    // Backtrack
        }
    }
}
