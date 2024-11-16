package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import utils.AlgoLevel3Study;

public class ComebackArmy implements AlgoLevel3Study {


    List<List<Node>> graph = new ArrayList<>();

    public int[] findPath(int n, int start) {
        boolean[] dp = new boolean[n + 1];
        int[] pathList = new int[n + 1];
        Arrays.fill(pathList, Integer.MAX_VALUE);

        Queue<Node> queue = new LinkedList<>();

        pathList[start] = 0;
        queue.add(new Node(start));

        while (!queue.isEmpty()) {
            Node loc = queue.poll();

            if (dp[loc.end]) {
                continue;
            }

            dp[loc.end] = true;

            // 갈 수 있는 곳을 순회를 한다.
            for (Node data : graph.get(loc.end)) {
                pathList[data.end] = pathList[data.end] > 1 + pathList[loc.end]
                    ? 1 + pathList[loc.end]
                    : pathList[data.end];

                queue.add(new Node(data.end));
            }


        }

        // System.out.println("?? : " + Arrays.toString(pathList));

        return pathList;
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[1]).add(new Node(road[0]));
            graph.get(road[0]).add(new Node(road[1]));
        }

        int[] dp = findPath(n, destination);

        for (int source : sources) {
            int dd = dp[source];
            ans.add(dd == Integer.MAX_VALUE ? -1 : dd);
        }

        int[] answer = ans.stream().mapToInt(a -> a).toArray();
        return answer;
    }

    @Override
    public void init() {

    }

    public static class Node {

        int end;

        public Node(int end) {
            this.end = end;
        }

        public String toString() {
            return "end : " + end;
        }
    }
}
