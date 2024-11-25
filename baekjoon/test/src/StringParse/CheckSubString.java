package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/16916
 */
public class CheckSubString implements BaekAlgoStudy {

    public int[] calPi(String find) {

        int[] pi = new int[find.length()];

        int left = 0;
        for (int right = 1; right < find.length(); right++) {
//            System.out.println(Arrays.toString(pi));
            // 스텝이 올라갔을 때 다른 게 나오면
            if (left > 0 && find.charAt(left) != find.charAt(right)) {
//                System.out.println("?");
                left = pi[left - 1];
            }

            // 만약 두 개의 값이 같으면 left를 하나 올려준다.
            if (find.charAt(left) == find.charAt(right)) {
                left += 1;
            }

            // 여기서 left의 값이 최대로 올라왔으면
            pi[right] = left;

        }

        return pi;
    }

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String origin = reader.readLine();
            String find = reader.readLine();

            int[] pi = calPi(find);

            System.out.println(Arrays.toString(pi));

            int count = 0;
            int pointer = 0;
            for (int left = 0; left < origin.length(); left++) {
                // 같으면 뭐를 해야 할까
                while (pointer > 0 && origin.charAt(left) != find.charAt(pointer)) {
                    pointer = pi[pointer - 1];
                }

                if (origin.charAt(left) == find.charAt(pointer)) {
                    pointer += 1;
                }

                //
                if (pointer == find.length()) {
                    count += 1;
                    pointer = pi[pointer - 1];
                    break;
                }


            }

            if (count > 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }

            reader.close();
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
