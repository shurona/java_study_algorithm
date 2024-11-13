package mathstepone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

public class SumOfDivisor implements BaekAlgoStudy {

    //    https://www.acmicpc.net/problem/17425
    public int checkDivisor(int num) {
        int sqrt = (int) Math.floor(Math.sqrt(num));
        int sum = 0;
        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                if (i == num / i) {
                    sum += i;
                } else {
                    sum += i;
                    sum += num / i;
                }
            }
        }

        return sum;
    }

    public void solution() {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String input = "init";

        try {
            int dpLength = 1000000;

            long[] fx = new long[dpLength + 1];
            long[] dp = new long[dpLength + 1];
            Arrays.fill(fx, 1);
            for (int i = 2; i <= dpLength; i++) {
                int count = 1;
                while ((i * count) < dpLength) {

                    fx[i * count] += i;

                    count++;
                }
            }

            dp[1] = 1;
            for (int i = 2; i <= dpLength; i++) {
                dp[i] = fx[i] + dp[i - 1];
            }

            int s = Integer.parseInt(bfr.readLine());
            for (int i = 0; i < s; i++) {
                String s1 = bfr.readLine();
                System.out.println(dp[Integer.parseInt(s1)]);
            }

            bfr.close();

        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    @Override
    public void init() {
        solution();
    }
}
