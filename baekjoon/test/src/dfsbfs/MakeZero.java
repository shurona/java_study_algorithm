package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/7490
 */
public class MakeZero implements BaekAlgoStudy {

    static List<String> answer;

    static String[] store = new String[]{"+", "-", " "};

    static int calNumber(String value, int init) {

        int output = init;

        int step = 1;
        int index = value.length() - 1;
        while (index > 0) {
            char c = value.charAt(index);
            if (c == '+' || c == '-') {
                break;
            } else if (c == ' ') {
                int left = (int) Math.pow(10, step);
                output = left * Integer.parseInt(String.valueOf(value.charAt(index - 1))) + output;
                step += 1;
            }

            index -= 1;
        }

        return output;
    }

    static void dfs(int depth, int last, int middle, int data, int operation, String stringValue) {
        if (depth == last) {
            data += (middle * operation);
//            System.out.println(stringValue + " : " + data);
            if (data == 0) {
                answer.add(stringValue);
            }
            return;
        }

        //1 2-3-4+5-6+7

        for (int i = 0; i < store.length; i++) {
            if (i == 2) {
                int tp = (middle * 10) + (depth + 1);
                dfs(depth + 1, last, tp, data, operation,
                    stringValue + " " + (depth + 1));
            } else {
                // 마이너스 i == 0
                if (i == 1) {
                    dfs(depth + 1, last, depth + 1, data + (middle * operation), -1,
                        stringValue + "-" + (depth + 1));
                } else {
                    // 더하기 i == 1
                    dfs(depth + 1, last, depth + 1, data + (middle * operation), 1,
                        stringValue + "+" + (depth + 1));

                }
            }

        }


    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ct; i++) {
            answer = new ArrayList<>();
            int input = Integer.parseInt(reader.readLine());
            dfs(1, input, 1, 0, 1, "1");
            Collections.sort(answer);
            for (String s : answer) {
                sb.append(s).append("\n");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
