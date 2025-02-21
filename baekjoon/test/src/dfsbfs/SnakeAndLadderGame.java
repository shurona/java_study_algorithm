package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import utils.BaekAlgoStudy;

public class SnakeAndLadderGame implements BaekAlgoStudy {

    static Map<Integer, Integer> ladder = new HashMap<>();
    static Map<Integer, Integer> snake = new HashMap<>();


    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

        for (int i = 0; i < firstLine[0]; i++) {
            int[] tp = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(j -> Integer.parseInt(j)).toArray();

            ladder.put(tp[0], tp[1]);

        }

        for (int i = 0; i < firstLine[1]; i++) {
            int[] tp = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(j -> Integer.parseInt(j)).toArray();

            snake.put(tp[0], tp[1]);
        }

        Queue<Node> nextStep = new LinkedList<>();
        nextStep.add(new Node(0, 1));

        boolean[] visit = new boolean[110];
        visit[1] = true;

        while (!nextStep.isEmpty()) {

            Node now = nextStep.poll();

            if (now.data == 100) {
                System.out.println(now.step);
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int nextBoard = now.data + i;

                if (snake.getOrDefault(nextBoard, -1) > 0) {
                    nextBoard = snake.get(nextBoard);
                } else if (ladder.getOrDefault(nextBoard, -1) > 0) {
                    nextBoard = ladder.get(nextBoard);
                }

                if (visit[nextBoard]) {
                    continue;
                }

                visit[nextBoard] = true;

                // 100이 넘어가면 패스
                if (nextBoard > 100) {
                    continue;
                }

                nextStep.add(new Node(now.step + 1, nextBoard));

            }

        }

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int step;
        int data;

        public Node(int step, int data) {
            this.step = step;
            this.data = data;
        }
    }
}
