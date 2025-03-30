package backtracking;

import java.util.ArrayList;
import java.util.List;
import utils.AlgoStudy;

/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/388352



 */
public class FindSecretKey implements AlgoStudy {

    int m = 5;
    int n;
    int answer = 0;
    List<Integer> list = new ArrayList<>();
    int[] check;
    int[][] q;

    int tp = 0;

    public void dfs(int start, int step) {
        if (step == m) {
//            tp += 1;
//            if (tp > 5) {
//                return;
//            }
            for (int row = 0; row < q.length; row++) {
                int checkSum = 0;
                for (int index = 0; index < m; index++) {
                    if (list.contains(q[row][index])) {
                        checkSum += 1;
                    }
                }

//                System.out.println(list + " : " + Arrays.toString(q[row]) + " : " + checkSum);

                if (checkSum != check[row]) {
                    return;
                }

            }

//            System.out.println();
//            System.out.println(list);
            answer += 1;
            return;
        }

        for (int i = start; i <= n; i++) {
            // 모든 경우의 수를 확인한다.
            list.add(i);
//            System.out.println(list);
            dfs(i + 1, step + 1);
            list.remove(list.size() - 1);
        }
    }

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.check = ans;

        dfs(1, 0);

        return answer;
    }

    @Override
    public void init() {
        int[][] a = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {3, 7, 8, 9, 10},
            {2, 5, 7, 9, 10},
            {3, 4, 5, 6, 7}
        };
        int solution = solution(10, a, new int[]{2, 3, 4, 3, 3});
        System.out.println("정답은 : " + solution);
    }
}
