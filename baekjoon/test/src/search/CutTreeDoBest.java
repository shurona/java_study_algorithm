package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크(이진탐색)
    https://www.acmicpc.net/problem/2805
 */
public class CutTreeDoBest implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstInput = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

        int wishTreeSize = firstInput[1];

        int[] treeInputs = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

        long minValue = 1;
        long maxValue = 2000000000;

        long answer = -1;
        while (minValue <= maxValue) {
//            System.out.println(
//                minValue + " : " + maxValue + "  : " + Integer.sum(minValue, maxValue));
            long cutSize = (Long.sum(minValue, maxValue)) / 2;

            long cutSum = 0;
            for (int treeSize : treeInputs) {
                if (treeSize > cutSize) {
                    cutSum += treeSize - cutSize;
                }

            }
//            System.out.println(cutSize + " : " + cutSum);
            if (cutSum >= wishTreeSize) {
                answer = Math.max(cutSize, answer);
//                System.out.println(cutSize + " : " + wishTreeSize + " : " + cutSum);
                minValue = cutSize + 1;
            } else {
                maxValue = cutSize - 1;
            }


        }

//        System.out.println(Integer.sum(minValue, maxValue));
        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
