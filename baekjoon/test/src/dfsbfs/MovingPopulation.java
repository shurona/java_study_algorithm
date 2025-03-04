package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/16234
 */
public class MovingPopulation implements BaekAlgoStudy {

    static int left;
    static int right;

    static boolean isOpened;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] store;
    static int sum;

    static boolean refinePopulation(List<Node> data, int sum, int[][] afterPopulation) {
        int afterMove = sum / data.size();
//        System.out.println(afterMove);
        for (int i = 0; i < data.size(); i++) {
            afterPopulation[data.get(i).row][data.get(i).col] = afterMove;
        }

        return sum != afterMove;
    }

    static void dfs(int row, int col, boolean[][] visited, List<Node> data) {

        int gizun = store[row][col];

        for (int i = 0; i < 4; i++) {
            int currentRow = row + dx[i];
            int currentCol = col + dy[i];

            if (currentRow < 0 || currentCol < 0
                || currentRow >= store.length || currentCol >= store[0].length) {
                continue;
            }

            int loc = store[currentRow][currentCol];
            int diff = Math.abs(gizun - loc);

            if (diff >= left && diff <= right) {
                if (!visited[currentRow][currentCol]) {
                    visited[currentRow][currentCol] = true;
                    data.add(new Node(currentRow, currentCol));
                    sum += loc;
                    dfs(currentRow, currentCol, visited, data);
                }

            }


        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int lineCt = firstLine[0];
        left = firstLine[1];
        right = firstLine[2];
        store = new int[lineCt][lineCt];

        for (int i = 0; i < lineCt; i++) {
            int[] input = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

            store[i] = input;

        }

        isOpened = true;

        int answer = -1;
        while (isOpened) {
            isOpened = false;
            // 초기화
            boolean[][] visited = new boolean[lineCt][lineCt];
            int[][] afterMoving = new int[lineCt][lineCt];

            // 순회한다.
            for (int row = 0; row < store.length; row++) {
                for (int col = 0; col < store[0].length; col++) {
                    if (!visited[row][col]) {
                        List<Node> tp = new ArrayList<>();
                        tp.add(new Node(row, col));
                        sum = store[row][col];
                        visited[row][col] = true;
                        dfs(row, col, visited, tp);
                        isOpened = refinePopulation(tp, sum, afterMoving) || isOpened;
//                        System.out.println(sum + " : " + tp);
//                        for (int[] ints : afterMoving) {
//                            System.out.println(Arrays.toString(ints));
//                        }
                    }

                }
            }

            for (int row = 0; row < store.length; row++) {
                for (int col = 0; col < store[0].length; col++) {
//                    if (afterMoving[row][col] == 0) {
//                        afterMoving[row][col] = store[row][col];
//                    }
                }
            }

            answer += 1;
            store = afterMoving;
//            for (int[] ints : store) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println();
        }

//        System.out.println(sum);
//        for (int[] ints : store) {
//            System.out.println(Arrays.toString(ints));
//        }
        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
