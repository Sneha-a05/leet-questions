class Solution {

    int postIdx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postIdx--]);
        int i = left;

        while (inorder[i] != root.val) {
            i++;
        }

        root.right = build(inorder, postorder, i + 1, right);
        root.left = build(inorder, postorder, left, i - 1);

        return root;
    }
}