https://leetcode.com/problems/contains-duplicate
// Algo 1 : Sorting : O(nlogn) , O(1)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1 ; i < nums.length ; i++) { // starting with i=1 as we have to check for i-1
            if(nums[i-1] == nums[i]) return true;
        }
        return false;
    }
}
// Algo 2 : Using HashSet : O(n) , O(n)
public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length);
    for (int x: nums) {
        if (set.contains(x)) return true;
        set.add(x);
    }
    return false;
}