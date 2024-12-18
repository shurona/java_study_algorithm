package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

public class FibonacciNumberSecond implements BaekAlgoStudy {

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int input = Integer.parseInt(reader.readLine());

            long[] dp = new long[91];

            dp[0] = 0;
            dp[1] = 1;

            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            System.out.println(dp[input]);

            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
