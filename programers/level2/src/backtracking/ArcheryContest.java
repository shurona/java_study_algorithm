package backtracking;

import java.util.Arrays;
import utils.AlgoStudy;

public class ArcheryContest implements AlgoStudy {

    private static int[] ryanArrow = new int[11];
    private static int[] answer = {-1};
    private static int score = -1;

    public int[] solution(int n, int[] info) {
//        int[] answer = {};

        backTracking(0, n, info);

        return answer;
    }

    private void backTracking(int count, int n, int[] info) {

        if (n == count) {
            if (ryanArrow[0] == 3 && ryanArrow[1] == 2) {
                System.out.println(Arrays.toString(ryanArrow));
            }

            calculateScore(info);
            return;
        }

        for (int i = 0; i < info.length; i++) {
            if (ryanArrow[i] > info[i]) {
                break;
            }
            ryanArrow[i] += 1;
            backTracking(count + 1, n, info);
            ryanArrow[i] -= 1;
        }
    }

    private void calculateScore(int[] info) {
        int apeach = 0;
        int ryan = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] == 0 && ryanArrow[i] == 0) {
                continue;
            }
            if (info[i] >= ryanArrow[i]) {
                apeach += (10 - i);
            } else {
                ryan += (10 - i);
            }
        }

        if (ryanArrow[0] == 3 && ryanArrow[1] == 2) {
//            System.out.println(apeach + "  :  " + ryan);
        }

        if (apeach >= ryan) {
            score = Math.max(score, -1);
        } else {
            int diff = ryan - apeach;
            if (diff >= score) {
                score = diff;
                answer = ryanArrow.clone();
            }
        }
    }


    @Override
    public void init() {
        int[] solution = solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
        System.out.println("출력합니다. " + Arrays.toString(solution));
    }
}
