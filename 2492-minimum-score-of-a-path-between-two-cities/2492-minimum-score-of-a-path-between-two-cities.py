class Solution:
    def minScore(self, n, roads):
        from collections import defaultdict, deque

        graph = defaultdict(list)
        for a, b, d in roads:
            graph[a].append((b, d))
            graph[b].append((a, d))

        visited = [False] * (n + 1)
        q = deque([1])
        visited[1] = True
        ans = float('inf')

        while q:
            node = q.popleft()
            for nei, dist in graph[node]:
                ans = min(ans, dist)
                if not visited[nei]:
                    visited[nei] = True
                    q.append(nei)

        return ans