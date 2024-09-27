package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.AlgoStudy;

public class HanoiTower implements AlgoStudy {

    List<List<Integer>> answer = new ArrayList<>();

    public void hanoiMove(int n, int src, int target, int middle) {

        if (n == 0) {
            return;
        }
//        System.out.println("n: " + n + " src:" + src + " target: " + target);
        hanoiMove(n - 1, src, middle, target);
        List<Integer> src1 = List.of(src, target);
        answer.add(src1);
        hanoiMove(n - 1, middle, target, src);

    }

    public int[][] solution(int n) {
//        int[][] answer = new int[][]{};
        // 짝수면 오른쪽
        if (n % 2 == 0) {
            hanoiMove(n, 1, 3, 2);
        } else {
            // 홀수면 가운데 로 시작
            hanoiMove(n, 1, 2, 3);
        }

        System.out.println(this.answer);

        return this.answer.stream().map(answer -> new int[]{answer.get(0), answer.get(1)})
            .toArray(int[][]::new);
    }

    @Override
    public void init() {

        int[][] solution = solution(2);
        System.out.println(Arrays.deepToString(solution));

    }
}
