package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/24337

    해결 방식
    left와 right중에 가장 높은 숫자를 기록을 하고 양 옆에 순서대로 빌딩을 놓아준다. => 양옆에서 보이는 것을 확인
    left가 1인 경우에만 별도로 처리한다.
 */
public class DogAndTower implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int buildingCount = inputs[0];
        int leftSight = inputs[1];
        int rightSight = inputs[2];

        List<Integer> yahoo = new ArrayList<>();

        if (leftSight + rightSight > buildingCount + 1) {
            System.out.println(-1);
            return;
        }

        for (int i = 1; i < leftSight; i++) {
            yahoo.add(i);
        }

        yahoo.add(Math.max(leftSight, rightSight));

        for (int i = rightSight - 1; i >= 1; i--) {
            yahoo.add(i);
        }

        if (leftSight == 1) {
            while (yahoo.size() < buildingCount) {
                yahoo.add(1, 1);
            }
        } else {
            while (yahoo.size() < buildingCount) {
                yahoo.add(0, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : yahoo) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
