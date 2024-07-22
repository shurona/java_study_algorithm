package problem;

import utils.BaekAlgoStudy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StandInLine implements BaekAlgoStudy {
    static int answer = 0;
    public static void sorting(int[] dp, int index) {
        int tp = dp[index];

        for (int i = index - 1; i > -1; i--) {
            if(dp[i] <= tp) break;

            dp[i + 1] = dp[i];
            dp[i] = tp;
            answer +=1;
        }

    }

    public void solution() throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int ct = Integer.parseInt(bufferedReader.readLine());

        int[] dp;
        for (int i = 0; i < ct; i++) {
            dp = new int[20];
            answer = 0;
            String input = bufferedReader.readLine();
            int index = -2;
            for (String s : input.split((" "))) {
                index += 1;
                if(index == -1) continue;
                dp[index] = Integer.parseInt(s);
            }

            for (int j = 1; j < 20; j++) {
                sorting(dp, j);
            }

            System.out.println((i + 1) + " " + answer);
        }
        bufferedReader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
