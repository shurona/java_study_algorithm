package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1806
 */
public class PartSum implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToLong(s -> Long.parseLong(s))
            .toArray();

        Long[] store = Arrays.stream(reader.readLine().split(" "))
            .map(s -> Long.parseLong(s))
            .toArray(Long[]::new);

        int answer = 987654321;

        int len = 0;
        int sum = 0;
        int left = 0;
        int right = 0;
        while (left < store.length) {
            while (right < store.length) {
                sum += store[right];
                len += 1;
                right += 1;
                if (sum >= firstLine[1]) {
//                    System.out.println(left + " : " + right + " : " + sum + " : " + len);
                    answer = Math.min(answer, len);
                    break;
                }
            }

            sum -= store[left];
            left += 1;
            len -= 1;
            while (len > 1 && sum >= firstLine[1]) {
                answer = Math.min(answer, len);
                sum -= store[left];
                left += 1;
                len -= 1;
            }

            if (len > 0 && sum >= firstLine[1]) {
//                System.out.println(left + " : " + sum + " : " + len);
                answer = Math.min(answer, len);
            }
        }

//        System.out.println(Arrays.toString(store));
        if (answer == 987654321) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
