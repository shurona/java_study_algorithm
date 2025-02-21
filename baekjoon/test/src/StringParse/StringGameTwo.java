package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import utils.BaekAlgoStudy;

public class StringGameTwo implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

        String alphabet = "abcdefghijklnmopqrstuvwxyz";

        int[] dp;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ct; i++) {
            String data = reader.readLine();
            int maxCount = Integer.parseInt(reader.readLine());

            int minString = Integer.MAX_VALUE;
            int maxString = Integer.MIN_VALUE;

            dp = new int[26];

            for (int left = 0; left < data.length(); left++) {
                char currentCheckAlphabet = data.charAt(left);
                Queue<Integer> indexQueue = new LinkedList<>();
//                indexQueue.add(left);
                int dpIndex = alphabet.indexOf(currentCheckAlphabet);
                // 이미 검증한 알파벳은 패스
                if (dp[dpIndex] < 0) {
                    continue;
                }

                dp[dpIndex] = -1;

                for (int point = left; point < data.length(); point++) {
                    // 한 번 확인
                    if (data.charAt(point) == currentCheckAlphabet) {
                        indexQueue.add(point);
                        if (indexQueue.size() == maxCount) {
                            // 길이 확인
                            Integer leftIndex = indexQueue.poll();
//                            System.out.println(
//                                point + " : " + leftIndex + " : " + ());

                            minString = Math.min(point - leftIndex + 1, minString);
                            maxString = Math.max(point - leftIndex + 1, maxString);

                        }
                    }
                }
            }
            if (maxString == Integer.MIN_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(minString).append(" ").append(maxString).append("\n");
            }

        }

        System.out.println(sb);
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
