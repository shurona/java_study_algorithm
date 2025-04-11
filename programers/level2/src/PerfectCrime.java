import java.util.Arrays;
import utils.AlgoStudy;

/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/389480

    문제 풀이

 */
public class PerfectCrime implements AlgoStudy {

    public int solution(int[][] info, int n, int m) {
        int answer = 987654321;

        int[][] dp = new int[info.length][m];

        for (int[] tp : dp) {
            Arrays.fill(tp, 987654321);
        }

        // 초기값을 설정해준다.
        dp[0][0] = info[0][0]; // a가 선택된 경우
        if (info[0][1] < m) {
            dp[0][info[0][1]] = 0; // b가 선택된 경우, a가 임계점 이상이면 기록하지 않는다.
        }
//

        for (int i = 1; i < info.length; i++) {
            int a = info[i][0];
            int b = info[i][1];

            // dp에 A를 골랐을 때와 B를 골랐을 때의 최솟값으로 저장해준다.
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                if (j + b < m) {
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }

        for (int[] tp : dp) {
            System.out.println(Arrays.toString(tp));
        }

        for (int i = 0; i < m; i++) {
            answer = Math.min(answer, dp[dp.length - 1][i]);
        }

        return answer >= n ? -1 : answer;
    }

    @Override
    public void init() {
        int[][] array = {
            {1, 2},
            {2, 3},
            {2, 1}
        };

//        int[][] array = {
//            {1, 2},
//            {2, 3},
//            {2, 1}
//        };

//        int[][] array = {
//            {3, 3},
//            {3, 3}
//        };

        int solution = solution(array, 4, 4);
        System.out.println("정답은 : " + solution);
    }
}
