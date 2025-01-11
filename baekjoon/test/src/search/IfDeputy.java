package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/19637
 */
public class IfDeputy implements BaekAlgoStudy {

    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(reader.readLine().split(" ")).mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int conditionSize = array[0];
        int characterSize = array[1];

        int[] conditionArray = new int[conditionSize];
        String[] conditionLevel = new String[conditionSize];
        for (int i = 0; i < conditionSize; i++) {
            String[] input = reader.readLine().split(" ");
            conditionArray[i] = Integer.parseInt(input[1]);
            conditionLevel[i] = input[0];
        }

//        System.out.println(Arrays.toString(conditionArray));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < characterSize; i++) {

            int input = Integer.parseInt(reader.readLine());

            int minNum = 0;
            int maxNum = conditionSize - 1;

            while (minNum <= maxNum) {
                int middle = (minNum + maxNum) / 2;
                int tp = conditionArray[middle];
                if (tp < input) {
                    minNum = middle + 1;
                } else {
                    maxNum = middle - 1;
                }
            }
            sb.append(conditionLevel[minNum]).append("\n");

        }

        System.out.println(sb.toString());
        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
