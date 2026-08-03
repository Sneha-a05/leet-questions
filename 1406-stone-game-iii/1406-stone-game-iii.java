class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] = best score difference (current player - opponent) starting from index i
        int[] dp = new int[n + 1];
        dp[n] = 0;
        
        // suffix sum ka use directly running sum se karte hain loop me
        for (int i = n - 1; i >= 0; i--) {
            int sum = 0;
            dp[i] = Integer.MIN_VALUE;
            // 1, 2, ya 3 stones le sakte hain
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                sum += stoneValue[i + k - 1];
                dp[i] = Math.max(dp[i], sum - dp[i + k]);
            }
        }
        
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}