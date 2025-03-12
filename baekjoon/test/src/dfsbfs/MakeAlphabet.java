package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1987
 */
public class MakeAlphabet implements BaekAlgoStudy {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static HashSet<String> set = new HashSet<>();
    static boolean[][] visited;

    static int tp = 0;

    public static void dfs(String[][] store, int row, int col, int step) {

        tp = Math.max(tp, step);

//        System.out.println(step + " : " + store[row][col] + " : " + set);

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (nextRow < 0 || nextCol < 0
                || nextRow >= store.length || nextCol >= store[0].length) {
                continue;
            }

            // 방문한 노드면 가지 않음
            if (visited[nextRow][nextCol]) {
                continue;
            }

            // 이미 들어있으면 안감
            if (set.contains(store[nextRow][nextCol])) {
                continue;
            }

            visited[nextRow][nextCol] = true;
            set.add(store[nextRow][nextCol]);
            // stack에서 진입 부분
            dfs(store, nextRow, nextCol, step + 1);
            visited[nextRow][nextCol] = false;
            set.remove(store[nextRow][nextCol]);
        }
    }

    /*
        스택으로 풀어보려고 하였으나 시간 초과 => 앞으로 dfs(백트래킹)은 재귀로 가자
     */
    public static int stackDfs(String[][] store) {

        Stack<Node> stack = new Stack();

        int answer = 0;
        visited[0][0] = true;
//        dp.put(store[0][0], true);

        stack.add(new Node(0, 0, 1, false));
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            set.add(store[current.row][current.col]);
            visited[current.row][current.col] = true;
//            System.out.println(current);
//            System.out.println(dp);
//            System.out.println();

            answer = Math.max(answer, current.step);

            if (current.exit) {
                // 여기가 나오는 부분
                set.remove(store[current.row][current.col]);
                visited[current.row][current.col] = false;
                continue;
            }

            stack.add(new Node(current.row, current.col, current.step, true));

            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                if (nextRow < 0 || nextCol < 0
                    || nextRow >= store.length || nextCol >= store[0].length) {
                    continue;
                }

                // 방문한 노드면 가지 않음
                if (visited[nextRow][nextCol]) {
                    continue;
                }

                // 이미 들어있으면 안감
                if (set.contains(store[nextRow][nextCol])) {
                    continue;
                }

//                visited[nextRow][nextCol] = true;
//                set.add(store[nextRow][nextCol]);
                // stack에서 진입 부분
                stack.add(new Node(nextRow, nextCol, current.step + 1, false));

            }


        }

        return answer;
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int row = firstLine[0];
        int col = firstLine[1];

        String[][] store = new String[row][col];
        visited = new boolean[store.length][store[0].length];

        for (int i = 0; i < row; i++) {
            String[] input = reader.readLine().split("");
            store[i] = input;
        }

//        int ans = dfs(store);
        set.add(store[0][0]);
        dfs(store, 0, 0, 1);
        System.out.println(tp);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int row;
        int col;
        int step;
        boolean exit;

        public Node(int row, int col, int step, boolean exit) {
            this.row = row;
            this.col = col;
            this.step = step;
            this.exit = exit;
        }

    }
}
