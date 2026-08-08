
class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();

        /*
         * dp[i] = number of characters of word2 that can be
         * matched exactly using word1[i...n-1].
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            dp[i] = dp[i + 1];

            if (j >= 0 && a[i] == b[j]) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        /*
         * First, greedily choose the smallest possible indices.
         */
        while (i < n && j < m) {

            // Exact match
            if (a[i] == b[j]) {

                ans[j] = i;
                j++;

            } else {

                /*
                 * Use our one allowed modification.
                 *
                 * After choosing i, we need to match
                 * all remaining characters exactly.
                 *
                 * Remaining characters = m - j - 1
                 */
                if (dp[i + 1] >= m - j - 1) {

                    ans[j] = i;
                    j++;

                    // The one modification has been used.
                    i++;

                    break;
                }
            }

            i++;
        }

        /*
         * If we already reached the end of word1 but
         * haven't matched all of word2, no answer exists.
         */
        if (j < m && i >= n) {
            return new int[0];
        }

        /*
         * The modification has already been used (if it was needed).
         * Now we must match the remaining characters exactly.
         */
        while (j < m && i < n) {

            if (a[i] == b[j]) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        // Couldn't form word2
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}
