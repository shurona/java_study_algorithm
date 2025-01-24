package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1138
 */
public class StandInLine implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(reader.readLine());

        int[] input = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int[] store = new int[number];

        for (int i = 0; i < number; i++) {
            int check = 0;
            int yahoo = 0;
            while (check < input[i]) {
                if (store[yahoo] == 0) {
                    check += 1;
                }
                yahoo += 1;
//                System.out.println(check + " : " + yahoo + " : " + input[i]);

            }

            while (store[yahoo] != 0) {
//                System.out.println("???");
                yahoo += 1;
            }
            store[yahoo] = i + 1;
//            System.out.println(yahoo + " : " + Arrays.toString(store));
        }

        StringBuilder sb = new StringBuilder();

        for (int i : store) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
