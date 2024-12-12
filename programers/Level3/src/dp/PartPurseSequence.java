package dp;

import utils.AlgoLevel3Study;

public class PartPurseSequence implements AlgoLevel3Study {

    public long solution(int[] sequence) {

        long[] dp = new long[sequence.length];

        long answer = sequence[0];
        dp[0] = sequence[0];
        int bit = 1;
        for (int i = 1; i < sequence.length; i++) {
            bit = bit * (-1);
            // 현재 값
            long current = sequence[i] * bit;

            //
            dp[i] = Math.max(current, current + dp[i - 1]);
            answer = Math.max(dp[i], answer);
        }

        dp = new long[sequence.length];
        dp[0] = sequence[0] * (-1);
        answer = Math.max(dp[0], answer);
        bit = -1;
        for (int i = 1; i < sequence.length; i++) {
            bit = bit * (-1);
            // 현재 값
            long current = sequence[i] * bit;

            //
            dp[i] = Math.max(current, current + dp[i - 1]);

            answer = Math.max(dp[i], answer);
        }
        // System.out.println(Arrays.toString(dp));

        return answer;
    }

    @Override
    public void init() {

    }
}
