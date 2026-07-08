class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {

            // Cannot reach this position
            if (i > maxReach) {
                return false;
            }

            maxReach = Math.max(maxReach, i + nums[i]);

            // Already can reach the end
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }
}