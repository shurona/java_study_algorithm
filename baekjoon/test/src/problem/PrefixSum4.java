package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

public class PrefixSum4 implements BaekAlgoStudy {

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String[] first = reader.readLine().split(" ");

            int[] input = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).mapToInt(i -> i).toArray();

            int[] dp = new int[input.length];

            dp[0] = input[0];
            for (int i = 1; i < input.length; i++) {
                dp[i] = dp[i - 1] + input[i];
            }

            for (int i = 0; i < Integer.parseInt(first[1]); i++) {
                String[] tp = reader.readLine().split(" ");

                int start = Integer.parseInt(tp[0]) - 1;
                int end = Integer.parseInt(tp[1]) - 1;

                int minus = 0;

                if (start > 0) {
                    minus = dp[start - 1];
                }

                int plus = dp[end];

                System.out.println(plus - minus);
            }

            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


    @Override
    public void init() throws IOException {
        solution();
    }
}
