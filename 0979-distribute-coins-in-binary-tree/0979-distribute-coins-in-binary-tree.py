class Solution(object):
    def distributeCoins(self, root):
        self.moves = 0

        def dfs(node):
            if node is None:
                return 0

            left_balance = dfs(node.left)
            right_balance = dfs(node.right)

            self.moves += abs(left_balance) + abs(right_balance)

            # Extra coins (+) or missing coins (-) passed to parent
            return node.val + left_balance + right_balance - 1

        dfs(root)
        return self.moves