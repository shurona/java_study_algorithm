package dfsbfs;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class EscapeMaze implements AlgoStudy {
    int[] dRow = {0, 0, -1, 1};
    int[] dCol = {1, -1, 0, 0};

    int answer = Integer.MAX_VALUE;

    public static class NodeInfo {
        int row;
        int col;
        int count;

        boolean onSwitch;

        public NodeInfo(int row, int col, int count, boolean onSwitch) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.onSwitch = onSwitch;
        }

        @Override
        public String toString() {
            return "NodeInfo{" +
                    "row=" + row +
                    ", col=" + col +
                    ", count=" + count +
                    ", onSwitch=" + onSwitch +
                    '}';
        }
    }

    public NodeInfo bfs(String[][] maps, boolean[][] visit, int row, int col, String target) {
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(row, col, 0, false));
        visit[row][col] = true;
        while (!queue.isEmpty()) {
            NodeInfo popInfo = queue.poll();
            System.out.println(popInfo.row + " :: " + popInfo.col );
            for (int i = 0; i < 4; i++) {
                int afterRow = dRow[i] + popInfo.row;
                int afterCol = dCol[i] + popInfo.col;

                // 범위 벗어났는지 확인
                if (afterRow < 0 || afterCol < 0 || afterRow >= maps.length || afterCol >= maps[0].length) {
                    continue;
                }

                if (!visit[afterRow][afterCol] && !maps[afterRow][afterCol].equals("X")) {
                    if (maps[afterRow][afterCol].equals(target)) {
                        return new NodeInfo(afterRow, afterCol, popInfo.count + 1, true);
                    } else {
                        visit[afterRow][afterCol] = true;
                        queue.add(new NodeInfo(afterRow, afterCol, popInfo.count + 1, popInfo.onSwitch));
                    }
                }
            }
        }
        return null;
    }

    public int solution(String[] maps) {
        String[][] map = new String[maps.length][maps[0].length()];
        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[0].length(); col++) {
                String s = String.valueOf(maps[row].charAt(col));

                if (s.equals("S")) {
                    startRow = row;
                    startCol = col;
                }
                map[row][col] = s;
            }
        }

        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        visit[startRow][startCol] = true;
        NodeInfo findSwitch = bfs(map, visit, startRow, startCol, "L");

        if (findSwitch == null) {
            return -1;
        }

        visit = new boolean[maps.length][maps[0].length()];
        visit[findSwitch.row][findSwitch.col] = true;
        NodeInfo findExit = bfs(map, visit, findSwitch.row, findSwitch.col, "E");

        System.out.println(findExit);

        if (findExit == null) {
            return -1;
        }

        return findSwitch.count + findExit.count;
    }


    @Override
    public void init() {
//        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        String[] maps = {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"};
        int solution = solution(maps);
        System.out.println("정답은 : " + solution);
    }

    public void dfs(String[][] maps, boolean[][] visit, int row, int col, int count, boolean onSwitch) {

//        if (onSwitch) {
//            System.out.println(row + " :: " + col);
//        }

        if (onSwitch && maps[row][col].equals("E")) {
            this.answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < 4; i++) {

            int afterRow = dRow[i] + row;
            int afterCol = dCol[i] + col;

            // 범위 벗어났는지 확인
            if (afterRow < 0 || afterCol < 0 || afterRow >= maps.length || afterCol >= maps[0].length) {
                continue;
            }

            if (!visit[afterRow][afterCol] && !maps[afterRow][afterCol].equals("X")) {
                visit[afterRow][afterCol] = true;
                if (maps[afterRow][afterCol].equals("L")) {
                    dfs(maps, visit, afterRow, afterCol, count + 1, true);
                } else {
                    dfs(maps, visit, afterRow, afterCol, count + 1, onSwitch);
                }
                visit[afterRow][afterCol] = false;
            }

        }
        return;
    }
}
