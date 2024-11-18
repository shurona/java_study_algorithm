package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

public class KmpWhyKmp implements BaekAlgoStudy {

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = reader.readLine();

            String[] split = input.split("-");

            String answer = "";

            for (String s : split) {
                answer += s.charAt(0);
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
