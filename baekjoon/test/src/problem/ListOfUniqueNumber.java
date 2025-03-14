package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/13144
 */
public class ListOfUniqueNumber implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        reader.readLine();

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        long answer = 0;

        int left = 0;
        int right = 0;
        int[] dp = new int[100001];

        while (left < numbers.length) {
            while (right < numbers.length) {
//                System.out.println(Arrays.toString(dp));
                if (dp[numbers[right]] > 0) {
                    break;
                }
                dp[numbers[right]] += 1;
                right += 1;
            }
//            System.out.println(right - left);
            answer += (right - left);
//            System.out.println();
            dp[numbers[left]] -= 1;
            left += 1;
        }

//        int n = 100000;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= n; i++) {
//            sb.append(i);
//            if (i < n) {
//                sb.append(" ");
//            }
//        }
//        System.out.println(sb.toString());
        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
