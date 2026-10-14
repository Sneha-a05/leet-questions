class Solution {

    public String maxSumOfSquares(int num, int sum) {
        // return empty string if sum cannot be achieved even with number of all 9s
        if (sum > 9*num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // append as many nines as possible
        int nines = sum / 9;
        for (int i = 0; i < nines; i++) {
            ans.append('9');
        }

        // append the remainder after the 9s
        int remainder = sum % 9;
        if (remainder > 0) {
            ans.append((char) ('0' + remainder));
        }

        // append 0 for the remaining length
        int zeros = num - ans.length();
        for (int i = 0; i < zeros; i++) {
            ans.append('0');
        }

        return ans.toString();
    }
}