class Solution:
    def subsequencePairCount(self, nums):
        MOD = 1000000007
        max_gcd = max(nums)

        def gcd(a, b):
            while b:
                a, b = b, a % b
            return a

        dp = [[0] * (max_gcd + 1) for _ in range(max_gcd + 1)]
        dp[0][0] = 1

        for x in nums:
            new_dp = [[0] * (max_gcd + 1) for _ in range(max_gcd + 1)]

            for g1 in range(max_gcd + 1):
                for g2 in range(max_gcd + 1):
                    ways = dp[g1][g2]
                    if ways == 0:
                        continue

                    # Do not select x
                    new_dp[g1][g2] = (new_dp[g1][g2] + ways) % MOD

                    # Put x in seq1
                    ng1 = gcd(g1, x)
                    new_dp[ng1][g2] = (new_dp[ng1][g2] + ways) % MOD

                    # Put x in seq2
                    ng2 = gcd(g2, x)
                    new_dp[g1][ng2] = (new_dp[g1][ng2] + ways) % MOD

            dp = new_dp

        answer = 0
        for g in range(1, max_gcd + 1):
            answer = (answer + dp[g][g]) % MOD

        return answer