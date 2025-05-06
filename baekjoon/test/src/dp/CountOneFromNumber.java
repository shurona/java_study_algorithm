package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/9527

    문제 해결
    비트마스크와 누적합을 이용해서 숫자 사이의 이진법 수의 1의 갯수를 구하는 방법
    2진법 수의 자릿수가 변경될 때 마다 1의 갯수의 규칙을 이용해서 구하는 문제이다.

 */
public class CountOneFromNumber implements BaekAlgoStudy {

    static long[] dp = new long[55];

    static void setupDp() {
        dp[0] = 1;
        for (int i = 1; i < 55; i++) {
            dp[i] = (dp[i - 1] << 1) + (1L << i);
        }
    }

    static long countOne(long n) {
        // 짝수 인지 홀수 인지 확인
        long count = n & 1;
        int size = (int) (Math.log(n) / Math.log(2));
        for (int i = size; i > 0; i--) {
            if ((n & (1L << i)) != 0L) {
                count += dp[i - 1] + (n - (1L << i) + 1);
                n -= (1L << i);    //비트 이동시키기
            }
        }
        return count;    //1의 개수 반환
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);

        setupDp();
        long l = countOne(b) - countOne(a - 1);
        System.out.println(l);

//        2 12

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
