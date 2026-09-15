package week4_pp;

public class BestTimeToBuyAndSellStock {
    static int maxProfit(int[] prices) {
        int min = prices[0], profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            else
                profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}