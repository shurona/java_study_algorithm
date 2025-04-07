package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/15683

    문제 풀이 과정
    완전한 구현 문제 => row와 col의 크기가 8이라서 모든 경우의 수를 찾는 것을 기본으로 깔고 진행하였다.
    cctv가 확인할 수 있는 경우의 수에 맞춰서 backtracking을 통해서 모든 가짓 수를 조사하였다.

 */
public class CCTVCheck implements BaekAlgoStudy {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] threeRow = {2, 3, 1, 0};
    static int[] threeCol = {2, 3, 1, 0};
    static int[][] board;
    static int tp = 0;
    static int answer = 987654321;

    static void mark(int row, int col, int dirRow, int dirCol, int mark, boolean isErase) {
        while (true) {
            row = row + dx[dirRow];
            col = col + dy[dirCol];

            if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
                break;
            }

            if (board[row][col] == 6) {
                break;
            }

            if (isErase) {
                if (board[row][col] == mark) {
                    board[row][col] = 0;
                }

            } else {
                if (board[row][col] == 0) {
                    board[row][col] = mark;
                }
            }

        }
    }

    static void dfs(List<CCTV> cctvs, int start, int step) {
        if (step == cctvs.size()) {
            int tpAnswer = 0;
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == 0) {
                        tpAnswer += 1;
                    }
                }
            }
            answer = Math.min(answer, tpAnswer);
//
//            if (true) {
//                System.out.println(cctvs.size());
//                for (int[] ints : board) {
//                    System.out.println(Arrays.toString(ints));
//                }
//                System.out.println();
//            }
            tp += 1;
            return;
        }

        for (int index = start; index < cctvs.size(); index++) {
            CCTV cctv = cctvs.get(index);
            for (int i = 0; i < 4; i++) {
                // cctv의 type에 맞춰서 진격
                switch (cctv.type) {
                    case 1:
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), false);
                        dfs(cctvs, index + 1, step + 1);
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), true);
                        break;
                    case 2:
                        if (i == 1 || i == 3) {
                            continue;
                        }
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), false);
                        mark(cctv.row, cctv.col, i + 1, i + 1, -1 * (index + 1), false);
                        dfs(cctvs, index + 1, step + 1);
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), true);
                        mark(cctv.row, cctv.col, i + 1, i + 1, -1 * (index + 1), true);
                        break;
                    case 3:
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), false);
                        mark(cctv.row, cctv.col, threeRow[i], threeCol[i], -1 * (index + 1), false);
                        dfs(cctvs, index + 1, step + 1);
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), true);
                        mark(cctv.row, cctv.col, threeRow[i], threeCol[i], -1 * (index + 1), true);
                        break;
                    case 4:
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), false);
                        mark(cctv.row, cctv.col, (i + 1) % 4, (i + 1) % 4, -1 * (index + 1), false);
                        mark(cctv.row, cctv.col, (i + 2) % 4, (i + 2) % 4, -1 * (index + 1), false);
                        dfs(cctvs, index + 1, step + 1);
                        mark(cctv.row, cctv.col, i, i, -1 * (index + 1), true);
                        mark(cctv.row, cctv.col, (i + 1) % 4, (i + 1) % 4, -1 * (index + 1), true);
                        mark(cctv.row, cctv.col, (i + 2) % 4, (i + 2) % 4, -1 * (index + 1), true);
                        break;
                    case 5:
                        if (i == 0) {
                            mark(cctv.row, cctv.col, i, i, -1 * (index + 1), false);
                            mark(cctv.row, cctv.col, i + 1, i + 1, -1 * (index + 1), false);
                            mark(cctv.row, cctv.col, i + 2, i + 2, -1 * (index + 1), false);
                            mark(cctv.row, cctv.col, i + 3, i + 3, -1 * (index + 1), false);
                            dfs(cctvs, index + 1, step + 1);
                            mark(cctv.row, cctv.col, i, i, -1 * (index + 1), true);
                            mark(cctv.row, cctv.col, i + 1, i + 1, -1 * (index + 1), true);
                            mark(cctv.row, cctv.col, i + 2, i + 2, -1 * (index + 1), true);
                            mark(cctv.row, cctv.col, i + 3, i + 3, -1 * (index + 1), true);
                        }
                        break;
                    default:
                        return;
                }

            }
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int row = input[0];
        int col = input[1];

        board = new int[row][col];

        for (int i = 0; i < row; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();
        }

        List<CCTV> cctvs = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] > 0 && board[i][j] < 6) {
                    cctvs.add(new CCTV(i, j, board[i][j]));
                }
            }
        }

        dfs(cctvs, 0, 0);

//        for (int[] ints : board) {
//            System.out.println(Arrays.toString(ints));
//        }

        System.out.println(answer);
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class CCTV {

        int row;
        int col;
        int type;

        public CCTV(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}
