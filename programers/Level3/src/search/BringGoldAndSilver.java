package search;

import utils.AlgoLevel3Study;

import java.util.Arrays;

public class BringGoldAndSilver implements AlgoLevel3Study {
    public int findTime(long currentTime, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int goldMax = 0;
        int silverMax = 0;
        int both = 0;

        // gold, silver
        for (int index = 0; index < t.length; index++) {
            int gold = g[index];
            int silver = s[index];
            int weight = w[index];
            int time = t[index];

            // 옮기는 횟수 * weight 옮길 수 있는 weight를 확한다.
            long ct = currentTime / (time * 2L);
            int mod = (int) (currentTime % (time * 2));
            if(mod >= time) ct += 1;

            if (currentTime == 45) {
                System.out.println("하로 : " + currentTime + " :: " + ct + " :: " + time * 2L + "  ::  " + mod);
            }


            // 금을 옮길 수 있는 최대량
            goldMax += (int) Math.min(ct * weight, gold);
            silverMax += (int) Math.min(ct * weight, silver);
            both += (int) Math.min(gold + silver, ct * weight);
        }

        if (currentTime == 45) {
            System.out.println("여기 : " + currentTime + " :: " + goldMax + " :: " + silverMax + "  ::  " + both);
        }

        // 3개다 넘으면 최댓값을 낮춰준다.
        if (goldMax >= a && silverMax >= b && both >= (a + b)) {
            return -1;
        } else {
            return 1;
        }
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;

//        long maxTime = 1000000000000000L;
        long maxTime = 1000;
        long minTime = 0;
        long mid = 0;
        while (maxTime >= minTime) {
            mid = (maxTime + minTime) / 2;
            int check = findTime(mid, a, b, g, s, w, t);
            System.out.println(maxTime + " :: " + minTime + " :: " + mid);
            if (check < 0) {
                // 옮긴 경우
                maxTime = mid - 1;
                answer = Math.min(mid, answer);
            } else {
                minTime = mid + 1;
            }
        }
        return mid;
    }

    @Override
    public void init() {
        int a = 10;
        int b = 10;

        int[] g = {100};
        int[] s = {100};
        int[] w = {7};
        int[] t = {10};

//        int a = 90;
//        int b = 500;
//
//        int[] g = {70, 70, 0};
//        int[] s = {0, 0, 500};
//        int[] w = {100, 100, 2};
//        int[] t = {4, 8 ,1};


        long solution = solution(a, b, g, s, w, t);
        System.out.println("답안 : " + solution);
    }
}
