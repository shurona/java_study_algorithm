package MathProblem;

import utils.AlgoStudy;

import java.util.Arrays;

public class JumpAndTeleportation implements AlgoStudy {
    public int solution(int n) {
        int plus = 0;
        int yahoo = n;

        while (yahoo > 1) {
            if (yahoo % 2 == 1) {
                plus += 1;
                yahoo -=1;
            }
            yahoo -= (yahoo / 2);
        }


        // K칸 점프
        // 온 거리 * 2 순간이동 => 안
        // 건전 지 최소 사용
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int div = i % 2 == 0 ? dp[i - (i / 2)] : Integer.MAX_VALUE;
            int before = dp[i - 1] + 1;
//            System.out.println(i + " = " + div + " : " + before);
            dp[i] = Math.min(div, before);
        }

        System.out.println(dp[n]);
        return plus + 1;
    }

    @Override
    public void init() {
        System.out.println(solution(6));
    }
}
