package dfsbfs;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ColoringBooks implements AlgoStudy {
    static class NodeLoc {
        int row;
        int col;
        int value;

        public NodeLoc(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    int[] dRow = {1, -1, 0, 0};
    int[] dCol = {0, 0, 1, -1};

    public int bfs(int[][] picture, boolean[][] visit, int row, int col, int value) {

        int size = 0;
        Queue<NodeLoc> queue = new LinkedList<>();
        queue.add(new NodeLoc(row, col, value));

        while (!queue.isEmpty()) {
            NodeLoc now = queue.poll();
            int currRow = now.row;
            int currCol = now.col;
            if (visit[currRow][currCol]) {
                continue;
            }
            size +=1;
            visit[currRow][currCol] = true;
            for (int i = 0; i < 4; i++) {
                int afterRow = dRow[i] + currRow;
                int afterCol = dCol[i] + currCol;

                // 칸 벗어나면 밴
                if (afterCol < 0 || afterRow < 0 || afterRow >= visit.length || afterCol >= visit[0].length) {
                    continue;
                }

                if (!visit[afterRow][afterCol] && value == picture[afterRow][afterCol]) {
                    queue.add(new NodeLoc(afterRow, afterCol, value));
                }
            }
        }
//        System.out.println("시작 : " + row + " : " + col);
//        System.out.println("사이즈 : " + size);
//        System.out.println();
        return size;
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visit = new boolean[picture.length][picture[0].length];


        for (int row = 0; row < picture.length; row++) {
            for (int col = 0; col < picture[0].length; col++) {
                int value = picture[row][col];
                if (value != 0 && !visit[row][col]) {
                    int areaSize = bfs(picture, visit, row, col, value);
                    maxSizeOfOneArea = Math.max(areaSize, maxSizeOfOneArea);
                    numberOfArea += 1;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    @Override
    public void init() {

        int m = 6;
        int n = 4;
//
//        int[][] picture = {
//                {1, 1, 1, 0},
//                {1, 2, 2, 0},
//                {1, 0, 0, 1},
//                {0, 0, 0, 1},
//                {0, 0, 0, 3},
//                {0, 0, 0, 3}
//        };

        int[][] picture = {
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1}
        };

        int[] solution = solution(m, n, picture);

        System.out.println(Arrays.toString(solution));

    }
}
