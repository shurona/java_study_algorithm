package backtracking;

import utils.AlgoStudy;

import java.util.Arrays;

public class SelectNumberInProcession implements AlgoStudy {

    boolean[] selected;
    int[] dp;

    public void backTracking(int[][] procession, int depth, int ho) {

        if (depth == procession.length) {
            System.out.println(Arrays.toString(this.dp));
            return;
        }


        for (int i = 0; i < procession.length; i++) {
            // 조건에 맞으면 넘어간다.
            if (!this.selected[i]) {
                this.selected[i] = true;
                this.dp[i] = procession[depth][i];
                backTracking(procession, depth + 1, ho);
                this.selected[i] = false;
            }
        }
    }

    public void solution(int[][] procession) {

        this.selected = new boolean[procession.length];
        this.dp = new int[procession.length];
        backTracking(procession, 0, 0);

    }

    @Override
    public void init() {
        int [][] procession = new int[][]{{2, 4, 3}, {1, 3, 7}, {6, 5, 6}}; //3X3 행렬
        solution(procession);
        System.out.println("SelectProcession.init");
    }
}
