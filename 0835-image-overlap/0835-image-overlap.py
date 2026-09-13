class Solution:
    def largestOverlap(self, img1: List[List[int]], img2: List[List[int]]) -> int:
        
        n = len(img1)

        max_count = 0
        for anchor_i in range(-(n-1), n):
            for anchor_j in range(-(n-1), n):
                count = 0
                for i in range(n):
                    for j in range(n):
                        # (i, j) is img1's local coordinate
                        # (i_, j_) is the global coordinate
                        i_ = anchor_i + i
                        j_ = anchor_j + j
                        if i_ < 0 or i_ >= n or j_ < 0 or j_ >= n:
                            # outside of the overlapping region
                            continue
                        if img1[i][j] and img2[i_][j_]:
                            count += 1
                max_count = max(max_count, count)
        
        return max_count
                        