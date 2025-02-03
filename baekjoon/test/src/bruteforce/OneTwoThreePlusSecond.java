package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/12101
 */
public class OneTwoThreePlusSecond implements BaekAlgoStudy {

    static int step = 1;
    static String answer = null;

    public static void dfs(String data, int partSum, int maxSum, int target) {

        if (partSum > maxSum) {
            return;
        }

        if (partSum == maxSum) {
//            System.out.println(step + " : " + data);
            if (step == target) {
                answer = data;
            }
            step += 1;
            return;
        }

//        System.out.println(data + " : " + partSum);
//        System.out.println();
        for (int i = 1; i <= 3; i++) {
            dfs(data + "+" + i, partSum + i, maxSum, target);
        }

        return;
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        for (int i = 1; i <= 3; i++) {
            dfs(i + "", i, n, k);
        }

        if (answer == null) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
