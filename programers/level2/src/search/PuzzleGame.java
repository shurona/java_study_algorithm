package search;

import utils.AlgoStudy;

public class PuzzleGame implements AlgoStudy {

    // wrong answer(시간 초과)
    public int wrongSolution(int[] diffs, int[] times, long limit) {

        int maxInt = Integer.MIN_VALUE;

        for (int i = 0; i < diffs.length; i++) {
            maxInt = Math.max(diffs[i], maxInt);
        }

        int answer = 1;
        // 최대값에서 하나씩 감소시키면서 확인한다.
        for (int i = 1; i <= maxInt; i++) {
            // dp[i] : i 번째 까지 걸리는 시간
            long[] dp = new long[times.length + 1];
            int level = i;
            long sum = 0;
            for (int index = 0; index < diffs.length; index++) {
                int diff = diffs[index];
                long cost;
                if (diff > level) {
                    cost = (diff - level) * (times[index - 1] + times[index]) + times[index];
                } else {
                    cost = times[index];
                }

                // 다음 거 저장
                dp[index + 1] = cost;
                sum += cost;
            }

            if (i < 5) {
                // System.out.println(i + " : " + sum + " : " + Arrays.toString(dp));
            }
            if (sum <= limit) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    public int solution(int[] diffs, int[] times, long limit) {

        int maxInt = Integer.MIN_VALUE;

        for (int i = 0; i < diffs.length; i++) {
            maxInt = Math.max(diffs[i], maxInt);
        }

        int minInt = 1;
        int middle = (minInt + maxInt) / 2;
        int answer = 0;
        while (minInt <= maxInt) {

            System.out.println(minInt + " : " + middle + " : " + maxInt);
            middle = (minInt + maxInt) / 2;
            long sum = 0;
            for (int index = 0; index < diffs.length; index++) {
                int diff = diffs[index];
                long cost;
                if (diff > middle) {
                    cost = (diff - middle) * (times[index - 1] + times[index]) + times[index];
                } else {
                    cost = times[index];
                }

                // 다음 거 저장
                sum += cost;
            }

            // 만약 sum이 limit 보다 작으면
            if (sum == limit) {
                answer = middle;
                break;
            } else if (sum < limit) {
                sum = 0;
                for (int index = 0; index < diffs.length; index++) {
                    int diff = diffs[index];
                    long cost;
                    if (diff > (middle - 1)) {
                        if (index == 0) {
                            cost = limit;
                        } else {
                            cost =
                                (diff - (middle - 1)) * (times[index - 1] + times[index])
                                    + times[index];
                        }

                    } else {
                        cost = times[index];
                    }

                    // 다음 거 저장
                    sum += cost;
                }
                
                if (sum > limit) {
                    answer = middle;
                    break;
                }
                maxInt = middle - 1;
            } else {
                minInt = middle + 1;
            }

        }
        return answer;
    }

    @Override
    public void init() {
        int solution = solution(new int[]{1}, new int[]{3}, 30);
        System.out.println("정답은 : " + solution);
    }
}
