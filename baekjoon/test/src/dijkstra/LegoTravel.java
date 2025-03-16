package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1976
 */
public class LegoTravel implements BaekAlgoStudy {

    public static void bfs(int[][] dp, int start) {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[dp.length];

        queue.add(start);
        visit[start] = true;

        while (!queue.isEmpty()) {

            Integer current = queue.poll();

            for (int i = 0; i < dp[current].length; i++) {
                if (!visit[i] && dp[current][i] == 1) {
                    dp[start][i] = 1;
                    visit[i] = true;
                    queue.add(i);
                }
            }

        }

    }

    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        int m = Integer.parseInt(reader.readLine());

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i] = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        }

        for (int i = 0; i < n; i++) {
            bfs(dp, i);
            dp[i][i] = 1;
        }

//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }

        int[] plan = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        boolean canGo = true;
        for (int i = 0; i < plan.length - 1; i++) {
            int start = plan[i] - 1;
            int end = plan[i + 1] - 1;
            if (dp[start][end] == 0) {
                canGo = false;
                break;
            }

        }

        if (canGo) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
