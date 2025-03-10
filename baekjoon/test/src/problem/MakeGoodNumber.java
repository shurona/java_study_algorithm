package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1253
 */
public class MakeGoodNumber implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Integer.parseInt(reader.readLine());

        long[] store = Arrays.stream(reader.readLine().split(" "))
            .mapToLong(s -> {
                return Long.parseLong(s);
            })
            .toArray();

        Arrays.sort(store);

        int answer = 0;
        for (int index = 1; index < store.length; index++) {
            int left = 0;
            int right = store.length - 1;
            while (left < right) {
                long leftData = store[left];
                long rightData = store[right];
//                System.out.println(leftData + " : " + rightData);

                if (left == index) {
                    left += 1;
                    continue;
                }
                if (right == index) {
                    right -= 1;
                    continue;
                }

                long sum = leftData + rightData;
                if (sum == store[index]) {
//                    System.out.println("? : " + sum);
                    answer += 1;
                    break;
                }

                if (sum > store[index]) {
                    right -= 1;
                } else {
                    left += 1;
                }
            }

        }

        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
