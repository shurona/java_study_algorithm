package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/14940
 */
public class EasyShortRoute implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        int rowSize = Integer.parseInt(input[0]);
        int colSize = Integer.parseInt(input[1]);

        int startRow = -1;
        int startCol = -1;

        int[][] output = new int[rowSize][colSize];
        for (int[] tp : output) {
            Arrays.fill(tp, -1);
        }

        int[][] board = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            String[] tp = reader.readLine().split(" ");
            for (int j = 0; j < tp.length; j++) {
                board[i][j] = Integer.parseInt(tp[j]);
                if (Integer.parseInt(tp[j]) == 2) {
                    startRow = i;
                    startCol = j;
                } else if (Integer.parseInt(tp[j]) == 0) {
                    output[i][j] = 0;
                }
            }


        }

        output[startRow][startCol] = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startRow, startCol, 0));

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
//            System.out.println(queue + " " + Arrays.deepToString(output));
            Node currentNode = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int currentRow = currentNode.row + dx[dir];
                int currentCol = currentNode.col + dy[dir];

                // 범위 확인
                if (currentRow < 0 || currentCol < 0 || currentRow >= rowSize
                    || currentCol >= colSize) {
                    continue;
                }

                if (board[currentRow][currentCol] == 0) {
                    continue;
                }

                // 방문한 적이 없거나 현재 자신이 최소 인경우
                if (output[currentRow][currentCol] == -1
                    || output[currentRow][currentCol] > currentNode.value + 1) {
                    output[currentRow][currentCol] = currentNode.value + 1;
                    queue.add(new Node(currentRow, currentCol, currentNode.value + 1));
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] print : output) {
            for (int i : print) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int row;
        int col;
        int value;

        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", value=" + value +
                '}';
        }
    }
}
