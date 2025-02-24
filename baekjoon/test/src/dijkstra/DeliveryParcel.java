package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/5972
 */
public class DeliveryParcel implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int nodeCt = firstLine[0];
        List<List<Node>> dp = new ArrayList<>();
        for (int i = 0; i <= nodeCt; i++) {
            dp.add(new ArrayList<>());
        }

        for (int i = 0; i < firstLine[1]; i++) {
            int[] input = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

            dp.get(input[0]).add(new Node(input[1], input[2]));
            dp.get(input[1]).add(new Node(input[0], input[2]));

        }

        Queue<Node> queue = new PriorityQueue<>(((o1, o2) -> o1.value - o2.value));
        queue.add(new Node(1, 0));

        boolean[] visited = new boolean[nodeCt + 1];
        visited[1] = true;

        int[] answer = new int[nodeCt + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        answer[1] = 0;

        int dd = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (dd == 6) {
                break;
            }

//            System.out.println(poll.curr + " : " + Arrays.toString(answer));
            if (current.dist == nodeCt) {
                break;
            }

            for (Node node : dp.get(current.dist)) {
                // 방문한 노드는 다시 가지 않는다.
                if (visited[node.dist]) {
                    continue;
                }

                // 하나씩 순회를 하면서 최소 데이터로 업데이트를 해줘야 한다.
                // 이전에서 현재 위치 까지 가는 거리 or 시작에서 바로 가는 방향
                if (answer[node.dist] > answer[current.dist] + node.value) {
                    answer[node.dist] = answer[current.dist] + node.value;
                    queue.add(new Node(node.dist, answer[node.dist]));
                }

            }
//            System.out.println();

        }

        // 정답
        System.out.println(Arrays.toString(answer));
        System.out.println(answer[nodeCt]);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int dist;
        int value;

        public Node(int dist, int value) {
            this.dist = dist;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                "dist=" + dist +
                ", value=" + value +
                '}';
        }
    }

}
