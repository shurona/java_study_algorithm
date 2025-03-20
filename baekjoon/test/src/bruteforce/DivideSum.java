package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

public class DivideSum implements BaekAlgoStudy {

    @Override
    public void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        boolean isOk = false;

        // 분배합을 구하자
        for (int i = 1; i < 1000000; i++) {
            int tpSum = i;
            int tp = i;

            while (tp > 0) {
                tpSum += tp % 10;
                tp = tp / 10;
            }

            System.out.println(tpSum);
            if (tpSum == num) {
                System.out.println(i);
                isOk = true;
                break;
            }
        }

        if (!isOk) {
            System.out.println(0);
        }

        reader.close();
    }
}
