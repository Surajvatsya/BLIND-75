https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// Algo 1 : Brute force : O(n^2) , O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for(int i = 0 ; i < prices.length ; i++) {
            for(int j = i+1 ; j < prices.length ; j++) {
                if(maxProfit < (prices[j] - prices[i])) 
                    maxProfit = prices[j] - prices[i];
            }
        }
        return maxProfit;
        
    }
}
// Algo 2 : One-Pass Iteration : O(n) , O(1)
public class Solution {
    public int maxProfit(int prices[]) {
        int minPrice = Integer.MAX_VALUE , maxProfit = 0;
        for(int i = 0 ; i < prices.length ; i++) {
            if(minPrice > prices[i])
                minPrice = prices[i];
            if(maxProfit < prices[i] - minPrice)
                maxProfit = prices[i] - minPrice;
        } 
        return maxProfit;
    }
}