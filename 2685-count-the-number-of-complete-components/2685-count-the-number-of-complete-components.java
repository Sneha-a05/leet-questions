import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int start = 0; start < n; start++) {
            if (visited[start]) continue;

            Stack<Integer> stack = new Stack<>();
            stack.push(start);
            visited[start] = true;

            int vertices = 0;
            int degreeSum = 0;

            while (!stack.isEmpty()) {
                int node = stack.pop();
                vertices++;
                degreeSum += graph.get(node).size();

                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        stack.push(neighbor);
                    }
                }
            }

            // A complete component with k vertices has total degree k * (k - 1).
            if (degreeSum == vertices * (vertices - 1)) {
                completeComponents++;
            }
        }

        return completeComponents;
    }
}