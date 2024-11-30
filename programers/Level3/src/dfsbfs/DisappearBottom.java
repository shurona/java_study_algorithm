package dfsbfs;

import java.util.Arrays;
import utils.AlgoLevel3Study;

/*
    문제링크
    https://school.programmers.co.kr/learn/courses/30/lessons/92345
 */
public class DisappearBottom implements AlgoLevel3Study {

    int aWin = 0;
    int aWinMin = 1000000;
    int bWin = 0;

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    int answer = 0;

    public int dfs(int[][] board, int row, int col, int tRow, int tCol, int depth) {
        int result = 0;

        // 현재 발판 확인
        if (board[row][col] == 0) {
            return 0;
        }

        for (int i = 0; i < 4; i++) {
            int afterRow = row + dx[i];
            int afterCol = col + dy[i];

            // 범위를 벗어나면 패스.
            if (afterCol < 0 || afterRow < 0
                || afterRow >= board.length || afterCol >= board[0].length) {
                continue;
            }

            // 이미 방문한 장소면 패스
            if (board[afterRow][afterCol] == 0) {
                continue;
            }

            // 이전에 있던 곳은 방문 처리
            board[row][col] = 0;
            int dfs = dfs(board, tRow, tCol, afterRow, afterCol, depth + 1) + 1;
            board[row][col] = 1;

            // 다 졌었는데 이 번에 이긴 경우
            if (dfs % 2 == 1 && result % 2 == 0) {
                result = dfs;
            } // 다 지는 경우
            else if (dfs % 2 == 0 && result % 2 == 0) {
                result = Math.max(dfs, result);
            } // 이긴 경우가 있는 경우
            else if (dfs % 2 == 1 && result % 2 == 1) {
                result = Math.min(dfs, result);
            }

        }

        return result;
    }


    public int solution(int[][] board, int[] aloc, int[] bloc) {
        // A가 먼저 시작했으므로 A가 이기는 경우가 있으면 무조건 A가 이겨야 한다.
        int dfs = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], 1);

        return dfs;
    }


    @Override
    public void init() {
//        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] board = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[] aloc = new int[]{1, 0};
        int[] bloc = new int[]{1, 2};

//        int[][] board = {{1, 1}};
        int solution = solution(board, aloc, bloc);
        System.out.println("정답은 : " + solution);
    }

    // 위치의 비교
    public void wrongAccessDfs(int[][] board, int[] aloc, int[] bloc, int moving, String step) {
        for (int i = 0; i < 4; i++) {
            // a의 위치를 변경
            int aRow = aloc[0] + dx[i];
            int aCol = aloc[1] + dy[i];

            // 밖으로 벗어났으면
            if (aRow < 0 || aCol < 0 || aRow >= board.length || aCol >= board[0].length) {
//                if (moving > bWin) {
//                    System.out.println(moving + " : " + step);
//                }
                bWin = Math.max(bWin, moving);
                continue;
            }

            // 0 이면 이동하지 않는다
            if (board[aRow][aCol] == 0) {
//                if (moving > answer) {
//                    System.out.println(moving + " : " + step);
//                }
                bWin = Math.max(bWin, moving);
                continue;
            }

            // 만약 이동 할 수 있고 이덩 전이 b와 위치가 같으면 거기서 종료한다.
            if (bloc[0] == aloc[0] && bloc[1] == aloc[1]) {
//                if (moving + 1 > answer) {
//                    System.out.println(moving + 1 + " : " + step);
//                }
                aWin = Math.max(aWin, moving + 1);
                aWinMin = Math.min(aWinMin, moving + 1);
                continue;
            }

            for (int j = 0; j < 4; j++) {
                int bRow = bloc[0] + dx[j];
                int bCol = bloc[1] + dy[j];
                //b의 위치를 변경한다.
                if (bRow < 0 || bCol < 0 || bRow >= board.length || bCol >= board[0].length) {
//                    if (moving + 1 > aWin) {
//                        System.out.println(moving + 1 + " : " + step);
//                    }
//                    aWin = Math.max(aWin, moving + 1);
//                    aWinMin = Math.min(aWinMin, moving + 1);
                    continue;
                }

                // 0 이면 이동하지 않는다
                if (board[bRow][bCol] == 0) {
                    if (moving + 1 > aWin) {
                        System.out.println(moving + 1 + " : " + step);
                    }
                    aWin = Math.max(aWin, moving + 1);
                    aWinMin = Math.min(aWinMin, moving + 1);
                    continue;
                }

                board[aRow][aCol] = 0;
                board[bRow][bCol] = 0;
                String toString =
                    "=>  aloc : " + Arrays.toString(new int[]{aRow, aCol}) + " bloc : "
                        + Arrays.toString(new int[]{bRow, bCol}) + " ";
                wrongAccessDfs(board, new int[]{aRow, aCol}, new int[]{bRow, bCol}, moving + 2,
                    step + toString);
                board[aRow][aCol] = 1;
                board[bRow][bCol] = 1;

            }

        }

    }


    public int wrongSolution(int[][] board, int[] aloc, int[] bloc) {
        board[aloc[0]][aloc[1]] = 0;
        board[bloc[0]][bloc[1]] = 0;
        String toString =
            "=>  aloc : " + Arrays.toString(aloc) + " bloc : "
                + Arrays.toString(bloc) + " ";
        wrongAccessDfs(board, aloc, bloc, 0, toString);

        System.out.println(aWin + " : " + aWinMin + " : " + bWin);

        return aWin;
    }

}
