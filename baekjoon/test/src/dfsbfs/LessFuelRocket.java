package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/17484
 */
public class LessFuelRocket implements BaekAlgoStudy {

    static int maxStep;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1};

    private static void goToMoon(int[][] board, int beforeDir, int loc, int sum, int step,
        String check) {
        if (step == maxStep) {
            System.out.println(check + " : " + sum);
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < 3; i++) {
            // 같은 방향이면 패스
            if (beforeDir == i) {
                continue;
            }
            int nextCol = loc + dx[i];
            if (nextCol >= 0 && nextCol < board[0].length) {
                goToMoon(board, i, nextCol, sum + board[step][loc], step + 1,
                    check + " " + beforeDir);
            }
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int row = input[0];
        int col = input[1];

        maxStep = row;

        int[][] board = new int[row][col];
        for (int i = 0; i < row; i++) {
            int[] loc = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(d -> Integer.parseInt(d)).toArray();
            board[i] = loc;
        }

        for (int i = 0; i < col; i++) {
            // beforeDir을 겹치지 않게 해준다.
            goToMoon(board, -1, i, 0, 0, "");
        }
        System.out.println(Arrays.deepToString(board));
        System.out.println(answer);

    }

    public void dp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int row = input[0];
        int col = input[1];

        int[] dp = new int[col];
        for (int i = 0; i < row; i++) {
            int[] loc = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(d -> Integer.parseInt(d)).toArray();

            for (int curr = 0; curr < loc.length; curr++) {
                int forCheck = loc[curr] + dp[curr];
                for (int check = -1; check < 2; check++) {
                    int dpLoc = check + curr;
                    if (dpLoc < 0 || dpLoc >= dp.length) {
                        continue;
                    }

                    // 위를 비교해준다.
                    forCheck = Math.min(dp[dpLoc] + loc[curr], forCheck);

                }
//                System.out.println();
                loc[curr] = forCheck;
            }

            dp = loc;
            System.out.println();

        }

        System.out.println(Arrays.toString(dp));
    }


    @Override
    public void init() throws IOException {
        solution();
    }
}
