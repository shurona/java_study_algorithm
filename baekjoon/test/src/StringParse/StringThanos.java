package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/20310
 */
public class StringThanos implements BaekAlgoStudy {

    @Override
    public void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            String input = reader.readLine();
            String[] arr = new String[input.length()];
            int[] dp = new int[2];
            for (int i = 0; i < input.length(); i++) {
                int i1 = Integer.parseInt(String.valueOf(input.charAt(i)));
                arr[i] = String.valueOf(input.charAt(i));
                dp[i1] += 1;
            }

//            System.out.println(Arrays.toString(arr));

            dp[0] = dp[0] / 2;
            dp[1] = dp[1] / 2;

            int ct = 0;
            int leftIndex = 0;
            int rightIndex = arr.length - 1;
            for (int i = 0; i < dp[1]; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j].equals("1")) {
                        arr[j] = "-";
                        break;
                    }
                }
            }

            for (int i = 0; i < dp[0]; i++) {
                for (int j = arr.length - 1; j >= 0; j--) {
                    if (arr[j].equals("0")) {
                        arr[j] = "-";
                        break;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            for (String s : arr) {
                if (!s.equals("-")) {
                    sb.append(s);
                }
            }

            System.out.println(sb.toString());

            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
