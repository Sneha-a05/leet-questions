class Solution {
    private static final int MAX = 1_000_001; // k <= 1e6, so values >= MAX can be truncated

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int halfLen = n / 2;
        char mid = (n % 2 == 1) ? s.charAt(halfLen) : 0;

        // Frequency of characters in the first half (the multiset that determines all palindromic permutations)
        int[] cnt = new int[26];
        for (int i = 0; i < halfLen; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // Total number of distinct permutations of the multiset
        long total = countWays(cnt);
        if (total < k) {
            return "";
        }

        // Build the k-th lexicographically smallest first half
        StringBuilder left = new StringBuilder();
        for (int i = 0; i < halfLen; i++) {
            for (int c = 0; c < 26; c++) {
                if (cnt[c] == 0) continue;

                // Temporarily place character c and count remaining permutations
                cnt[c]--;
                long ways = countWays(cnt);
                cnt[c]++; // restore

                if (k <= ways) {
                    // The answer lies in the permutations that start with c
                    left.append((char) ('a' + c));
                    cnt[c]--;
                    break;
                } else {
                    // Skip all permutations starting with c
                    k -= ways;
                }
            }
        }

        // Reconstruct the full palindrome: left + mid (if any) + reverse(left)
        StringBuilder ans = new StringBuilder(left);
        if (mid != 0) {
            ans.append(mid);
        }
        ans.append(left.reverse());
        return ans.toString();
    }

    /**
     * Number of distinct permutations of the multiset given by cnt:
     *   n! / (c0! * c1! * ... * c25!)
     * rewritten as a product of combinations to avoid large factorials.
     * Returns at most MAX (sufficient because k <= 1e6).
     */
    private long countWays(int[] cnt) {
        int space = 0;
        for (int v : cnt) space += v;

        long ways = 1;
        for (int v : cnt) {
            if (v == 0) continue;
            ways *= comb(space, v);
            if (ways >= MAX) return MAX;
            space -= v;
        }
        return ways;
    }

    /**
     * Combinations C(n, k) computed multiplicatively.
     * Truncates at MAX; uses the identity C(n, k) = C(n, n-k).
     * Intermediate results stay integers because any product of k consecutive
     * integers is divisible by k!.
     */
    private long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        k = Math.min(k, n - k);

        long res = 1;
        for (int i = 1; i <= k; i++) {
            // multiply first, then divide (exact division is guaranteed)
            res = res * (n - i + 1) / i;
            if (res >= MAX) return MAX;
        }
        return res;
    }
}