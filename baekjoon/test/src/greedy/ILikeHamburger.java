package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
 * 문제 링크
 * https://www.acmicpc.net/problem/19941
 */
public class ILikeHamburger implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int maxLength = inputs[1];

        String buggerAndHuman = reader.readLine();

        boolean[] dp = new boolean[inputs[0]];

        int answer = 0;
        for (int i = 0; i < buggerAndHuman.length(); i++) {
            boolean isEat = false;
            if (buggerAndHuman.charAt(i) == 'P') {
                // 왼쪽으로 한 번
                for (int step = maxLength; step > 0; step--) {
                    int loc = i - step;

                    if (loc < 0) {
                        continue;
                    }
                    if (!dp[loc] && buggerAndHuman.charAt(loc) == 'H') {
                        System.out.println("loc :" + loc);
                        answer += 1;
                        isEat = true;
                        dp[loc] = true;
                        break;
                    }

                }

                // 이미 먹었으면 break;
                if (isEat) {
                    continue;
                }

                // 오른쪽으로 한 번
                for (int step = 1; step <= maxLength; step++) {
                    int loc = i + step;
                    if (loc >= buggerAndHuman.length()) {
                        break;
                    }
                    if (!dp[loc] && buggerAndHuman.charAt(loc) == 'H') {
                        answer += 1;
                        isEat = true;
                        dp[loc] = true;
                        break;
                    }

                }

            }

        }

        System.out.println(Arrays.toString(dp));
        System.out.println(answer);

        reader.close();


    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
