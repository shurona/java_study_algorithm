package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
 * 문제 링크
 * https://www.acmicpc.net/problem/1305
 */
public class AdStringCheck implements BaekAlgoStudy {

    public int[] findPattern(String data) {

        int[] pattern = new int[data.length()];

        int isSame = 0;
        for (int j = 1; j < data.length(); j++) {
            while (isSame > 0 && data.charAt(isSame) != data.charAt(j)) {
//                System.out.println(
//                    Arrays.toString(pattern) + " : " + isSame + " : " + pattern[isSame - 1]);
                isSame = pattern[isSame - 1];
            }

            if (data.charAt(isSame) == data.charAt(j)) {
                isSame += 1;
            }

            pattern[j] = isSame;
        }

        return pattern;
    }


    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int size = Integer.parseInt(reader.readLine());
            String input = reader.readLine();

            int[] pattern = findPattern(input);
//            System.out.println(Arrays.toString(pattern));

            int[] yahoo = anotherSolution(input);

            System.out.println(Arrays.toString(yahoo));
            System.out.println(yahoo[input.length() - 1]);

            int yes = 0;
            for (int i = 0; i < pattern.length; i++) {
                if (pattern[i] == 0) {
                    yes = i;
                }
            }

            for (int start = yes; start < size; start++) {
                boolean isAllSame = true;
                int loop = 0;
                for (int left = 0; left < size; left++) {

//                    System.out.println(left + " : " + loop);
//                    System.out.println(input.charAt(left) + " : " + input.charAt(loop));

                    if (input.charAt(left) != input.charAt(loop)) {
                        isAllSame = false;
                        break;
                    }

                    if (loop == start) {
                        loop = 0;
                    } else {
                        loop += 1;
                    }

                }

                if (isAllSame) {
                    System.out.println("정답은 : " + (start + 1));
                    break;
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public int[] anotherSolution(String data) {
        char[] pa = data.toCharArray();
        int[] board = new int[pa.length];

        int k = 0;
        for (int i = 1; i < pa.length; i++) {
            if (k > 0 && pa[i] != pa[k]) {
                k = board[k - 1];
            }
            if (pa[i] == pa[k]) {
                k += 1;
                board[i] = k;
            }

        }
        return board;
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
