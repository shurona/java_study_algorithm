package Hash;

import java.util.Arrays;
import java.util.HashMap;
import utils.AlgoStudy;

public class SisoMatch implements AlgoStudy {

    public long solution(int[] weights) {

        Arrays.sort(weights);
        HashMap<Double, Integer> storage = new HashMap<>();

        long answer = 0;

        double[] dp = {0.5, 1, 0.3, 0.6, 0.25, 0.75};

        for (int loc = 0; loc < weights.length; loc++) {
            double weight = weights[loc];
            for (int i = 0; i < dp.length; i++) {
                double currentRatio = dp[i];

                double findFriend;
                if (currentRatio == 0.3) {
                    findFriend = weight / 3 * 1;
                } else if (currentRatio == 0.6) {
                    findFriend = weight / 3 * 2;
                } else {
                    findFriend = weight * currentRatio;
                }

                // 친구가 있는 지 확인
                if (storage.getOrDefault(findFriend, 0) != 0) {
                    System.out.println(weight + " : " + findFriend);
                    answer += storage.get(findFriend);
                }

            }

            // 위에서 다 찾은 후 나의 정보를 넣어준다.
            Integer oo = storage.getOrDefault(weight, 0);
            storage.put(weight, oo + 1);

        }
        return answer;
    }

    @Override
    public void init() {

        long solution = solution(new int[]{100, 180, 360, 100, 270});

        System.out.println("정답은 : " + solution);
    }
}
