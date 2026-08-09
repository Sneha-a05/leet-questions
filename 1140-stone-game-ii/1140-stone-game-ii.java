class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = sum of piles from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][M] = maximum stones current player can get
        // starting from index i with M
        int[][] dp = new int[n + 1][n + 1];

        // Fill from right to left
        for (int i = n - 1; i >= 0; i--) {

            for (int M = 1; M <= n; M++) {

                int maxTake = Math.min(2 * M, n - i);

                // If we can take all remaining piles
                if (maxTake == n - i) {
                    dp[i][M] = suffix[i];
                    continue;
                }

                // Try taking X piles
                for (int X = 1; X <= maxTake; X++) {

                    int nextM = Math.max(M, X);

                    // Total remaining stones - opponent's best
                    int current = suffix[i] - dp[i + X][nextM];

                    dp[i][M] = Math.max(dp[i][M], current);
                }
            }
        }

        return dp[0][1];
    }
}