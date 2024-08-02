package greedy;

import utils.AlgoLevel3Study;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class NumberGame implements AlgoLevel3Study {

    public int solution(int[] A, int[] B) {

        HashMap<Integer, Integer> bInfo = new HashMap<>();

        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;

        int bStart = 0;
        for (int a : A) {
            for (int bIndex = bStart; bIndex < B.length; bIndex++) {
                int b = B[bIndex];
                bStart += 1;
                if (a < b) {
//                    System.out.println(a + " : " + b);
                    answer +=1;
                    break;
                }
            }
        }


        return answer;
    }

    @Override
    public void init() {
        int[] a = {5, 1, 3, 7};
        int[] b = {2, 2, 6, 8};

        int solution = solution(a, b);
        System.out.println("정답은 : " + solution);

    }
}
