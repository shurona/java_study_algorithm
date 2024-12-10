package dp;

import utils.AlgoStudy;

/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/12945
 */
public class FibonacciNumbers implements AlgoStudy {

    int[] dp = new int[100001];

    public int solution(int n) {

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        // int answer = 0;
        return dp[n];
    }

    @Override
    public void init() {

    }
}
