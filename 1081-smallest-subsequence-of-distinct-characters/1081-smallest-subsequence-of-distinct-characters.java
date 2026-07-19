class Solution {
    public String smallestSubsequence(String s) {
        int[] remaining = new int[26];
        boolean[] used = new boolean[26];

        for (char ch : s.toCharArray()) {
            remaining[ch - 'a']++;
        }

        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            remaining[index]--;

            if (used[index]) {
                continue;
            }

            while (stack.length() > 0) {
                char last = stack.charAt(stack.length() - 1);

                // Remove a larger character if it will appear again later
                if (last > ch && remaining[last - 'a'] > 0) {
                    used[last - 'a'] = false;
                    stack.deleteCharAt(stack.length() - 1);
                } else {
                    break;
                }
            }

            stack.append(ch);
            used[index] = true;
        }

        return stack.toString();
    }
}