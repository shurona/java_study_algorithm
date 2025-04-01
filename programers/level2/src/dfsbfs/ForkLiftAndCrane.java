package dfsbfs;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import utils.AlgoStudy;

/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/388353

    문제 해결
    간단한 DFS 문제이다.
    지게차의 경우 패딩을 해서 null인경우에만 이동해서 갖고 올 수 있도록 진행한다.
 */
public class ForkLiftAndCrane implements AlgoStudy {

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public void crane(String[][] padded, String target) {
        for (int row = 1; row < padded.length; row++) {
            for (int col = 1; col < padded[0].length; col++) {
                if (padded[row][col] == null) {
                    continue;
                }
                if (target.equals(padded[row][col])) {
                    padded[row][col] = null;
                }
            }
        }
    }

    public void forkLift(String[][] padded, String target) {

        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.step - o2.step);
        boolean[][] visit = new boolean[padded.length][padded[0].length];

        queue.add(new Node(0, 0, 0));
        visit[0][0] = true;

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                if (nextRow < 0 || nextCol < 0
                    || nextRow >= padded.length || nextCol >= padded[0].length) {
                    continue;
                }

                if (visit[nextRow][nextCol]) {
                    continue;
                }

                visit[nextRow][nextCol] = true;
                if (padded[nextRow][nextCol] == null) {
                    queue.add(new Node(nextRow, nextCol, current.step + 1));
                } else if (padded[nextRow][nextCol].equals(target)) {
                    padded[nextRow][nextCol] = null;
                }
            }

        }

    }

    public int solution(String[] storage, String[] requests) {
        // storage를 padding 한다.
        String[][] padded = new String[storage.length + 2][storage[0].length() + 2];

        for (int row = 0; row < storage.length; row++) {
            for (int col = 0; col < storage[row].length(); col++) {
                padded[row + 1][col + 1] = String.valueOf(storage[row].charAt(col));
            }
        }

        for (String request : requests) {
            if (request.length() == 2) {
                crane(padded, String.valueOf(request.charAt(0)));
            } else {
                forkLift(padded, request);
            }
        }

        int answer = 0;
        for (String[] strings : padded) {
            System.out.println(Arrays.toString(strings));

            for (String data : strings) {
                if (data != null) {
                    answer += 1;
                }
            }
        }

        return answer;
    }

    @Override
    public void init() {
//        String[] storage = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
//        String[] requests = {"A", "BB", "A"};

//        String[] storage = {"HAH", "HBH", "HHH", "HAH", "HBH"};
//        String[] requests = {"C", "B", "B", "B", "B", "H"};

        String[] storage = {"DAAAAAAAAAA", "AADDDDDDDDD"};
        String[] requests = {"C", "B", "B", "B", "AA", "DD"};

        int solution = solution(storage, requests);
        System.out.println("정답은 : " + solution);
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
