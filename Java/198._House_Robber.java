https://leetcode.com/problems/house-robber/
// Algo 1 : Recursion with Memoization : O(n) , O(n)
// Recursive relation : robFrom(i) = max(robFrom(i+1),robFrom(i+2)+nums(i))
class Solution {
    private int[] nums;
    private int n;
    private int[] memo;
    public int rob(int[] nums) {
        init(nums);
        return maxMoneyStartingFrom(0);
    }
    private void init(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.memo = new int[n];
        Arrays.fill(memo , -1); // Fill with sentinel value representing not-calculated recursions.
    }
    private int maxMoneyStartingFrom(int startIndex) {
        if(startIndex >= n) return 0;                           // No more houses left to examine.
        if(memo[startIndex] != -1) return memo[startIndex];     // Return cached value.
        
        int skipCurrentHouse = maxMoneyStartingFrom(startIndex+1);
        int includeCurrentHouse = nums[startIndex] + maxMoneyStartingFrom(startIndex+2);
        int maxMoney = Math.max(skipCurrentHouse , includeCurrentHouse);
        
        memo[startIndex] = maxMoney;                            // Cache the value
        return maxMoney;
    }
    
}
// Algo 2 : DP : O(n) , O(n)
class Solution {    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int[] maxRobbedAmount = new int[n+1];
        maxRobbedAmount[n] = 0; // Base Case : ArrayIndexOutOfBounds
        maxRobbedAmount[n-1] = nums[n-1];
        for (int startIndex = n-2; startIndex >= 0; startIndex--) {// DP table calculations.
          int skipCurrentHouse = maxRobbedAmount[startIndex+1];
          int includeCurrentHouse = nums[startIndex] + maxMoneyStartingFrom(startIndex+2);
          maxRobbedAmount[startIndex] = Math.max(skipCurrentHouse, includeCurrentHouse);
        }       
        return maxRobbedAmount[0];
    }
}
// Algo 3 : DP Optmizied : O(n) , O(1)
class Solution {    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int robNext = 0, robNextPlusOne = nums[n-1];
        // DP table calculations. Note: we are not using any table here for storing values. Just using two variables will suffice.
        for (int i = n-2; i >= 0; --i) {
            int current = Math.max(robNext, robNextPlusOne + nums[i]);// Same as the recursive solution.
            robNextPlusOne = robNext;
            robNext = current;
        }
        return robNext;
    }
}