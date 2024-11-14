package dfsbfs;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import utils.AlgoStudy;

public class MatchCard implements AlgoStudy {

//    https://school.programmers.co.kr/learn/courses/30/lessons/72415

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int[][] table;
    int next;
    int answer;
    Set<Integer> data = new HashSet<>();
    Node[][] firstLocation = new Node[7][2];
    boolean[] visited;

    public void dfs(Node current, int cost, int depth, String step) {

        // 가장 깊이 까지 온 경우
        if (depth == data.size()) {
            System.out.println(cost + " : " + step);
            answer = Math.min(cost, answer);
            return;
        }

        // 방문한지 확인을 해본다.
        for (int i : data) {
            // 방문 후
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            // 두 번의 경우의 수를 다 확인한다.
            for (int j = 0; j < 2; j++) {
                Node first = firstLocation[i][j % 2];
                Node second = firstLocation[i][(j + 1) % 2];

                int middle = findRoute(current, first, i) + findRoute(first, second, i);

                // 제거 후
                table[first.row][first.col] = 0;
                table[second.row][second.col] = 0;

                // 다음 위치
                dfs(second, cost + middle, depth + 1, step + " -> " + i);

                // 복귀
                table[first.row][first.col] = i;
                table[second.row][second.col] = i;
            }

            // 다음에 방문할 수 있도록 방문 취소
            visited[i] = false;
        }

    }

    public int findRoute(Node start, Node end, int tt) {
        boolean[][] visit = new boolean[4][4];
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.count - o2.count;
            }
        });
        queue.add(new Node(start.row, start.col, 0));
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            visit[current.row][current.col] = true;

            if (current.isSame(end)) {
                // 확인출력
//                System.out.println(current + " " + tt);
//                    answer += current.count;
                return current.count;
            }

            for (int i = 0; i < 4; i++) {
                int currentRow = current.row;
                int currentCol = current.col;

                // 계속 스텝 바이 스텝으로 나아간다.
                for (int j = 1; j < 4; j++) {
                    currentRow += dy[i];
                    currentCol += dx[i];

                    // 범위 밖은 넘긴다.
                    if (currentRow == -1 || currentRow == 4
                        || currentCol == -1 || currentCol == 4) {
                        break;
                    }

                    if (!visit[currentRow][currentCol]) {
                        queue.add(new Node(currentRow, currentCol, current.count + j));
                    }
                }

                currentRow = current.row;
                currentCol = current.col;
                // ctrl로 누른 것을 확인한다.
                for (int j = 1; j < 4; j++) {
                    currentRow += dy[i];
                    currentCol += dx[i];

                    // 범위 밖은 넘긴다.
                    if (currentRow == -1 || currentRow == 4
                        || currentCol == -1 || currentCol == 4) {
                        break;
                    }

                    // 그림이 있는지 확인한다.
                    if (table[currentRow][currentCol] != 0 && !visit[currentRow][currentCol]) {
                        queue.add(new Node(currentRow, currentCol, current.count + 1));
                        break;
                    }

                    // 끝에 도달했느지 확인한다.
                    if (((currentRow == 0 || currentRow == 3) && (i == 0 || i == 1)) // 가로
                        || ((currentCol == 0 || currentCol == 3) && (i == 2 || i == 3))) //세로 {
                    {
                        if (!visit[currentRow][currentCol]) {
                            queue.add(new Node(currentRow, currentCol, current.count + 1));
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int solution(int[][] board, int r, int c) {
        table = board;
        answer = Integer.MAX_VALUE;

        // 처음 위치와 갯수를 확인한다.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    if (firstLocation[board[i][j]][0] == null) {
                        firstLocation[board[i][j]][0] = new Node(i, j, 0);
                    } else {
                        firstLocation[board[i][j]][1] = new Node(i, j, 0);
                    }

                    data.add(board[i][j]);
                }

            }
        }

        visited = new boolean[data.size() + 2];

        dfs(new Node(r, c, 0), 0, 0, "");

        return answer + (data.size() * 2);
    }

    public Node findSame(int row, int col, boolean isFindNew) {
        boolean[][] visit = new boolean[4][4];
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.count - o2.count;
            }
        });
        queue.add(new Node(row, col, 0));
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (!isFindNew && next == 1) {
//                System.out.println(current + " : " + queue);
            }

            visit[current.row][current.col] = true;

            if (isFindNew) {
                if (table[current.row][current.col] != 0) {
                    next = table[current.row][current.col];
                    table[current.row][current.col] = 0;

                    // 확인출력
                    System.out.println(current + " " + next);
                    answer += current.count;
                    return current;
                }
            } else {
                if (table[current.row][current.col] == next) {
                    table[current.row][current.col] = 0;
//                    System.out.println(queue);
                    // 확인출력
                    System.out.println(current + " " + next);
                    answer += current.count;
                    return current;
                }
            }

            for (int i = 0; i < 4; i++) {
                int currentRow = current.row;
                int currentCol = current.col;

                // 다음 것을 찾는다.
                for (int j = 1; j < 4; j++) {
                    currentRow += dy[i];
                    currentCol += dx[i];

                    // 범위 밖은 넘긴다.
                    if (currentRow == -1 || currentRow == 4
                        || currentCol == -1 || currentCol == 4) {
                        break;
                    }

                    // 다음 진행이 되는지 확인한다.
                    if (((currentRow == 0 || currentRow == 3) && (i == 0 || i == 1))
                        || ((currentCol == 0 || currentCol == 3) && (i == 2 || i == 3))) {

                        if (!visit[currentRow][currentCol]) {
                            queue.add(new Node(currentRow, currentCol, current.count + 1));
                        }
                        continue;
                    }

                    if (table[currentRow][currentCol] != 0 && !visit[currentRow][currentCol]) {
                        queue.add(new Node(currentRow, currentCol, current.count + 1));
                    }

                    if (!visit[currentRow][currentCol]) {
                        queue.add(new Node(currentRow, currentCol, current.count + j));
                    }

                }
            }
        }

        return null;
    }

    // bfs로 접근한 잘못된 풀이방법
    public int wrongSolution(int[][] board, int r, int c) {
        table = board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    data.add(board[i][j]);
                }

            }

        }

        answer = 0;
        Node current = new Node(r, c, 0);
        next = table[r][c];
        if (next == 0) {
            current = findSame(r, c, true);
//            answer += current.count;
        } else {
            table[r][c] = 0;
        }
//        System.out.println(data);
//        System.out.println(next);

//        for (int[] d : table) {
//            System.out.println(Arrays.toString(d));
//        }
//        System.out.println();

        answer += (data.size() * 2);
        while (!data.isEmpty()) {
            current = findSame(current.row, current.col, false);
            data.remove(next);

            if (data.isEmpty()) {
                break;
            }
            current = findSame(current.row, current.col, true);
        }

        return answer;
    }

    @Override
    public void init() {

        int[][] array = {
            {1, 0, 0, 3},
            {2, 0, 0, 0},
            {0, 0, 0, 2},
            {3, 0, 1, 0}
        };

        int[][] array1 = {
            {3, 0, 0, 2},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {2, 0, 0, 3}
        };

        int solution = solution(array1, 0, 1);
        System.out.println("정답은 : " + solution);

    }

    public static class Node {

        int row;
        int col;
        int count;

        public Node(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        public boolean isSame(Node target) {
            return this.row == target.row && this.col == target.col;
        }

        @Override
        public String toString() {
            return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", count=" + count +
                '}';
        }
    }
}
