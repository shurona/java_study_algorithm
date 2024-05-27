package MathProblem;

import utils.AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HailSequence implements AlgoStudy {
    public double[] solution(int k, int[][] ranges) {

        // k는 시작값
        // n은 1이 되는 횟수
        // 짝수면 2로 나눔
        // 홀수면 3을 곱하고 1을 더함

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(k);
        int n = 0;
        while (k != 1) {
            if (k % 2 == 0) {
                k = k / 2;
            } else {
                k = k * 3 + 1;
            }
            n +=1;
            arr.add(k);
        }

        double[] dp = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            // 높이는 1로 고정
            int leftRow = arr.get(i - 1);
            int rightRow = arr.get(i);
            dp[i] = (double) (leftRow + rightRow) / 2;

        }

        System.out.println(arr);
        System.out.println("dp = " + Arrays.toString(dp));

        double[] answer = new double[ranges.length];
        for (int index = 0; index < ranges.length; index++) {
            int start = ranges[index][0];
            int end = n + ranges[index][1];

            if (start > end) {
                answer[index] = -1;
                continue;
            } else if (start == end) {
                answer[index] = 0;
                continue;
            }

            double sum = 0;
            for (int j = start + 1; j < end + 1; j++) {
                sum += dp[j];
            }
            answer[index] = sum;

        }
        return answer;
    }

    @Override
    public void init() {
//        int[][] data = {
//                {0, 0},
//                {0, -1},
//                {2, -3},
//                {3, -3}
//        };

        int[][] data = {
                {0, 0},
                {1, -2},
                {3, -3}
        };

        System.out.println(Arrays.toString(solution(3, data)));

    }
}
