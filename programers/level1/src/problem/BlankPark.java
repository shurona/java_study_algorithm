package problem;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/340198
 */
public class BlankPark {

    int check(String[][] park, int row, int col) {

        int step = 1;
        boolean isGo = true;
        while (true) {
            // 범위를 확인한다.
            if (row + step >= park.length || col + step >= park[0].length) {
                break;
            }

            // row 확인
            for (int i = 0; i <= step; i++) {
                // System.out.println(row + step + " : " + (col + i));
                if (!park[row + step][col + i].equals("-1")) {
                    isGo = false;
                }
            }

            // col 확인
            for (int i = 0; i <= step; i++) {
                // System.out.println(row + i + " : " + (col + step));
                if (!park[row + i][col + step].equals("-1")) {
                    isGo = false;
                }
            }

            if (!isGo) {
                break;
            }

            step += 1;
            // System.out.println();
        }

        return step;

    }

    public int solution(int[] mats, String[][] park) {
        int max = -1;
        for (int row = 0; row < park.length; row++) {
            for (int col = 0; col < park[0].length; col++) {
                if (park[row][col].equals("-1")) {
                    int d = check(park, row, col);
                    max = Math.max(max, check(park, row, col));
                    // System.out.println();
                }
            }
        }

        Arrays.sort(mats);

        // System.out.println(Arrays.toString(mats));

        int answer = 0;
        for (int i = 0; i < mats.length; i++) {
            if (mats[i] <= max) {
                answer = mats[i];
            }
        }

        if (answer == 0) {
            return -1;
        }

        return answer;
    }
}
