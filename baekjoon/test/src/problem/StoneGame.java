package problem;

import utils.BaekAlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class StoneGame implements BaekAlgoStudy {

    public void solution(int n) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.close();


        // 4로 나눴을 때 2로 나눴을 때
        if (ct % 4 == 2 || ct % 4 == 0) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }

    }

    @Override
    public void init() throws IOException {
        solution(5);

    }
}
