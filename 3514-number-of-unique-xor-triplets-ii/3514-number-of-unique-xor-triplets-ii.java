class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        // dp[k][x] = true if xor value x can be formed
        // by choosing exactly k distinct elements.
        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int num : nums) {
            for (int k = 2; k >= 0; k--) {
                for (int x = 0; x < MAX; x++) {
                    if (dp[k][x]) {
                        dp[k + 1][x ^ num] = true;
                    }
                }
            }
        }

        boolean[] seen = new boolean[MAX];

        // Values obtained when indices are not all distinct
        for (int num : nums) {
            seen[num] = true;
        }

        // Values obtained from 3 distinct elements
        for (int x = 0; x < MAX; x++) {
            if (dp[3][x]) {
                seen[x] = true;
            }
        }

        int ans = 0;
        for (boolean b : seen) {
            if (b) ans++;
        }

        return ans;
    }
}