package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/14425
 */
public class StringSet implements BaekAlgoStudy {

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String input = reader.readLine();

            String[] split = input.split(" ");
            int baseSetLen = Integer.parseInt(split[0]);
            int checkSetLen = Integer.parseInt(split[1]);

            List<String> arr = new ArrayList<>();

            for (int i = 0; i < baseSetLen; i++) {
                arr.add(reader.readLine());
            }

            int answer = 0;
            for (int i = 0; i < checkSetLen; i++) {
                input = reader.readLine();
                for (String base : arr) {
                    if (base.equals(input)) {
//                        System.out.println(input);
                        answer += 1;
                        break;
                    }

                }
            }

            System.out.println(answer);

            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    @Override
    public void init() throws IOException {

        solution();

    }
}
