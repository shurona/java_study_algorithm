package backtracking;

import utils.AlgoStudy;

import java.util.Arrays;

/**
 * DFS로 접근 했으나 시간 초과로 백트랙킹을 학습
 */
public class NQueen implements AlgoStudy {

    int[] dRow = {0, 0, 1, -1, 1, 1, -1, -1};
    int[] dCol = {1, -1, 0, 0, 1, -1, -1, 1};
    int answer = 0;

    // 일차원 배열의 row 값은 퀸이 놓여져 있는 열의 값을 의미한다.
    // 직선의 경우 배열의 값을 비교하면 되고 대각선은 기울기를 비교하면 된다.
    int[] dp;

    public boolean checkValid(int loc) {
        boolean isContinue = true;
        for (int i = 0; i < loc; i++) {
            if (this.dp[loc] == this.dp[i]) isContinue = false;
            // 기울기 비교인데
            if (Math.abs(loc - i) == Math.abs(this.dp[loc] - this.dp[i])) isContinue = false;
        }
        return isContinue;
    }

    public void dfs(int depth, int n) {
        // 들어간 깊이가 주어진 n과 같아지만 멈춘다.
        if (depth == n) {
            answer += 1;
            System.out.println(Arrays.toString(this.dp));
            return;
        }

        for (int i = 0; i < n; i++) {
            this.dp[depth] = i;
            if (checkValid(depth)) {
                dfs(depth + 1, n);
            }
        }
    }

    public int solution(int n) {
        this.dp = new int[n];
        // 백트랙킹을 시작한다.
        dfs(0, n);
        return answer;
    }

    @Override
    public void init() {
        int solution = solution(4);
        System.out.println("답 : " + solution);
    }
}
