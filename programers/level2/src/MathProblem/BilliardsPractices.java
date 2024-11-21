package MathProblem;

import java.util.Arrays;
import utils.AlgoStudy;

/*
    문제 풀이
    https://school.programmers.co.kr/learn/courses/30/lessons/169198
 */
public class BilliardsPractices implements AlgoStudy {

    public double findXInclination(int[] ball, int[] target) {
        return (double) (ball[1] * target[0] + target[1] * ball[0]) / (ball[1] + target[1]);
    }

    public double findYInclination(int[] ball, int[] target) {
        return (double) (target[0] * ball[1] + ball[0] * target[1]) / (ball[0] + target[0]);
    }

    public int[] convertXLoc(int[] target, int base) {

        int targetX = target[0];
        if (base == 0) {
            return new int[]{targetX * (-1), target[1]};
        } else {
            return new int[]{targetX + (base - targetX) * 2, target[1]};
        }
    }

    public int[] convertYLoc(int[] target, int base) {

        int targetY = target[1];
        if (base == 0) {
            return new int[]{target[0], targetY * (-1)};
        } else {
            return new int[]{target[0], targetY + (base - targetY) * 2};
        }

    }

    public int calDistance(int[] start, int[] middle) {

        int sx = start[0];
        int sy = start[1];
        int mx = middle[0];
        int my = middle[1];

        int xDistance = Math.abs(sx - mx);
        int yDistance = Math.abs(sy - my);

        return (yDistance * yDistance) + (xDistance * xDistance);
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] start = {startX, startY};

        int[] answer = new int[balls.length];
        // x와 y 거리 비교
        for (int i = 0; i < balls.length; i++) {
            int tp;
            int minValue = 10000000;

            // y 0좌표에 튕겼을 때
            tp = calDistance(start, convertXLoc(balls[i], 0));
            if (start[1] == balls[i][1] && start[0] > balls[i][0]) {
                tp = 10000000;
            }

            // 최소치를 넣는다.
            minValue = Math.min(minValue, tp);

            // System.out.println(Arrays.toString(convertXLoc(balls[i], 0)));
            // System.out.println((int) Math.round(tp * tp));

            // y n좌표에 튕겼을 때
            tp = calDistance(start, convertXLoc(balls[i], m));
            if (start[1] == balls[i][1] && start[0] < balls[i][0]) {
                tp = 10000000;
            }
            // 최소치를 넣는다.
            minValue = Math.min(minValue, tp);

            // System.out.println(Arrays.toString(convertXLoc(balls[i], n)));
            // System.out.println((int) Math.round(tp * tp));

            // x 0좌표에 튕겼을 때
            tp = calDistance(start, convertYLoc(balls[i], 0));
            if (start[0] == balls[i][0] && start[1] > balls[i][1]) {
                tp = 10000000;
            }
            minValue = Math.min(minValue, tp);

            // System.out.println(Arrays.toString(convertYLoc(balls[i], 0)));
            // System.out.println((int) Math.round(tp * tp));

            // x m좌표에 튕겼을 때
            tp = calDistance(start, convertYLoc(balls[i], n));
            if (start[0] == balls[i][0] && start[1] < balls[i][1]) {
                tp = 10000000;
            }
            minValue = Math.min(minValue, tp);

            // System.out.println(Arrays.toString(convertYLoc(balls[i], m)));
            // System.out.println((int) Math.round(tp * tp));

            answer[i] = minValue;
            // System.out.println();

        }

        return answer;
    }

    @Override
    public void init() {
//        int m = 10;
//        int n = 10;
//        int startX = 2;
//        int startY = 2;

        int m = 1000;
        int n = 1000;
        int startX = 3;
        int startY = 7;
        int[][] balls = {{999, 999}, {7, 7}, {2, 7}, {7, 3}};
        int[] solution = solution(m, n, startX, startY, balls);
        System.out.println("정답은 : " + Arrays.toString(solution));
    }
}
