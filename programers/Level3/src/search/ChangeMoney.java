package search;

import utils.AlgoLevel3Study;

import java.util.*;

/*
      거스름돈을 줄 경우의 수
      5 => 1 1 3
 */
public class ChangeMoney implements AlgoLevel3Study {

    int count = 0;
    int gizun = 0;

    static class ForBfs {
        String stack;
        int sumOfMoney;

        public ForBfs(String stack, int sumOfMoney) {
            this.stack = stack;
            this.sumOfMoney = sumOfMoney;
        }
    }

    public void dfs(int[] money, int beforeNumber, int sumOfMoney) {

        if (sumOfMoney == this.gizun) {
//                System.out.println(data.stack);
            this.count = (this.count + 1) % 1000000007;
            return;
        }

        if (sumOfMoney > this.gizun) {
            return;
        }

        for (int m : money) {
            if (m > beforeNumber) {
                continue;
            }
            dfs(money, m, sumOfMoney + m);
        }
    }

    public int[][] dp(int n, int[] money) {
        int[][] dp = new int[money.length + 1][n + 1];
        // 최대를 갖고 만들 수 있는 건 위의 값  + 자신 만큼 뺀 값
        for (int row = 1; row <= money.length; row++) {
            for (int col = 0; col <= n; col++) {
                if (col == 0) {
                    dp[row][col] = 1;
                } else if (col < money[row - 1]) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - money[row - 1]];
                }

            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp;
    }

    public int solution(int n, int[] money) {

//        this.gizun = n;
//
        Arrays.sort(money);
//
//        dfs(money,Integer.MAX_VALUE, 0);

        int[][] dp = dp(n, money);

        return dp[dp.length - 1][dp[0].length - 1];
    }

    @Override
    public void init() {
        int n = 8;
        int[] money = {1, 2, 5};
//        int[] money = {2, 3, 5, 7};
        System.out.println(solution(n, money));
    }
}
