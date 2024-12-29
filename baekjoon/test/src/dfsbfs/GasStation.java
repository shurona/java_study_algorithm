package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

public class GasStation implements BaekAlgoStudy {

    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int cityCount = Integer.parseInt(reader.readLine());

        int[] distance = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

//        int totalDistance = Arrays.stream(distance).reduce(0, (x, y) -> Integer.sum(x, y));

        int[] gasCostList = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

        long answer = 0;
        int currentLoc = 0;
        while (currentLoc < cityCount - 1) {
            // 하나씩 앞을 보면서 자신보다 낮은 부분이 생기면 멈춘다.
            int currentGasCost = gasCostList[currentLoc];
            int totalDistance = 0;
            for (int i = currentLoc; i < cityCount; i++) {
                int gasCost = gasCostList[i];
//                System.out.println(currentGasCost + " : " + gasCost);
                if (gasCost < currentGasCost) {
                    currentLoc = i;
                    break;
                }
                // 끝까지 도착
                if (i >= distance.length) {
                    currentLoc = i;
                    break;
                }
                totalDistance += distance[i];
            }
            System.out.println(currentGasCost + " : " + totalDistance);
            answer += ((long) currentGasCost * totalDistance);

        }

        System.out.println(answer);
        reader.close();


    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
