package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/4485
 */
public class FindZelda implements BaekAlgoStudy {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int bfs(int[][] table, int[][] copy) {

        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> {
            return o1.step - o2.step;
        });
        queue.add(new Node(0, 0, 0, table[0][0]));
        copy[0][0] = table[0][0];

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int currentRow = now.row + dx[i];
                int currentCol = now.col + dy[i];

                if (currentRow < 0 || currentCol < 0 || currentCol >= table.length
                    || currentRow >= table.length) {
                    continue;
                }

                int nextValue = now.sum + table[currentRow][currentCol];
//                System.out.println(nextValue);
                if (nextValue < copy[currentRow][currentCol]) {
                    copy[currentRow][currentCol] = nextValue;
                    queue.add(new Node(currentRow, currentCol, now.step + 1, nextValue));
                }
            }


        }
        return copy[copy.length - 1][copy.length - 1];
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct;
        int index = 1;
        StringBuilder sb = new StringBuilder();
        do {
            ct = Integer.parseInt(reader.readLine());
            if (ct == 0) {
                break;
            }

            int[][] store = new int[ct][ct];

            for (int i = 0; i < ct; i++) {
                store[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            }

            int[][] copy = new int[ct][ct];
            for (int i = 0; i < copy.length; i++) {
                Arrays.fill(copy[i], 987654321);
            }

            sb.append("Problem ").append(index).append(": ").append(bfs(store, copy)).append("\n");
            index += 1;


        } while (ct > 0);

        System.out.println(sb);

    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int row;
        int col;
        int step;
        int sum;

        public Node(int row, int col, int step, int sum) {
            this.row = row;
            this.col = col;
            this.step = step;
            this.sum = sum;
        }
    }
}
