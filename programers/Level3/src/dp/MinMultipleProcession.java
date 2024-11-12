package dp;

import java.util.Arrays;
import utils.AlgoLevel3Study;

public class MinMultipleProcession implements AlgoLevel3Study {

    public int solution(int[][] matrix_sizes) {

        int[][] temp = new int[matrix_sizes.length][matrix_sizes.length];

        for (int[] tp : temp) {
            Arrays.fill(tp, Integer.MAX_VALUE);
        }

        for (int i = 0; i < matrix_sizes.length; i++) {
            for (int j = 0; j < matrix_sizes.length; j++) {

                int a = j;
                int b = j + i;

                if (b > matrix_sizes.length - 1) {
                    break;
                }

                if (a == b) {
                    temp[a][b] = 0;
                } else {
                    for (int k = a; k < b; k++) {
                        temp[a][b] = Math.min(temp[a][b],
                            temp[a][k] + temp[k + 1][b]
                                + (matrix_sizes[a][0] * matrix_sizes[k][1] * matrix_sizes[b][1]));
                    }
                }
            }
        }

        for (int[] dd : temp) {
            System.out.println(Arrays.toString(dd));
        }

        int answer = temp[0][matrix_sizes.length - 1];

        return answer;
    }

    @Override
    public void init() {
        solution(new int[][]{{5, 3}, {3, 10}, {10, 6}});
    }
}
