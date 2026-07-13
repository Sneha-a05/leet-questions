class Solution:
    def combinationSum(self, candidates, target):
        result = []
        candidates.sort()

        def backtrack(start, remaining, current):
            if remaining == 0:
                result.append(current[:])
                return

            for i in range(start, len(candidates)):
                if candidates[i] > remaining:
                    break

                current.append(candidates[i])
                backtrack(i, remaining - candidates[i], current)  # reuse allowed
                current.pop()

        backtrack(0, target, [])
        return result