package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2467
 */
public class SolutionWater implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

        long[] solutions = Arrays.stream(reader.readLine().split(" "))
            .mapToLong(s -> Long.parseLong(s))
            .toArray();

        // 0에 가까운 숫자를 만드는 방법

        int left = 0;
        int right = solutions.length - 1;

        int answerLeft = 0;
        int answerRight = solutions.length - 1;
        while (left < right) {
            long leftData = solutions[left];
            long rightData = solutions[right];

            // 합을 확인한다.
            long sum = leftData + rightData;
//            System.out.println(sum);

            // 만약 근사치가 적으면 바꾼다.
            if (Math.abs(solutions[answerLeft] + solutions[answerRight]) >= Math.abs(sum)) {
                answerRight = right;
                answerLeft = left;
            }

            if (sum == 0) {
                break;
            } else if (sum > 0) {
                // 만약 합이 0보다 크면
                right -= 1;

            } else {
                left += 1;
            }
        }

        System.out.println(solutions[answerLeft] + " " + solutions[answerRight]);
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
