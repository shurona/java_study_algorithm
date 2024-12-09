package problem;

import utils.AlgoLevel3Study;

/*
 * 누적합 알고리즘을 사용해서 풀이
 * https://school.programmers.co.kr/learn/courses/30/lessons/92344
 */
public class NotDestroyBuilding implements AlgoLevel3Study {

    int[][] dp;

    public void rowDirSum() {
        for (int row = 0; row < dp.length; row++) {
            int sum = dp[row][0];
            for (int col = 1; col < dp[row].length; col++) {
                sum += dp[row][col];
                dp[row][col] = sum;
            }
        }
    }

    public void colDirSum() {
        for (int col = 0; col < dp[0].length; col++) {
            int sum = dp[0][col];
            for (int row = 1; row < dp.length; row++) {
                sum += dp[row][col];
                dp[row][col] = sum;
            }
        }
    }

    public int solution(int[][] board, int[][] skill) {
        // 누적합 배열 초기화
        dp = new int[board.length + 1][board[0].length + 1];

        for (int[] ski : skill) {
            int gizun = ski[0];

            int damage = gizun == 1 ? (ski[5] * -1) : ski[5];

            int row = ski[1];
            int col = ski[2];
            int endRow = ski[3] + 1;
            int endCol = ski[4] + 1;

            dp[row][col] += damage;
            dp[endRow][endCol] += damage;
            dp[row][endCol] += (damage * (-1));
            dp[endRow][col] += (damage * (-1));

        }

        rowDirSum();
        colDirSum();

        int answer = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] + dp[row][col] > 0) {
                    answer += 1;
                }
            }
        }

        return answer;
    }

    @Override
    public void init() {

    }
}
