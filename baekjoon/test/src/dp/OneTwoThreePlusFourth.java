package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/15989
 */
public class OneTwoThreePlusFourth implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());

        int[][] dp = new int[4][10002];

        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[2][2] = 1;
        dp[1][3] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;

        for (int i = 4; i < dp[0].length; i++) {
            dp[1][i] = dp[1][i - 1];
            dp[2][i] = dp[2][i - 2] + dp[1][i - 2];
            dp[3][i] = dp[3][i - 3] + dp[2][i - 3] + dp[1][i - 3];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input; i++) {
            int tp = Integer.parseInt(reader.readLine());
            int sum = 0;
            for (int j = 1; j <= 3; j++) {
                sum += dp[j][tp];
            }

            sb.append(sum).append("\n");

        }

        System.out.println(sb);
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
