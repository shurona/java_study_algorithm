package search;

import utils.AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FindShortestMapInGame implements AlgoStudy {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    int[][] cost;
    boolean[][] visit;

    public void findPath(int[][] maps, int row, int col, int count) {
        // 현재 위치가 끝인지 확인
        if (row == maps.length - 1 && col == maps[0].length - 1) {
            return;
        }

        Queue<ArrayList<Integer>> bfs = new LinkedList<>();

        bfs.add(new ArrayList<>(Arrays.asList(row, col, count)));

        while (!bfs.isEmpty()) {
            ArrayList<Integer> current = bfs.poll();
            int currentCount = current.get(2);
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = current.get(0) + this.dx[dir];
                int nextCol = current.get(1) + this.dy[dir];

                // 범위 넘어가면 안감
                if (nextRow < 0 || nextCol < 0 || nextRow == maps.length || nextCol == maps[0].length) {
                    continue;
                }

                if (!visit[nextRow][nextCol] && maps[nextRow][nextCol] == 1 && currentCount <= cost[nextRow][nextCol]) {
                    System.out.println(nextRow + " : " + nextCol);
                    // 현재 코스트 변경
                    cost[nextRow][nextCol] = currentCount + 1;
                    visit[nextRow][nextCol] = true;
                    bfs.add(new ArrayList<>(Arrays.asList(nextRow, nextCol, currentCount + 1)));

                }
            }
        }
    }

    public int solution(int[][] maps) {

        this.visit = new boolean[maps.length][maps[0].length];
        this.cost = new int[maps.length][maps[0].length];

        for (int[] ct : this.cost) {
            Arrays.fill(ct, Integer.MAX_VALUE);
        }
        this.visit[0][0] = true;
        findPath(maps, 0, 0, 1);

        for (int[] ints : this.cost) {
            System.out.println(Arrays.toString(ints));
        }

        return this.cost[maps.length - 1][maps[0].length - 1] == Integer.MAX_VALUE ? -1 : this.cost[maps.length - 1][maps[0].length - 1];

    }

    @Override
    public void init() {

//        [1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};

//        [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]
//        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
//        int[][] maps = {{1, 0}, {0, 1}};

        System.out.println(solution(maps));

    }
}
