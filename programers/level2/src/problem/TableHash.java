package problem;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.Comparator;

public class TableHash implements AlgoStudy {
    public int solution(int[][] data, int col, int row_begin, int row_end) {

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[col - 1] != o2[col - 1]) {
                    return o1[col - 1] - o2[col - 1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });

        int answer = 0;
        for (int i = (row_begin - 1); i <= (row_end - 1); i++) {
            int si = 0;
            for (int c = 0; c < data[0].length; c++) {
                si += data[i][c] % (i + 1);
            }

            if (answer == 0) {
                answer = si;
            } else {
                answer = answer ^ si;
            }
        }
        return answer;
    }

    @Override
    public void init() {
        int[][] data = {
                {2, 2, 6},
                {1, 5, 10},
                {4, 2, 9},
                {3, 8, 3}
        };

        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        int solution = solution(data, col, 2, 3);
        System.out.println("TableHash.init : " + solution);

    }
}
