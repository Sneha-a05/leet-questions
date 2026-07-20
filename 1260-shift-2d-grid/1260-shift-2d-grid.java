class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        k %= total;
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                int newIndex = i * n + j;
                int oldIndex = (newIndex - k + total) % total;

                row.add(grid[oldIndex / n][oldIndex % n]);
            }

            result.add(row);
        }

        return result;
    }
}