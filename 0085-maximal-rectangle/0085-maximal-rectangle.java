import java.util.Stack;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int cols = matrix[0].length;
        int[] heights = new int[cols + 1]; // extra 0 flushes the stack
        int maxArea = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    heights[col]++;
                } else {
                    heights[col] = 0;
                }
            }

            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i <= cols; i++) {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int height = heights[stack.pop()];
                    int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                    int width = i - leftBoundary - 1;
                    maxArea = Math.max(maxArea, height * width);
                }
                stack.push(i);
            }
        }

        return maxArea;
    }
}