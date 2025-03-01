package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/22251
 */
public class VillainHoseok implements BaekAlgoStudy {


    static int[] calDisplayArray(int x, int k) {
        int[] output = new int[k];
        int copyX = x;
        for (int i = 0; i < k; i++) {
            output[k - 1 - i] = copyX % 10;
            copyX /= 10;
        }

        return output;
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        Map<String, Integer> intString = new HashMap<>();

        int n = input[0]; // 최대 층수
        int k = input[1]; // 보일 자릿수
        int p = input[2]; // 반전 시키는 개수
        int x = input[3]; // 멈추는 위치

        int[][] store = new int[10][7];
        store[0] = new int[]{1, 1, 1, 0, 1, 1, 1};
        store[1] = new int[]{0, 0, 1, 0, 0, 1, 0};
        store[2] = new int[]{1, 0, 1, 1, 1, 0, 1};
        store[3] = new int[]{1, 0, 1, 1, 0, 1, 1};
        store[4] = new int[]{0, 1, 1, 1, 0, 1, 0};
        store[5] = new int[]{1, 1, 0, 1, 0, 1, 1};
        store[6] = new int[]{1, 1, 0, 1, 1, 1, 1};
        store[7] = new int[]{1, 0, 1, 0, 0, 1, 0};
        store[8] = new int[]{1, 1, 1, 1, 1, 1, 1};
        store[9] = new int[]{1, 1, 1, 1, 0, 1, 1};

        // 차이나는 만큼 저장할 배열
        int[][] diff = new int[10][10];

        // 계산
        for (int base = 0; base < store.length; base++) {
            for (int target = 0; target < store.length; target++) {
                if (base == target) {
                    continue;
                }
                int diffBit = 0;
                for (int col = 0; col < store[0].length; col++) {
                    if (store[base][col] != store[target][col]) {
                        diffBit += 1;
                    }
                }
                diff[base][target] = diffBit;
            }
        }

        for (int[] ints : diff) {
            System.out.println(Arrays.toString(ints));
        }

        int[] original = calDisplayArray(x, k);
        int answer = 0;
        for (int target = 0; target <= n; target++) {
            if (target == x) {
                continue;
            }
            int[] targetDisplay = calDisplayArray(target, k);
            int diffCount = 0;
            for (int i = 0; i < targetDisplay.length; i++) {
                diffCount += diff[original[i]][targetDisplay[i]];
            }

            if (diffCount <= p) {
                System.out.println(
                    Arrays.toString(original) + " : " + Arrays.toString(targetDisplay) + " : "
                        + diffCount);
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
