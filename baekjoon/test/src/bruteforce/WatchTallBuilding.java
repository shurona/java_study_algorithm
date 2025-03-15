package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

public class WatchTallBuilding implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        reader.readLine();

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int answer = -1;
        // 왼쪽과 오른쪽에 자신보다 같거나 높은 빌딩을 확인한다.
        for (int i = 0; i < numbers.length; i++) {
            int tp = 0;
            int currentHeight = numbers[i];
            double tpInclination = -98765431;
            // 왼쪽
            for (int index = i - 1; index >= 0; index--) {
//                tpInclination =
//                    Math.max(tpInclination,
//                        (double) (numbers[index] - currentHeight) / (i - index));
                double currentInclination = (double) (numbers[index] - currentHeight) / (i - index);

                if (index == i - 1 || tpInclination < currentInclination) {
                    tp += 1;
                    tpInclination = currentInclination;
                }

            }

            // 오른쪽
            for (int index = i + 1; index < numbers.length; index++) {
//                tpInclination =
//                    Math.max(tpInclination,
//                        (double) (numbers[index] - currentHeight) / (index - i));
                double currentInclination = (double) (numbers[index] - currentHeight) / (index - i);

                if (index == i + 1 || tpInclination < currentInclination) {
                    tp += 1;
                    tpInclination = currentInclination;
                }

            }

//            System.out.println(tp);
            if (tp > answer) {
//                System.out.println("?? : " + i + " : " + tp);
            }
            answer = Math.max(answer, tp);

//            break;
//            System.out.println();
        }

        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
