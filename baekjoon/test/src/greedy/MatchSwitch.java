package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2138
 */
public class MatchSwitch implements BaekAlgoStudy {

    static String targetString;
    static int MAX_VALUE = 987654321;

    static int switchUntilSame(int[] input, int[] secondLine) {

        int changeCount = 0;
        for (int i = 1; i < input.length; i++) {
            if (input[i - 1] != secondLine[i - 1]) {
                changeByIndex(input, i);
                changeCount += 1;

            }

        }

        return changeCount;
    }

    static void changeByIndex(int[] inputData, int index) {

        if (index > 0) {
            inputData[index - 1] = inputData[index - 1] ^ 1;
        }
        inputData[index] = inputData[index] ^ 1;
        if (index < inputData.length - 1) {
            inputData[index + 1] = inputData[index + 1] ^ 1;
        }

    }

    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

        int[] firstLine = Arrays.stream(reader.readLine().split(""))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        targetString = reader.readLine();
        int[] secondLine = Arrays.stream(targetString.split(""))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

//        System.out.println(Arrays.toString(firstLine));
//        System.out.println(Arrays.toString(secondLine));

        int answer = MAX_VALUE;

        int[] copyArray = Arrays.copyOf(firstLine, firstLine.length);

        changeByIndex(copyArray, 0);

//        System.out.println(Arrays.toString(firstLine));

        int d = switchUntilSame(firstLine, secondLine);

        String tp = Arrays.stream(firstLine).mapToObj(inner -> String.valueOf(inner))
            .collect(Collectors.joining());

//            System.out.println(Arrays.toString(input));

        if (tp.equals(targetString)) {
            answer = Math.min(d, answer);

        }

//        System.out.println(tp);
//        System.out.println();
//        System.out.println(Arrays.toString(copyArray));
        d = switchUntilSame(copyArray, secondLine) + 1;

        tp = Arrays.stream(copyArray).mapToObj(inner -> String.valueOf(inner))
            .collect(Collectors.joining());

//            System.out.println(Arrays.toString(input));

        if (tp.equals(targetString)) {
            answer = Math.min(d, answer);

        }

        if (answer >= MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
