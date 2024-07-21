package mathstepone;

import utils.BaekAlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangeDecimal implements BaekAlgoStudy {

    public int changeNum(char input) {

        String num = "0123456789";
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == input) {
                return i;
            }
        }

        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < alpha.length(); i++) {
            if (alpha.charAt(i) == input) {
                return i + 10;
            }
        }

        return -1;
    }

    public int solution() throws IOException {

        try {

        } catch (Exception e) {
        }

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String input = bfr.readLine();

        String[] value = input.split(" ");

        String s = value[0];
        int dec = Integer.parseInt(value[1]);

        int answer = 0;
        int ii = 1;
        for (int i = 0; i < s.length(); i++) {
            int currentIndex = s.length() - (i + 1);

            answer += ii * changeNum(s.charAt(currentIndex));
            System.out.println(ii);
            ii *= dec;

        }

        bfr.close();

        System.out.println(answer);
        return answer;
    }

    @Override
    public void init() throws IOException {
        int solution = solution();
        System.out.println("답은 : " + solution);

    }
}
