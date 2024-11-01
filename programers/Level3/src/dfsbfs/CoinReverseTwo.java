package dfsbfs;

import java.util.Arrays;
import utils.AlgoLevel3Study;

public class CoinReverseTwo implements AlgoLevel3Study {

    int row;
    int col;
    int answer = 11;
    private int[][] beginning;
    private int[][] target;

    private void reverseRow(int r) {
        for (int c = 0; c < col; c++) {
            this.beginning[r][c] = (this.beginning[r][c] + 1) % 2;
        }

    }

    private int compareColumn(int c) {
        int tp = 0;
        for (int i = 0; i < row; i++) {
            if (this.beginning[i][c] == target[i][c]) {
                tp++;
            }
        }

        // 모두 다른 경우
        if (tp == 0) {
            return 1;
            // 모두 같은 경우
        } else if (tp == row) {
            return 0;
        }

        return -1;

    }

    private void dfs(int r, int count) {
        if (r == row) {
            int flag = 1;

            for (int c = 0; c < col; c++) {
                int ct = compareColumn(c);
                if (ct == -1) {
                    flag = 0;
                    break;
                }
                count += ct;
            }
            if (flag > 0) {
                answer = Math.min(answer, count);
            }
            return;
        } else {
            dfs(r + 1, count);
            reverseRow(r);
            dfs(r + 1, count + 1);
            reverseRow(r);

        }

        return;

    }

    public int solution(int[][] beginning, int[][] target) {

        this.beginning = beginning;
        this.target = target;

        row = beginning.length;
        col = beginning[0].length;

        dfs(0, 0);

        if (answer == 11) {
            return -1;
        }

        return answer;
    }

    @Override
    public void init() {
        int[][] array1 = {
            {0, 1, 0, 0, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 1, 1, 0},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 1, 0}
        };

        int[][] array2 = {
            {0, 0, 0, 1, 1},
            {0, 0, 0, 0, 1},
            {0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1}
        };
        int solution = solution(array1, array2);
        System.out.println("정답은 : " + solution);
    }

    private static class Node {

        int[][] beginning;
        boolean[] row;
        boolean[] col;
        int count;

        public Node(
            int[][] beginning, boolean[] row, boolean[] col, int count) {

            this.beginning = new int[beginning.length][];

            for (int i = 0; i < beginning.length; i++) {
                this.beginning[i] = beginning[i].clone();
            }

            this.row = row.clone();
            this.col = col.clone();
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                "beginning=" + Arrays.deepToString(beginning) +
                ", row=" + Arrays.toString(row) +
                ", col=" + Arrays.toString(col) +
                ", count=" + count +
                '}' + "\n";
        }
    }
}
