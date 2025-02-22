package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1697
 */
public class HideAndSeek implements BaekAlgoStudy {

    static boolean checkRangeOut(int input) {

        if (input < 1 || input > 100000) {
            return true;
        }

        return false;
    }

    // 수빈은 x-1, x+1, x*2 3가지의 종류가 있다.
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        int subin = Integer.parseInt(input[0]);

        int sister = Integer.parseInt(input[1]);

        boolean[] dp = new boolean[100001];

        Queue<Node> queue = new LinkedList<>();
//        System.out.println(subin + " : " + sister);

        queue.add(new Node(subin, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.loc == sister) {
                System.out.println(current.step);
                break;
            }
            for (int i = 0; i < 3; i++) {
                int now;
                if (i == 0) {
                    now = current.loc + 1;
                    // + 1
                    if (checkRangeOut(now) || dp[now]) {
                        continue;
                    }
                    dp[now] = true;
                    queue.add(new Node(now, current.step + 1));

                } else if (i == 1) {
                    // - 1
                    now = current.loc - 1;
                    if (checkRangeOut(now) || dp[now]) {
                        continue;
                    }
                    dp[now] = true;
                    queue.add(new Node(now, current.step + 1));

                } else {
                    // * 2
                    now = current.loc * 2;
                    if (checkRangeOut(now) || dp[now]) {
                        continue;
                    }
                    dp[now] = true;
                    queue.add(new Node(now, current.step + 1));

                }
            }

        }

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int loc;
        int step;

        public Node(int loc, int step) {
            this.loc = loc;
            this.step = step;
        }
    }
}
