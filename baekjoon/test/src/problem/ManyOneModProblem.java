package problem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

public class ManyOneModProblem implements BaekAlgoStudy {

    @Override
    public void init() {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String input = "init";

        try {
            while ((input = bfr.readLine()) != null) {

                int now = Integer.parseInt(input);

                int count = 1;
                int mod = 0;
                while (true) {
                    mod = mod * 10 + 1;
//                    System.out.println(mod);
                    mod = mod % now;

                    if (mod == 0) {
                        System.out.println(count);
                        break;
                    }
                    count += 1;
                }
            }

            bfr.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


    }
}
