https://leetcode.com/problems/two-sum
//Algo 1 : Brute force : O(n^2) , O(1)
class Solution {
  public int[] twoSum(int[] nums, int target) {
    for(int i = 0 ; i < nums.length ; i++) {
        for(int j = i+1 ; j < nums.length ; j++) {Note : j = i+1
            if(nums[i] + nums[j] == target) {
                return new int[] {i , j};
            }
        }
    }
    return null;
  }
}
// Algo 2 : Two-pass HashTable : O(n) , O(n)
class Solution {
  public int[] twoSum(int[] nums, int target) { 
    // First Pass : Map : value to index
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int i = 0 ; i < nums.length ; i++) {
        map.put(nums[i] , i);
    }
    // Second pass : check if complement exists using HashMap
    for(int i = 0 ; i < nums.length ; i++) {
        int complement = target - nums[i];
        if(map.containsKey(complement) && map.get(complement) != i) {
            return new int[]{i , map.get(complement)};
        }
    }
    return null;
  }
}
// Algo 3 : One-pass HashTable : O(n) , O(n)
class Solution {
  public int[] twoSum(int[] nums, int target) { 
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int i = 0 ; i < nums.length ; i++) {
        int complement = target - nums[i];
        if(map.containsKey(complement)) {
            return new int[] {i , map.get(complement)}; 
        }
        map.put(nums[i] , i);
    }
    throw new IllegalArgumentException("No Solution found");
  }
}