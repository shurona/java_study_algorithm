package MathProblem;

import java.util.ArrayList;
import java.util.List;
import utils.AlgoStudy;

/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/77885
 */
public class DifferentTwoBit implements AlgoStudy {

    private long convertTo(long input) {
        List<Integer> arr = new ArrayList<>();
        while (input > 0) {
            arr.add((int) (input % 2));
            input /= 2;
        }

        if (arr.size() == 0) {
            return 1;
        }

        // 짝수인 경우 1만 바꿔주면 된다.
        if (arr.get(0) == 0) {
            arr.set(0, 1);
        } else {
            boolean isAllOne = true;
            // System.out.println(arr);
            for (int i = 0; i < arr.size(); i++) {
                // 0을 찾는다.
                if (arr.get(i) == 0) {
                    // 0은 1로 바꾸고
                    arr.set(i, 1);
                    // 1은 0으로 바꾼다.
                    arr.set(i - 1, 0);
                    isAllOne = false;
                    break;
                }
            }

            if (isAllOne) {
                arr.set(arr.size() - 1, 0);
                arr.add(1);
            }

        }

        // 10진수로 변환
        long output = 0;
        long base = 1;
        for (int num : arr) {
            output += (num * base);
            base *= 2;
        }

        return output;
    }

    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = convertTo(numbers[i]);
        }

        return answer;
    }

    @Override
    public void init() {

    }
}
