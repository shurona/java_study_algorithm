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
    https://www.acmicpc.net/problem/1260
 */
public class DefaultDfsBfs implements BaekAlgoStudy {

    static int[][] nodeInfo;
    static String answer = "";

    public static void bfs(boolean[] visited, int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        String answer = "";
        visited[start] = true;

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            int currentNode = queue.poll();
            answer += currentNode + " ";

            for (int i = 1; i < visited.length; i++) {
                if (nodeInfo[currentNode][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }

            }

        }

        System.out.println(answer);
    }

    public static void dfs(boolean[] visited, int start, int depth, StringBuilder output) {
        int[] currentNodeInfo = nodeInfo[start];

        for (int i = 1; i < visited.length; i++) {
            if (currentNodeInfo[i] == 0) {
                continue; // 연결되지 않은 경우 무시
            }

            if (!visited[i]) {
                visited[i] = true;
                dfs(visited, i, depth + 1, output); // 재귀 호출
            }
        }

        // 현재 노드를 문자열 맨 앞에 추가
        if (output.length() > 0) {
            output.insert(0, " ");
        }
        output.insert(0, start);
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int size = input[0];
        int start = input[2];

        nodeInfo = new int[size + 1][size + 1];
        for (int i = 0; i < input[1]; i++) {
            String[] tp = reader.readLine().split(" ");

            nodeInfo[Integer.parseInt(tp[0])][Integer.parseInt(tp[1])] = 1;
            nodeInfo[Integer.parseInt(tp[1])][Integer.parseInt(tp[0])] = 1;

        }
        boolean[] visited = new boolean[size + 1];

        //
        visited[start] = true;
        StringBuilder sb = new StringBuilder();
        dfs(visited, start, 1, sb);
        System.out.println(sb);

        // 초기화
        visited = new boolean[size + 1];
        bfs(visited, start);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }


}
