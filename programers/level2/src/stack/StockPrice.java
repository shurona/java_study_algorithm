package stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StockPrice {
    public void solution() {
        int[] prices = { 4, 5, 1, 2, 6, 1, 1 };

        int[] answer = new int[prices.length];

        Queue<Integer> remain = new LinkedList<>();
        remain.add(0);

        int currentPrice;
        for (int i = 1; i < prices.length; i++) {
            currentPrice = prices[i];
            int currentRemainSize = remain.size();
            for (int j = 0; j < currentRemainSize; j++) {
                int second = remain.poll();
                if (currentPrice < prices[second]) {
                    answer[second] = i - second;
                } else {
                    remain.add(second);
                }
            }
            remain.add(i);
        }

        for (int remainSecond : remain) {
            answer[remainSecond] = prices.length - 1 - remainSecond;
        }

        System.out.println(Arrays.toString(answer));
    }
}
