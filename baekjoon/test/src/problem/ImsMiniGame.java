package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import utils.BaekAlgoStudy;

/*
 * 문제 링크
 * https://www.acmicpc.net/problem/25757
 */
public class ImsMiniGame implements BaekAlgoStudy {

    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> gameInfo = new HashMap<>();
        gameInfo.put("Y", 1);
        gameInfo.put("F", 2);
        gameInfo.put("O", 3);

        HashSet<String> dupMember = new HashSet<>();

        String[] input = reader.readLine().split(" ");
        int line = Integer.parseInt(input[0]);
        int maxMember = gameInfo.get(input[1]);

        int answer = 0;
        int gameCount = 0;
        for (int i = 0; i < line; i++) {
            String member = reader.readLine();

            if (dupMember.contains(member)) {
                continue;
            }
            dupMember.add(member);

            gameCount += 1;

            if (gameCount == maxMember) {
                answer += 1;
                gameCount = 0;
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
