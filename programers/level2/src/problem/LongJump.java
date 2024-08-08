package problem;

import utils.AlgoStudy;

public class LongJump implements AlgoStudy {

    public long solution(int n) {

        long[] dp = new long[2001];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }


        return dp[n];
    }

    @Override
    public void init() {
        long solution = solution(500);
        System.out.println("정답은 : " + solution);
    }
}
