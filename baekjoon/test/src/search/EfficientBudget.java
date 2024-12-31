package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크 추가
    https://www.acmicpc.net/problem/2512
 */
public class EfficientBudget implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());

        int[] requestBudgetList = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int minBudget = 1;
        int budget = Integer.parseInt(reader.readLine());
        int maxBudget = budget;

        int tempBudget = -1;
        int answer = 0;
        while (minBudget <= maxBudget) {
            int middleBudget = (minBudget + maxBudget) / 2;
            int sum = 0;
            int currentMaxBudget = -1;
            for (int requestBudget : requestBudgetList) {
                //순차적으로 돌면서 비교를 한다.
                if (requestBudget > middleBudget) {
                    sum += middleBudget;
                    currentMaxBudget = Math.max(middleBudget, currentMaxBudget);
                } else {
                    sum += requestBudget;
                    currentMaxBudget = Math.max(requestBudget, currentMaxBudget);
                }
            }

//            System.out.println(sum + " => " + minBudget + " : " + middleBudget + " : " + maxBudget);
            if (budget < sum) {
                maxBudget = middleBudget - 1;
            } else {
                // 이 때만 참이다.
                answer = Math.max(answer, currentMaxBudget);
//                System.out.println(tempBudget + " : " + currentMaxBudget);
                minBudget = middleBudget + 1;
            }
        }

//        System.out.println(tempBudget);
        System.out.println(answer);
        reader.close();

    }


    @Override
    public void init() throws IOException {
        solution();
    }
}
