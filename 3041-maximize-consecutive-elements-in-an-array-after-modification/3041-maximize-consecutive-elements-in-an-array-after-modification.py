class Solution:
    def maxSelectedElements(self, nums: List[int]) -> int:
        nums.sort()
        px = -5
        d0 = d1 = best = 0
        for x in nums:
            if x == px:
                d1 = d0 + 1
                if d1 > best:
                    best = d1
            else:
                g = x - px
                if g == 1:
                    d1, d0 = d1 + 1, d0 + 1
                elif g == 2:
                    d1, d0 = 1, d1 + 1
                else:
                    d1 = d0 = 1
                px = x
                if d0 > best:
                    best = d0
                if d1 > best:
                    best = d1
        return best