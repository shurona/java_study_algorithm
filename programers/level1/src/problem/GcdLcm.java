package problem;

import java.util.Arrays;

public class GcdLcm {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];

        int maxNum = Math.max(n, m);
        int minNum = Math.min(n, m);

        for (int start = 1; start <= minNum; start++) {
            if (minNum %  start == 0 && maxNum % start == 0) {
                answer[0] = start;
            }
        }

        answer[1] = (n * m) / answer[0];

        return answer;
    }


    public void init() {
        System.out.println("GcdLcm.init");
        int[] given = {3, 12};
//        int[] given = {2, 5};

        int[] solution = solution(given[0], given[1]);

        System.out.println("solution = " + Arrays.toString(solution));
    }
}
