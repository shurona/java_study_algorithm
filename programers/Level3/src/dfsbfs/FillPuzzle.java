package dfsbfs;

import utils.AlgoLevel3Study;

import java.util.*;

public class FillPuzzle implements AlgoLevel3Study {

    int[] dRow = {-1, 1, 0, 0};
    int[] dCol = {0, 0, -1, 1};

    List<List<Point>> gameBlankList = new ArrayList<>();
    List<List<Point>> tablePiceList = new ArrayList<>();

    private static class Point implements Comparable<Point> {
        int col;
        int row;

        public Point(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public int compareTo(Point pt) {
            if (this.col == pt.col) {
                return this.row - pt.row;
            }
            return this.col - pt.col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "col=" + col +
                    ", row=" + row +
                    '}';
        }
    }

    private void bfs(int col, int row, int on, int[][] board, boolean[][] visited, List<List<Point>> list) {
        visited[row][col] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(col, row));

        List<Point> tp = new ArrayList<>();
        tp.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point pt = queue.poll();

            for (int i = 0; i < 4; i++) {
                int currentCol = pt.col + this.dCol[i];
                int currentRow = pt.row + this.dRow[i];

                if(currentRow < 0 || currentCol < 0 || currentCol >= board[0].length || currentRow >= board.length)
                    continue;

                if (!visited[currentRow][currentCol] && board[currentRow][currentCol] == on) {
                    visited[currentRow][currentCol] = true;
                    queue.add(new Point(currentCol, currentRow));
                    tp.add(new Point(currentCol - col, currentRow - row));
                }
            }
        }
        list.add(tp);
    }

    private boolean rotatePiece(List<Point> gameBoard, List<Point> table, int checkIndex) {
        Collections.sort(gameBoard);
        Collections.sort(table);

        //
//        if (checkIndex == 2) {
//            System.out.println(gameBoard);
//        }
        int gameBoardFirstRow = gameBoard.get(0).row;
        int gameBoardFirstCol = gameBoard.get(0).col;
        for (int init = 0; init < gameBoard.size(); init++) {
            gameBoard.get(init).row -= gameBoardFirstRow;
            gameBoard.get(init).col -= gameBoardFirstCol;

        }

//        if (checkIndex == 2) {
//            System.out.println(gameBoard);
//            System.out.println();
//        }

        // 0 ~ 270도 전환
        for (int i = 0; i < 4; i++) {
            // 첫 번째를 갖고 온다.
            int firstRow = table.get(0).row;
            int firstCol = table.get(0).col;

            // 첫번째에 맞춰서 0점 맞춰준다.
            for (int j = 0; j < table.size(); j++) {
                table.get(j).row -= firstRow;
                table.get(j).col -= firstCol;
            }
//
//            if (checkIndex == 2) {
//                System.out.println(table);
//            }

            boolean isFit = true;
            for (int index = 0; index < gameBoard.size(); index++) {
                Point tableLoc = table.get(index);
                Point boardLoc = gameBoard.get(index);

                if (tableLoc.col != boardLoc.col || tableLoc.row != boardLoc.row) {
                    isFit = false;
                    break;
                }
            }
            // 맞았는지 확인
            if (isFit) {
                return true;
            }

            for (Point point : table) {
                int temp = point.col;
                point.col = point.row;
                point.row = (-1) * (temp);
            }
            Collections.sort(table);
        }
        return false;
    }


    public int solution(int[][] game_board, int[][] table) {
        boolean[][] boardVisit = new boolean[game_board.length][game_board[0].length];
        boolean[][] tableVisit = new boolean[table.length][table[0].length];

        for (int row = 0; row < game_board.length; row++) {
            for (int col = 0; col < game_board[0].length; col++) {
                if (game_board[row][col] == 0 && !boardVisit[row][col]) {
                    bfs(col, row, 0, game_board, boardVisit, this.gameBlankList);
                }

                if (table[row][col] == 1 && !tableVisit[row][col]) {
                    bfs(col, row, 1, table, tableVisit, this.tablePiceList);
                }
            }
        }

        for (List<Point> points : this.gameBlankList) {
//            System.out.println(points);
        }
        // 채워진 곳인지 확인
        boolean[] isFill = new boolean[this.gameBlankList.size()];
        int answer = 0;
        for (int i = 0; i < this.gameBlankList.size(); i++) {
            List<Point> blank = this.gameBlankList.get(i);
            for (int j = 0; j < this.tablePiceList.size(); j++) {
                List<Point> piece = this.tablePiceList.get(j);
                if (isFill[j]) {
                    continue;
                }
                if (blank.size() == piece.size()) {
                    if (rotatePiece(blank, piece, j)) {
//                        System.out.println(piece);
                        answer += piece.size();
                        isFill[j] = true;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    @Override
    public void init() {
        int[][] game_board = {
                {1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0}
        };

        int[][] table = {
                {1, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0}
        };

        int solution = solution(game_board, table);
        System.out.println(solution);
    }
}
