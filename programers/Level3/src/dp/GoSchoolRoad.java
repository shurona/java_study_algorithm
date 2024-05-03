package dp;

import java.util.Arrays;

public class GoSchoolRoad {
    public void solution() {
        int m = 5;
        int n = 3;
        int[][] puddles = {{2, 2}};

        int gizun = 1000000007;
        int[][] saved = new int[n][m];

        for (int[] puddle : puddles) {
            saved[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        saved[0][0] = 1;

        //왼쪽에서 오거나 위에서 아래로 오는 경우만 있다.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (saved[row][col] < 0) {
                    continue;
                }
                // 시작 지점인 경우 패스
                if (col == 0 && row == 0) {
                    continue;
                } else if(row == 0){
                    // 제일 위의 경로 인 경우
                    saved[row][col] = saved[row][col - 1];
                } else if (col == 0) {
                    // 제일 왼쪽 경로 인 경우
                    saved[row][col] = saved[row - 1][col];
                } else{
                    // 나머지
                    int left = saved[row][col - 1] < 0 ? 0 : saved[row][col - 1] % gizun;
                    int up = saved[row - 1][col] < 0 ? 0 : saved[row - 1][col] % gizun;

                    saved[row][col] = left + up;

                }
            }
        }

        int answer = (int) ((saved[n - 1][m - 1]) % gizun);

        System.out.println("saved = " + Arrays.deepToString(saved));

        System.out.println("answer = " + answer);
    }
}
