package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

public class PrefixSum5 implements BaekAlgoStudy {


    public void rowDirection(int[][] origin, int[][] sum) {
        for (int row = 0; row < sum.length; row++) {
            sum[row][0] = origin[row][0];
            for (int col = 1; col < sum.length; col++) {
                sum[row][col] = sum[row][col - 1] + origin[row][col];
            }
        }

    }

    public void colDirection(int[][] origin, int[][] sum) {
        for (int col = 0; col < sum.length; col++) {
            for (int row = 1; row < sum.length; row++) {
                sum[row][col] = sum[row - 1][col] + sum[row][col];
            }
        }
    }

    public void solution() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String[] first = reader.readLine().split(" ");

            int a = Integer.parseInt(first[0]);
            int b = Integer.parseInt(first[1]);

            int[][] dp = new int[a][a];
            int[][] sumDp = new int[a][a];

            for (int i = 0; i < a; i++) {
                String[] oneLine = reader.readLine().split(" ");

                for (int j = 0; j < a; j++) {
                    dp[i][j] = Integer.parseInt(oneLine[j]);
                }

            }

            rowDirection(dp, sumDp);
            colDirection(dp, sumDp);

            for (int i = 0; i < b; i++) {
                int[] given = Arrays.stream(reader.readLine().split(" "))
                    .map(d -> Integer.parseInt(d)).mapToInt(d -> d).toArray();

                int sRow = given[0] - 1;
                int sCol = given[1] - 1;
                int eRow = given[2] - 1;
                int eCol = given[3] - 1;

                int maxSum = sumDp[eRow][eCol];
                int minusRow = 0;
                int minusCol = 0;
                int sumDup = 0;
                if (sRow > 0) {
                    minusRow = sumDp[sRow - 1][eCol];
                }

                if (sCol > 0) {
                    minusCol = sumDp[eRow][sCol - 1];
                }

                if (sRow > 0 && sCol > 0) {
                    sumDup = sumDp[sRow - 1][sCol - 1];
                }

//                System.out.println(maxSum + " " + sumDup + " " + minusRow + " " + minusCol);

                System.out.println(maxSum + sumDup - minusCol - minusRow);
            }
//            for (int[] ints : sumDp) {
//                System.out.println(Arrays.toString(ints));
//            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
