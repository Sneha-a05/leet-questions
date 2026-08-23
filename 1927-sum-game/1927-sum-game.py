class Solution:
    def sumGame(self, num: str) -> bool:
        n = len(num)

        diff = 0
        left_q = 0
        right_q = 0

        for i in range(n // 2):
            if num[i] == '?':
                left_q += 1
            else:
                diff += int(num[i])

        for i in range(n // 2, n):
            if num[i] == '?':
                right_q += 1
            else:
                diff -= int(num[i])

        # If the number of '?' is odd, Alice makes the last move
        # and can always make the sums unequal.
        if (left_q + right_q) % 2 == 1:
            return True

        # Bob can force equality only when the existing difference
        # can be exactly compensated by the '?' digits.
        return diff != 9 * (right_q - left_q) // 2