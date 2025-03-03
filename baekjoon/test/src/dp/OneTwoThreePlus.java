package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/9095
 */
public class OneTwoThreePlus implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());

        int[] dp = new int[13];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 12; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input; i++) {
            sb.append(dp[Integer.parseInt(reader.readLine())]).append("\n");
        }
        System.out.println(sb);

        reader.close();


    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
