class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i = 0; i<= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                int wt = coins[j];
                if(i-wt >= 0) {
                    dp[i] = Math.min(dp[i], dp[i-wt] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(dp));

        return dp[amount] == amount +1 ? -1 : dp[amount];
    }

}