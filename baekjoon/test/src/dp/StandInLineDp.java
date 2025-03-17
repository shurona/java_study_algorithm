package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2631
 */
public class StandInLineDp implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        int[] dp = new int[n];
        int[] store = new int[n];

        for (int i = 0; i < n; i++) {
            store[i] = Integer.parseInt(reader.readLine());
        }

        dp[0] = 1; // 연속 길이 1
        for (int i = 1; i < store.length; i++) {
            int max = 1;
            // 이전 값을 확인
            for (int j = 0; j < i; j++) {
                // 이전에 지금보다 작으면
//                System.out.println(i + " ?? " + j);
                // 더 크면 뒤의 길이가 긴 부분을 저장한다.
                if (store[i] > store[j]) {
                    max = Math.max(dp[j] + 1, max);
                }
            }
            dp[i] = max;
        }

        int answer = 0;
        for (int i = 0; i < store.length; i++) {
            answer = Math.max(answer, dp[i]);
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(n - answer);
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
