class Solution {
    public int maximumLengthSubstring(String s) {
        // Intuition: check every substring independently, count frequencies from scratch
        int n = s.length();
        int result = 0;
        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                int[] freq = new int[26];
                boolean valid = true;
                for (int i = left; i <= right; i++) {
                    int c = s.charAt(i) - 'a';
                    freq[c]++;
                    if (freq[c] > 2) {
                        valid = false;
                        break;
                    }
                }
                if (valid) result = Math.max(result, right - left + 1);
            }
        }
        return result;
    }
}