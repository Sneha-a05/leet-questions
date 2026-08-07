from collections import Counter
from typing import Dict, Tuple

class Solution:
    # digit -> Counter of its prime factors
    FACTORS = {
        0: Counter(),
        1: Counter(),
        2: Counter([2]),
        3: Counter([3]),
        4: Counter([2, 2]),
        5: Counter([5]),
        6: Counter([2, 3]),
        7: Counter([7]),
        8: Counter([2, 2, 2]),
        9: Counter([3, 3]),
    }

    def smallestNumber(self, num: str, t: int) -> str:
        need, ok = self._factorize(t)
        if not ok:
            return "-1"

        packed = self._pack(need)
        if sum(packed.values()) > len(num):
            return self._build(packed)

        # cumulative factors of the whole string
        prefix = sum((self.FACTORS[int(c)] for c in num), start=Counter())
        first_zero = next((i for i, c in enumerate(num) if c == "0"), len(num))

        if first_zero == len(num) and self._covers(prefix, need):
            return num

        # try to raise a digit from right to left
        for i in range(len(num) - 1, -1, -1):
            d = int(num[i])
            prefix -= self.FACTORS[d]          # remove current digit
            space = len(num) - 1 - i

            if i > first_zero:
                continue

            for nd in range(d + 1, 10):
                still = need - prefix - self.FACTORS[nd]
                pack_suf = self._pack(still)
                if sum(pack_suf.values()) <= space:
                    ones = space - sum(pack_suf.values())
                    return (
                        num[:i]
                        + str(nd)
                        + "1" * ones
                        + self._build(pack_suf)
                    )

        # need one extra digit
        packed = self._pack(need)
        return "1" * (len(num) + 1 - sum(packed.values())) + self._build(packed)

    # ------------------------------------------------------------------
    def _factorize(self, t: int) -> Tuple[Counter, bool]:
        cnt = Counter()
        for p in (2, 3, 5, 7):
            while t % p == 0:
                cnt[p] += 1
                t //= p
        return cnt, t == 1

    def _pack(self, cnt: Counter) -> Dict[str, int]:
        """Greedy packing into the fewest digits (prefer 8, 9, 6 …)."""
        c2, c3, c5, c7 = cnt[2], cnt[3], cnt[5], cnt[7]

        n8, c2 = divmod(c2, 3)
        n9, c3 = divmod(c3, 2)
        n4, c2 = divmod(c2, 2)
        n6 = 0

        if c2 == 1 and c3 == 1:
            n6, c2, c3 = 1, 0, 0
        if c3 == 1 and n4 == 1:
            n6, n4, c2, c3 = 1, 0, 1, 0

        return {
            "2": c2, "3": c3, "4": n4, "5": c5,
            "6": n6, "7": c7, "8": n8, "9": n9,
        }

    def _build(self, packed: Dict[str, int]) -> str:
        return "".join(d * packed[d] for d in "23456789")

    def _covers(self, have: Counter, need: Counter) -> bool:
        return all(have[p] >= need[p] for p in (2, 3, 5, 7))