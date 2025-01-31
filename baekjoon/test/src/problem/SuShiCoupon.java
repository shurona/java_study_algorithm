package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import utils.BaekAlgoStudy;

public class SuShiCoupon implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        HashSet<Integer> store = new HashSet<>();
        int[] dp = new int[firstLine[1] + 1];
        dp[firstLine[3]] += 1; // 쿠폰 번호는 미리 올려놓는다.
        store.add(firstLine[3]);

        int pickSize = firstLine[2];
        int sushiLength = firstLine[0];

        int[] belt = new int[sushiLength];
        for (int i = 0; i < pickSize; i++) {
            int sushi = Integer.parseInt(reader.readLine());
            store.add(sushi);
            dp[sushi] += 1;
            belt[i] = sushi;
        }

//        System.out.println(store);
        int step = 1;
        int left = step;
        int right = step + pickSize - 1;
        int answer = store.size();
        while (step < sushiLength - pickSize + 1) {

            left = step % sushiLength;
            right = (step + pickSize - 1) % sushiLength;

            int sushi = Integer.parseInt(reader.readLine());
            dp[sushi] += 1;
            belt[right] = sushi;
            store.add(sushi);

            // 여기서 빠진 만큼 내린다
            int deleteIndex = left - 1 < 0 ? belt.length - 1 : left - 1;
            int deleteSushi = belt[deleteIndex];
            if (dp[deleteSushi] == 1) {
                store.remove(deleteSushi);
            }

            dp[deleteSushi] -= 1;

//            System.out.println(deleteIndex + " : " + left + " : " + right);
//            System.out.println(store);
            answer = Math.max(answer, store.size());

            step++;
//            System.out.println(step + " : " + sushiLength);
        }

        for (int i = 0; i <= pickSize; i++) {
            left = step % sushiLength;
            right = (step + pickSize - 1) % sushiLength;

            int sushi = belt[right];
            dp[sushi] += 1;
            belt[right] = sushi;
            store.add(sushi);

            int deleteIndex = left - 1 < 0 ? belt.length - 1 : left - 1;

            int deleteSushi = belt[deleteIndex];
            if (dp[deleteSushi] == 1) {
                store.remove(deleteSushi);
            }

            dp[deleteSushi] -= 1;

//            System.out.println(left + " : " + right);
//            System.out.println(store);
            answer = Math.max(answer, store.size());
            step++;

        }

//        System.out.println(Arrays.toString(belt));

        System.out.println(answer);
        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
