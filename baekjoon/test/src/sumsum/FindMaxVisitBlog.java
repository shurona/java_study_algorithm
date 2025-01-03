package sumsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import utils.BaekAlgoStudy;

/*
    문제 링크 (누적합)
    https://www.acmicpc.net/problem/21921
 */
public class FindMaxVisitBlog implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstInput = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

        int range = firstInput[1];

        int[] visitList = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

        int[] sumList = new int[firstInput[0] + 1];

        int startSum = 0;
        for (int i = 0; i < range; i++) {
            startSum += visitList[i];
        }
        Map<Integer, Integer> store = new HashMap<>();

        int answer = startSum;
        sumList[0] = visitList[0];
        int currentSum = startSum;
        store.put(startSum, 1);
        for (int i = range; i < visitList.length; i++) {
            int plusValue = visitList[i];
            int minusValue = visitList[i - range];

            currentSum -= minusValue;
            currentSum += plusValue;

            // 비교한다.
//            System.out.println(currentSum);
            Integer ct = store.getOrDefault(currentSum, 0);
            answer = Math.max(answer, currentSum);
            store.put(currentSum, ct + 1);
        }

        if (answer == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(answer);
            System.out.println(store.get(answer));
        }

//        System.out.println(store);
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
