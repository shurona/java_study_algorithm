package search;

import utils.AlgoStudy;

import java.util.Arrays;

public class RicochetRobot implements AlgoStudy {

    int answer = Integer.MAX_VALUE;
    int[][] visitedCount;
    // 서, 북, 남, 동
    int[] dx = {0, -1, 1, 0};
    int[] dy = {-1, 0, 0, 1};

    public boolean canMoveFunc(String[] board, int row, int col) {

        boolean canMove = true;
        int rowMax = board.length;
        int colMax = board[0].length();

        if(row == rowMax) canMove = false;
        if(row < 0) canMove = false;
        if(col == colMax) canMove = false;
        if(col < 0) canMove = false;

        if (canMove && board[row].charAt(col) == 'D') {
            canMove = false;
        }

        return canMove;
    }

    public void dfs(String[] board, boolean[][] visited, int row, int col, int count) {

        if (board[row].charAt(col) == 'G') {
            this.answer = Math.min(this.answer, count);
        }

        // 상하좌우
        for (int i = 0; i < this.dx.length; i++) {
            int nx = row;
            int ny = col;
            // 계속 이동을 하자
            boolean canMove = canMoveFunc(board, nx + dx[i], ny + dy[i]);
            while (canMove) {
                nx = nx + dx[i];
                ny = ny + dy[i];
                canMove = canMoveFunc(board, nx + dx[i], ny + dy[i]);
            }
            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                if (count + 1 < this.visitedCount[nx][ny]) {
                    this.visitedCount[nx][ny] = Math.min(this.visitedCount[nx][ny], count + 1);
                    dfs(board, visited, nx, ny, count + 1);
                }
                visited[nx][ny] = false;
            }
        }
        visited[row][col] = true;
    }

    public int solution(String[] board) {

        boolean[][] visited = new boolean[board.length][board[0].length()];
        this.visitedCount = new int[board.length][board[0].length()];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length(); col++) {
                this.visitedCount[row][col] = Integer.MAX_VALUE;
            }
        }

        int rowG = 0;
        int colG = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length(); col++) {
                if (board[row].charAt(col) == 'R') {
                    this.visitedCount[row][col] = 0;
                    dfs(board, visited, row, col, 0);
                }
                if (board[row].charAt(col) == 'G') {
                    rowG = row;
                    colG = col;
                }
            }
        }
//        for (int[] ints : this.visitedCount) {
//            System.out.println(Arrays.toString(ints));
//        }

        return this.visitedCount[rowG][colG] == Integer.MAX_VALUE ? -1 : this.visitedCount[rowG][colG];
    }

    @Override
    public void init() {
        String[] input = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
//        String[] input = {".D.R", "....", ".G..", "...D"};
//        String[] input = {"...D..R", ".D.G...", "....D..", "D....D.", "..D...."};
        for (String s : input) {
            System.out.println(s);
        }
        System.out.println(solution(input));
    }
}
