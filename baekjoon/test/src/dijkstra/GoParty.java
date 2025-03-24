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
    https://www.acmicpc.net/problem/1238

    풀이 과정
    사람이 다른 곳에 들렸다가 다시 돌아오는 왔을 때 최댓값으로
    경유지라 생각하고 나눠서 풀면 된다.
 */
public class GoParty implements BaekAlgoStudy {


    static List<List<Node>> store = new ArrayList<>();

    static void calCost(int[] dp, int start) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        boolean[] visited = new boolean[dp.length];
        dp[start] = 0;
        visited[start] = true;
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            List<Node> nodes = store.get(current.loc);
            for (Node node : nodes) {
                // 이제 dp와 비교를 한다.
                if ((current.value + node.value) < dp[node.loc]) {
                    dp[node.loc] = current.value + node.value;
                    queue.add(new Node(node.loc, current.value + node.value));
                }
            }
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int n = firstLine[0];
        int m = firstLine[1];
        int x = firstLine[2];

        for (int i = 0; i <= n; i++) {
            store.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] abs = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

            store.get(abs[0]).add(new Node(abs[1], abs[2]));

        }

        // 다익스트라 배열 만들어놓자.
        int[][] answer = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(answer[i], 987654321);
            calCost(answer[i], i);
        }

        int output = -1;
        for (int i = 1; i <= n; i++) {
            output = Math.max(answer[i][x] + answer[x][i], output);
        }

        System.out.println(output);
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int loc;
        int value;

        public Node(int loc, int value) {
            this.loc = loc;
            this.value = value;
        }
    }
}
