from dataclasses import dataclass
import itertools

@dataclass
class Group:
    start: int
    length: int

class SparseTable:
    def __init__(self, nums: list[int]):
        self.n = len(nums)
        self.st = [[0] * (self.n + 1) for _ in range(self.n.bit_length() + 1)]
        self.st[0] = nums.copy()
        for i in range(1, self.n.bit_length() + 1):
            for j in range(self.n - (1 << i) + 1):
                self.st[i][j] = max(self.st[i - 1][j],
                                     self.st[i - 1][j + (1 << (i - 1))])

    def query(self, l: int, r: int) -> int:
        i = (r - l + 1).bit_length() - 1
        return max(self.st[i][l], self.st[i][r - (1 << i) + 1])

class Solution:
    def maxActiveSectionsAfterTrade(self, s: str, queries: list[list[int]]) -> list[int]:
        ones = s.count('1')
        zeroGroups, zeroGroupIndex = self._getZeroGroups(s)
        if not zeroGroups:
            return [ones] * len(queries)

        st = SparseTable(self._getZeroMergeLengths(zeroGroups))

        def getMaxActiveSections(l: int, r: int) -> int:
            gL = zeroGroupIndex[l]
            gR = zeroGroupIndex[r]
            left = (-1 if s[l] == '1'
                    else zeroGroups[gL].length - (l - zeroGroups[gL].start))
            right = (-1 if s[r] == '1'
                     else r - zeroGroups[gR].start + 1)

            startAdjIdx = gL + 1
            groupEnd = gR if s[r] == '1' else gR - 1   # last group fully interior/at r
            endAdjIdx = groupEnd - 1                    # fix: pair index needs this extra -1

            active = ones
            if s[l] == '0' and s[r] == '0' and gL + 1 == gR:
                active = max(active, ones + left + right)
            elif startAdjIdx <= endAdjIdx:
                active = max(active, ones + st.query(startAdjIdx, endAdjIdx))

            if s[l] == '0' and gL + 1 <= groupEnd:
                active = max(active, ones + left + zeroGroups[gL + 1].length)
            if s[r] == '0' and gL < gR - 1:
                active = max(active, ones + right + zeroGroups[gR - 1].length)
            return active

        return [getMaxActiveSections(l, r) for l, r in queries]

    def _getZeroGroups(self, s: str):
        zeroGroups, zeroGroupIndex = [], []
        for i, ch in enumerate(s):
            if ch == '0':
                if i > 0 and s[i - 1] == '0':
                    zeroGroups[-1].length += 1
                else:
                    zeroGroups.append(Group(i, 1))
            zeroGroupIndex.append(len(zeroGroups) - 1)
        return zeroGroups, zeroGroupIndex

    def _getZeroMergeLengths(self, zeroGroups):
        return [a.length + b.length for a, b in itertools.pairwise(zeroGroups)]