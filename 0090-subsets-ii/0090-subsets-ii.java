class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // Sort the array so that duplicate values become adjacent.
        Arrays.sort(nums);

        // Start recursion from index 0 with an empty subset.
        friend(0, nums, new ArrayList<>(), result);

        return result;
    }

    public static void friend(int idx,
                              int[] nums,
                              List<Integer> presentList,
                              List<List<Integer>> result) {

        // If every element has been processed,
        // store the current subset.
        if (idx == nums.length) {
            result.add(new ArrayList<>(presentList));
            return;
        }

        // Choice 1:
        // Include the current element.
        presentList.add(nums[idx]);
        friend(idx + 1, nums, presentList, result);


        // Backtrack:
        // Undo the previous choice before
        presentList.remove(presentList.size() - 1);

        // Skip every duplicate of the current value.
        int pos = idx + 1;
        while (pos < nums.length && nums[pos] == nums[idx]) {
            pos++;
        }

        // Choice 2:
        // Exclude the current value and all of its duplicates.
        friend(pos, nums, presentList, result);
    }
}