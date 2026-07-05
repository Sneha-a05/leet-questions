class Solution {
    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();
        int MOD = 1000000007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        // Initialize score with -1 (unreachable)
        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], -1);
        }

        // Start from 'S'
        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        // Traverse from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                char ch = board.get(i).charAt(j);

                if (ch == 'X' || (i == n - 1 && j == n - 1))
                    continue;

                int maxScore = -1;
                int count = 0;

                // From Down
                if (i + 1 < n && score[i + 1][j] != -1) {
                    if (score[i + 1][j] > maxScore) {
                        maxScore = score[i + 1][j];
                        count = ways[i + 1][j];
                    } else if (score[i + 1][j] == maxScore) {
                        count = (count + ways[i + 1][j]) % MOD;
                    }
                }

                // From Right
                if (j + 1 < n && score[i][j + 1] != -1) {
                    if (score[i][j + 1] > maxScore) {
                        maxScore = score[i][j + 1];
                        count = ways[i][j + 1];
                    } else if (score[i][j + 1] == maxScore) {
                        count = (count + ways[i][j + 1]) % MOD;
                    }
                }

                // From Down-Right (Diagonal)
                if (i + 1 < n && j + 1 < n && score[i + 1][j + 1] != -1) {
                    if (score[i + 1][j + 1] > maxScore) {
                        maxScore = score[i + 1][j + 1];
                        count = ways[i + 1][j + 1];
                    } else if (score[i + 1][j + 1] == maxScore) {
                        count = (count + ways[i + 1][j + 1]) % MOD;
                    }
                }

                if (maxScore == -1)
                    continue;

                score[i][j] = maxScore;
                ways[i][j] = count;

                // Add current cell value (except E)
                if (ch != 'E') {
                    score[i][j] += ch - '0';
                }
            }
        }

        if (ways[0][0] == 0)
            return new int[]{0, 0};

        return new int[]{score[0][0], ways[0][0]};
    }
}