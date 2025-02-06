package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1283
 */
public class ShortCutSetting implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());

        Map<Character, String> store = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input; i++) {
            String data = reader.readLine();

            String[] div = data.split(" ");

            // 첫 글자 확인
            boolean isOkay = false;
            for (int j = 0; j < div.length; j++) {
                char c = div[j].charAt(0);
                // 없으면 들어간다.
                if (store.getOrDefault(Character.toUpperCase(c), null) == null) {
                    store.put(Character.toUpperCase(c), data);
                    isOkay = true;
                    boolean tp = true; // 같은 문자여도 이미 나온거면 넘긴다
                    int part = 0;
                    // .
                    for (int loc = 0; loc < data.length(); loc++) {
                        if (data.charAt(loc) == ' ') {
                            part += 1;
                        }
                        if (tp && data.charAt(loc) == c && part == j) {
                            sb.append("[").append(data.charAt(loc)).append("]");
                            tp = false;
                        } else {
                            sb.append(data.charAt(loc));
                        }
                    }
                    sb.append("\n");
                    break;
                }
            }

            if (isOkay) {
                continue;
            }

            // 나오는 순서대로 진행하면 됨
            for (int k = 0; k < data.length(); k++) {
                char c = data.charAt(k);
                if (c == ' ') {
                    continue;
                }

                if (store.getOrDefault(Character.toUpperCase(c), null) == null) {
                    store.put(Character.toUpperCase(c), data);
                    boolean tp = true;
                    for (int loc = 0; loc < data.length(); loc++) {
                        if (tp && data.charAt(loc) == c) {
                            sb.append("[").append(data.charAt(loc)).append("]");
                            tp = false;
                        } else {
                            sb.append(data.charAt(loc));
                        }
                    }
                    sb.append("\n");
                    isOkay = true;
                    break;
                }
            }

            if (isOkay) {
                continue;
            }

            sb.append(data).append("\n");

        }

        System.out.println(sb);

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
