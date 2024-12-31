package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
 * 문제 링크
 * https://www.acmicpc.net/problem/2110
 */
public class EfficientRouter implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(reader.readLine().split(" ")).mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int routerCount = array[1];
        int houseCount = array[0];

        int[] houseLoc = new int[houseCount];
        for (int i = 0; i < houseCount; i++) {
            houseLoc[i] = Integer.parseInt(reader.readLine());

        }

        // 정렬
        Arrays.sort(houseLoc);

        System.out.println(Arrays.toString(houseLoc));
        int minDistance = 1;
        int maxDistance = houseLoc[houseCount - 1];

//        System.out.println(minDistance + " : " + maxDistance);

        int answer = 0;

        while (minDistance <= maxDistance) {
            int routerOn = 1;
            int middle = (minDistance + maxDistance) / 2;
            int beforeHouseLoc = houseLoc[0];
            for (int i = 1; i < houseCount; i++) {
                // 현재 위치하고 이전 위치를 비교 한다.
                int checkDistance = houseLoc[i] - beforeHouseLoc;
                if (checkDistance >= middle) {
                    beforeHouseLoc = houseLoc[i];
                    routerOn += 1;
                }
            }

            if (routerCount > routerOn) {
                maxDistance = middle - 1;
            } else {
                System.out.println(middle + " : " + routerOn);
                answer = Math.max(middle, answer);
                minDistance = middle + 1;
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
