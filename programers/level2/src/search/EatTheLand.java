package search;

import utils.AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class EatTheLand implements AlgoStudy {

    int solution(int[][] land) {
        int answer = 0;

        // 현재 라인의

        // 이전과 같은 열을 밟을 수 없으며 최대 값
        for (int i = 1; i < land.length; i++) {
            for (int col = 0; col < 4; col++) {
                int currentColMaxValue = land[i][col];
                for (int beforeCol = 0; beforeCol < 4; beforeCol++) {
                    if (beforeCol == col) {
                        continue;
                    }
                    currentColMaxValue = Math.max(currentColMaxValue, land[i - 1][beforeCol] + land[i][col]);
                }
                land[i][col] = currentColMaxValue;
            }
        }

        for (int i : land[land.length - 1]) {
            answer = Math.max(i, answer);
        }

        return answer;
    }
    @Override
    public void init() {

//        [[1,2,3,5],[5,6,7,8],[4,3,2,1]]
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
//        int[][] land = {{1, 2, 3, 4}, {1, 1, 1, 100}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        System.out.println(solution(land));
    }
}
