class Solution:
    def smallestPalindrome(self, s):
        freq = [0] * 26
        for ch in s:
            freq[ord(ch) - 97] += 1

        left = []
        mid = ""

        for i in range(26):
            if freq[i] % 2 == 1:
                mid = chr(i + 97)
            left.append(chr(i + 97) * (freq[i] // 2))

        left = "".join(left)
        return left + mid + left[::-1]