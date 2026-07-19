import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();

        for (String folder : path.split("/")) {
            if (folder.isEmpty() || folder.equals(".")) {
                continue;
            } else if (folder.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.addLast(folder);
            }
        }

        return "/" + String.join("/", stack);
    }
}