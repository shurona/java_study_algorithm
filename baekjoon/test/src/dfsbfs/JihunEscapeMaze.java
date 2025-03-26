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
    https://www.acmicpc.net/problem/4179

    문제 해결 과정 중에 처음에 잘못접근 했던 부분
        처음 makeFire 함수를 살펴보면 마름모 모양으로 불이 펴진다고 생각을 하고
        중복되는 컬럼을 무시하고 계산을 했었다.
        => 여기서 중복컬럼을 없애는 최고의 방법은 BFS

    고친 방법
        본인 위치 뿐만이 아니라 불의 위치도 BFS를 적용하면 된다는 것을 생각하지 못한 것이 처음 접근 방법의 문제였다.
        불의 위치도 BFS를 접근하면서 본인의 위치 이동과 step을 맞춰가면서 이동하였다.

 */
public class JihunEscapeMaze implements BaekAlgoStudy {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void fireBfs(String[][] maze, Queue<Node> fireQueue, boolean[][] fireVisit,
        int currentStep) {

        while (!fireQueue.isEmpty()) {
            // 현재 단계를 확인한다.
            if (fireQueue.peek().step != currentStep) {
                break;
            }

            Node poll = fireQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = poll.row + dx[i];
                int nextCol = poll.col + dy[i];

                // 범위를 벗어난 거면 나간것이므로 탈출
                if (nextRow < 0 || nextCol < 0 || nextRow >= maze.length
                    || nextCol >= maze[0].length) {
                    continue;
                }

                if (maze[nextRow][nextCol].equals("#") || maze[nextRow][nextCol].equals("F")) {
                    continue;
                }

                if (fireVisit[nextRow][nextCol]) {
                    continue;
                }

                fireVisit[nextRow][nextCol] = true;
                maze[nextRow][nextCol] = "F";
                fireQueue.add(new Node(nextRow, nextCol, poll.step + 1));
            }

        }

    }

    static void makeFire(String[][] maze, int fireRow, int fireCol, int step) {
        for (int i = 0; i <= step; i++) {
            // 좌로 하나씩 불을 넣는다.
            for (int j = 0; j <= step - i; j++) {
//                System.out.println(i + " : " + j + " : " + step);
                int leftFireCol = fireCol - i;
                int leftFireRowUp = fireRow + j;
                int leftFireRowDown = fireRow - j;

                // 위
                if (leftFireCol < maze[0].length && leftFireCol >= 0
                    && leftFireRowUp < maze.length && leftFireRowUp >= 0) {
                    if (!maze[leftFireRowUp][leftFireCol].equals("#")) {
                        maze[leftFireRowUp][leftFireCol] = "F";
                    }
                }

                // 아래
                if (leftFireCol < maze[0].length && leftFireCol >= 0
                    && leftFireRowDown < maze.length && leftFireRowDown >= 0) {
                    if (!maze[leftFireRowDown][leftFireCol].equals("#")) {
                        maze[leftFireRowDown][leftFireCol] = "F";
                    }
                }
//                System.out.println(leftFireRowUp + " : " + leftFireCol);

            }

            // 우로 하나씩 불을 넣는다.
            for (int j = 0; j <= step - i; j++) {
                int rightFireCol = fireCol + i;
                int rightFireRowUp = fireRow + j;
                int rightFireRowDown = fireRow - j;

                // 위
                if (rightFireCol < maze[0].length && rightFireCol >= 0
                    && rightFireRowUp < maze.length && rightFireRowUp >= 0) {
                    if (!maze[rightFireRowUp][rightFireCol].equals("#")) {
                        maze[rightFireRowUp][rightFireCol] = "F";
                    }
                }

                // 아래
                if (rightFireCol < maze[0].length && rightFireCol >= 0
                    && rightFireRowDown < maze.length && rightFireRowDown >= 0) {
                    if (!maze[rightFireRowDown][rightFireCol].equals("#")) {
                        maze[rightFireRowDown][rightFireCol] = "F";
                    }
                }
            }
        }
    }


    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        String[][] maze = new String[firstLine[0]][firstLine[1]];
        boolean[][] visit = new boolean[firstLine[0]][firstLine[1]];
        boolean[][] fireVisit = new boolean[firstLine[0]][firstLine[1]];

        Queue<Node> fireQueue = new PriorityQueue<>((o1, o2) -> o1.step - o2.step);
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.step - o2.step);
        for (int i = 0; i < maze.length; i++) {
            maze[i] = reader.readLine().split("");

            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j].equals("J")) {
                    queue.add(new Node(i, j, 0));
                    visit[i][j] = true;
                } else if (maze[i][j].equals("F")) {
                    fireQueue.add(new Node(i, j, 0));
                    fireVisit[i][j] = true;
                }
            }
        }

        int answer = 987654321;
        int currentStep = 0;

        while (!queue.isEmpty()) {

            Node poll = queue.poll();

//            if (poll.step == 3) {
//                break;
//            }

            // 스텝이 변경되었을 때
            if (currentStep != poll.step) {
                fireBfs(maze, fireQueue, fireVisit, currentStep);
                currentStep = poll.step;

            }

            if (maze[poll.row][poll.col].equals("F")) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = poll.row + dx[i];
                int nextCol = poll.col + dy[i];

                // 범위를 벗어난 거면 나간것이므로 탈출
                if (nextRow < 0 || nextCol < 0 || nextRow >= maze.length
                    || nextCol >= maze[0].length) {
//                    System.out.println(nextRow + " : " + nextCol + " : " + poll.step);
                    answer = Math.min(answer, poll.step + 1);
                    continue;
                }

                if (maze[nextRow][nextCol].equals("#") || maze[nextRow][nextCol].equals("F")) {
                    continue;
                }

                if (visit[nextRow][nextCol]) {
                    continue;
                }

                visit[nextRow][nextCol] = true;
                queue.add(new Node(nextRow, nextCol, poll.step + 1));
            }

        }

//        for (String[] strings : maze) {
//            System.out.println(Arrays.toString(strings));
//        }

        if (answer == 987654321) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }

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

        public Node(int row, int col, int step) {
            this.row = row;
            this.col = col;
            this.step = step;
        }
    }
}
