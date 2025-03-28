package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2206

    풀이 과정
    1차 접근 : Node 클래스를 만든 다음 벽을 부순적이 있는 지 없는 지 확인한 다음에
             부신적이 없으면 1을 통과 후 다음에는 통과 못하도록 하면서 bfs를 접근
             하지만 아래의 반례에서 통과하지 못하는 문제 발생

             6 5
            00000
            11110
            00000
            01111
            01111
            00010

    2차 접근 : 벽을 부순 이후 visit과 부수기 전 visit을 나눠서 접근한다.
 */
public class MoveThroughWall implements BaekAlgoStudy {

    public void solution() throws IOException {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        int rowSize = Integer.parseInt(input[0]);
        int colSize = Integer.parseInt(input[1]);

        String[][] maze = new String[rowSize][colSize];
        boolean[][] visit = new boolean[rowSize][colSize];
        boolean[][] breakWallVisit = new boolean[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            maze[i] = reader.readLine().split("");
        }

//        for (String[] strings : maze) {
//            System.out.println(Arrays.toString(strings));
//        }

        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.step - o2.step);
        queue.add(new Node(0, 0, false, 1));
        visit[0][0] = true;

        int answer = 987654321;
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.row == rowSize - 1 && current.col == colSize - 1) {
                answer = current.step;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                // 범위
                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (current.isBreak && maze[nextRow][nextCol].equals("1")) {
                    continue;
                }

                if (current.isBreak) {
                    if (breakWallVisit[nextRow][nextCol]) {
                        continue;
                    }
                    breakWallVisit[nextRow][nextCol] = true;
                } else {
                    if (visit[nextRow][nextCol]) {
                        continue;
                    }
                    breakWallVisit[nextRow][nextCol] = true;
                    visit[nextRow][nextCol] = true;
                }

                if (maze[nextRow][nextCol].equals("1")) {
                    queue.add(new Node(nextRow, nextCol, true, current.step + 1));
                } else {
                    queue.add(new Node(nextRow, nextCol, current.isBreak, current.step + 1));
                }
            }

        }

        if (answer == 987654321) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int row;
        int col;
        boolean isBreak;
        int step;

        public Node(int row, int col, boolean isBreak, int step) {
            this.row = row;
            this.col = col;
            this.isBreak = isBreak;
            this.step = step;
        }
    }
}
