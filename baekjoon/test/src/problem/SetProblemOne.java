package problem;

import utils.BaekAlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SetProblemOne implements BaekAlgoStudy {

    public void solution() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


            String inputLines = bufferedReader.readLine();

            int[] dp = new int[21];
            int[] fill = new int[21];
            Arrays.fill(fill, 1);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Integer.parseInt(inputLines); i++) {
                String input = bufferedReader.readLine();
                String[] sp = input.split(" ");
                String operation = sp[0];

                int num = 0;
                if (sp.length > 1) {
                    num = Integer.parseInt(sp[1]);
                }

                switch(operation) {
                    case "add":
                        dp[num] = 1;
                        break;

                    case "check":
                        if (dp[num] == 1) {
                            sb.append(1 + "\n");
                        } else {
                            sb.append(0 + "\n");
                        }
                        break;

                    case "remove":
                        dp[num] = 0;
                        break;

                    case "toggle":
                        if (dp[num] == 1) {
                            dp[num] = 0;
                        } else {
                            dp[num] = 1;
                        }
                        break;

                    case "all":
                        dp = Arrays.copyOf(fill, fill.length);
                        break;

                    case "empty":
                        dp = new int[21];
                        break;

                    default:
                        //
                }

            }

            bufferedReader.close();
            System.out.println(sb);

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
