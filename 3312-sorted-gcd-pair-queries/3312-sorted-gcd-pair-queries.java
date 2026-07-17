import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] cnt = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            long c = 0;
            for (int j = g; j <= max; j += g) {
                c += freq[j];
            }
            cnt[g] = c * (c - 1) / 2;
        }

        for (int g = max; g >= 1; g--) {
            for (int j = g * 2; j <= max; j += g) {
                cnt[g] -= cnt[j];
            }
        }

        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + cnt[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;
            int l = 1, r = max;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (prefix[mid] >= k) r = mid;
                else l = mid + 1;
            }
            ans[i] = l;
        }

        return ans;
    }
}