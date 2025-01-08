package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2607
 */
public class SimilarWord implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> store = new HashMap<>();

        int lineLength = Integer.parseInt(reader.readLine());

        String gizun = reader.readLine();

        for (int i = 0; i < gizun.length(); i++) {
            Integer ct = store.getOrDefault(gizun.charAt(i), 0);

            store.put(gizun.charAt(i), ct + 1);
        }

        int answer = 0;
        for (int i = 1; i < lineLength; i++) {
            Map<Character, Integer> tp = new HashMap<>();
            String input = reader.readLine();

            int orderCompare = 0;
            for (int loc = 0; loc < input.length(); loc++) {

                // compare
                if (input.length() == gizun.length() && gizun.charAt(loc) != input.charAt(loc)) {
                    orderCompare += 1;
                }

                Integer ct = tp.getOrDefault(input.charAt(loc), 0);
                tp.put(input.charAt(loc), ct + 1);
            }

            if (orderCompare <= 1 && input.length() == gizun.length()) {
//                System.out.println(input);
                answer += 1;
                continue;
            }

            int check = 0;
            int exceptCheck = 0;
            int minCheck = 0;

            // 비교해준다.
            for (Character c : store.keySet()) {
                Integer sub = tp.getOrDefault(c, 0);
                Integer main = store.get(c);

                int abs = Math.abs(main - sub);
                exceptCheck += sub;
                check += abs;
                minCheck += Math.min(sub, main);

            }

            //ABABA
            //BBAAB

//            System.out.println(input + " : " + check + " : " + minCheck);
            if (check + (input.length() - exceptCheck) <= 1) {
//                System.out.println(input);
                answer += 1;
            } else if ((input.length() - minCheck) == 1 && input.length() == gizun.length()) {
//                System.out.println(input);
                answer += 1;
            }
        }

        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
