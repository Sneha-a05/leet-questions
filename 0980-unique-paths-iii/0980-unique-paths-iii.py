class Solution(object):
    def uniquePathsIII(self, grid):
        rows = len(grid)
        cols = len(grid[0])
        empty_count = 0

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] != -1:
                    empty_count += 1
                if grid[r][c] == 1:
                    start_r, start_c = r, c

        def dfs(r, c, visited):
            if grid[r][c] == 2:
                return 1 if visited == empty_count else 0

            original = grid[r][c]
            grid[r][c] = -1
            paths = 0

            for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                nr = r + dr
                nc = c + dc

                if 0 <= nr < rows and 0 <= nc < cols and grid[nr][nc] != -1:
                    paths += dfs(nr, nc, visited + 1)

            grid[r][c] = original
            return paths

        return dfs(start_r, start_c, 1)