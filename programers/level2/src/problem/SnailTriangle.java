package problem;

import java.util.Arrays;
import utils.AlgoStudy;

public class SnailTriangle implements AlgoStudy {
    public int[] solution(int n) {
        int[][] board = new int[n][n];

        if (n == 1) {
            return new int[]{1};
        }

        int memo = 1;
        int row = 0;
        int col = 0;
        int status = 0;
        while (board[row][col] == 0) {
            switch (status) {
                // 왼쪽 아래
                case 0:
                    for (int i = row; i < n; i++) {
                        if (board[row][col] != 0) {
                            row -=1;
                            break;
                        }
                        board[row][col] = memo;

                        if (row < n - 1) {
                            row +=1;
                        }
                        memo +=1;
                    }
                    col += 1;
                    status = 1;
                    break;

                // 오른쪽 으로
                case 1:
                    for (int i = col; i < n; i++) {
                        if (board[row][col] != 0) {
                            col -=1;
                            break;
                        }
                        board[row][col] = memo;
                        if (col < n - 1) {
                            col +=1;
                        }
                        memo += 1;
                    }
                    row -=1;
                    col -= 1;
                    status = 2;
                    break;

                // 왼쪽 위로
                case 2:
                    for (int i = 0; i < n; i++) {
                        if (board[row][col] != 0) {
                            row +=1;
                            col +=1;
                            break;
                        }
                        board[row][col] = memo;
                        row -=1;
                        col -=1;
                        memo +=1;
                    }
                    row += 1;
//                    System.out.println(row + "  " + col);
                    status = 0;
                    break;
            }
            if (memo == 21) {
                System.out.println(row + "  " + col + "  " + status);
            }

//            System.out.println("row : " + row + " col : " + col);
//            break;
        }

        System.out.println(Arrays.deepToString(board) + " : " + memo);
        int[] answer = new int[memo - 1];
        int answerIndex = 0;
        for (int[] b : board) {
            for (int i : b) {
                if (i == 0) {
                    continue;
                }
                answer[answerIndex] = i;
                answerIndex +=1;
            }
        }
        return answer;
    }

    @Override
    public void init() {
        int[] solution = solution(1);
        System.out.println("정답은 : " + Arrays.toString(solution));
    }
}
//[1, 2, 15, 3, 16, 14, 4, 17, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11]
//[1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11]

