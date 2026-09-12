class Solution:
    def maximumWeight(self, intervals: list[list[int]]) -> list[int]:
        from bisect import bisect_right

        arr = sorted(
            (l, r, w, i)
            for i, (l, r, w) in enumerate(intervals)
        )

        n = len(arr)
        starts = [x[0] for x in arr]

        # First interval with start > current end
        nxt = [
            bisect_right(starts, r)
            for _, r, _, _ in arr
        ]

        # dp[i][k] = best (score, indices)
        dp = [[(0, []) for _ in range(5)] for _ in range(n + 1)]

        for i in range(n - 1, -1, -1):
            _, _, w, idx = arr[i]

            for k in range(1, 5):
                # Skip current interval
                skip = dp[i + 1][k]

                # Take current interval
                score, ids = dp[nxt[i]][k - 1]
                take = (score + w, sorted(ids + [idx]))

                # Maximize score, then minimize indices lexicographically
                if take[0] > skip[0] or (
                    take[0] == skip[0] and take[1] < skip[1]
                ):
                    dp[i][k] = take
                else:
                    dp[i][k] = skip

        return dp[0][4][1]
