package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
 * 문제 링크
 * https://www.acmicpc.net/problem/1515
 */
public class EraseNumber implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        int givenLoc = 0;

        for (int i = 1; i < Integer.MAX_VALUE; i++) {

            String convertNumber = String.valueOf(i);

            for (int loc = 0; loc < convertNumber.length(); loc++) {
                if (givenLoc == input.length()) {
                    break;
                }
                //  변환 String                    주어진 숫자
                if (convertNumber.charAt(loc) == input.charAt(givenLoc)) {
                    givenLoc += 1;
                }
            }

            if (givenLoc == input.length()) {
//                System.out.println(i);
//                break;
            }
        }

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
