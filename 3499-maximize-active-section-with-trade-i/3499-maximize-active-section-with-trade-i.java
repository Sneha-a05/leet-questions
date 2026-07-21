class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int active = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') active++;
        }

        String t = "1" + s + "1";
        int bestGain = 0;

        int i = 1; // Ignore the artificial first '1'
        while (i < t.length() - 1) {
            if (t.charAt(i) != '1') {
                i++;
                continue;
            }

            // Find this contiguous block of '1's.
            int oneStart = i;
            while (i < t.length() - 1 && t.charAt(i) == '1') {
                i++;
            }
            int oneEnd = i - 1;

            // A valid removable 1-block must have 0 on both sides.
            if (t.charAt(oneStart - 1) == '0' && t.charAt(oneEnd + 1) == '0') {
                int leftZeros = 0;
                int p = oneStart - 1;
                while (p >= 0 && t.charAt(p) == '0') {
                    leftZeros++;
                    p--;
                }

                int rightZeros = 0;
                p = oneEnd + 1;
                while (p < t.length() && t.charAt(p) == '0') {
                    rightZeros++;
                    p++;
                }

                // Those adjacent zero blocks become one large active block.
                bestGain = Math.max(bestGain, leftZeros + rightZeros);
            }
        }

        return active + bestGain;
    }
}