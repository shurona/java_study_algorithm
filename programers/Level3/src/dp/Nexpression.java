package dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Nexpression {
    List<HashSet<Integer>> dp;
    private void addSave(int n, int count) {
        for(int start = 1; start < count; start++){
            // n - 1 하고 n하고 계산
            for (Integer dp2 : dp.get(start)) {
                for (Integer dp1 : dp.get(count - start)) {
                    // 사칙연산
                    dp.get(count).add(dp2 + dp1);
                    if (dp2 - dp1 >= 0) {
                        dp.get(count).add(dp2 - dp1);
                    }
                    dp.get(count).add(dp2 * dp1);
                    if (dp1 > 0) {
                        dp.get(count).add(dp2/dp1);
                    }
                }
            }
        }
        // n개 넣어주기
        StringBuilder str = new StringBuilder();
        for (int l = 0; l < count; l++) {
            str.append(n);
        }
        dp.get(count).add(Integer.valueOf(str.toString()));
    }

    public void solution() {

        int N = 5;
        int number = 12;
        int answer = -1;

        // 최소값이 8 이하

        // 같으면 끝
        if (N == number) {
            System.out.println("같아요");
        }

        dp = new ArrayList<>();
        dp.add(new HashSet<>());

        // 작은 연산으로 구하는 것
        // 같은 연산값들은 중복된다
        // 저장을 어떻게 해야 할 까

        for (int i = 1; i < 9; i++) {
            dp.add(new HashSet<>());
            addSave(N, i);
            if (dp.get(i).contains(number)) {
                answer = i;
                break;
            }

        }
        System.out.println("answer = " + answer);
        System.out.println(dp.get(4));
        System.out.println("Nexpression.solution");
    }
}
