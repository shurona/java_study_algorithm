package problem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MakeMinData {
    int output = Integer.MAX_VALUE;
    void permutation(int[] A, int[] B, boolean[] visit, int[] af,int ct) {

        if (ct == visit.length) {
            int ot = 0;
            for (int loop = 0; loop < af.length; loop++) {
                // 하나 씩
                ot += A[loop] * B[af[loop]];

            }
            this.output = Math.min(ot, this.output);

            return;
        }

        for (int i = 0; i < visit.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                af[i] = ct;
                permutation(A, B,visit, af, ct  + 1);
                visit[i] = false;
            }
        }
    }

    // A와 B의 하나씩의 곱을 더한 값의 최솟
    public int solution(int []A, int []B)
    {
        int answer = 0;
        System.out.println("Hello Java");

        int[] af = new int[A.length];

//        boolean[] visit = new boolean[A.length];

        Arrays.sort(A);

        Arrays.sort(B);

        int min = 0;
        for (int index = 0; index < A.length; index++) {
            min += A[index] * B[B.length - 1 - index];
        }



//        permutation(A, B, visit, af, 0);

//        int minData = Integer.MAX_VALUE;
//        for (int index = 0; index < per.size(); index++) {
//            int[] data = per.get(index);
//            int ot = 0;
//            for (int loop = 0; loop < data.length; loop++) {
//                // 하나 씩
//                ot += A[loop] * B[data[loop]];
//
//            }
//            minData = Math.min(ot, minData);
//        }
        return output;
    }

    public void init() {
        int[] A = {1, 4, 2};
        int[] B = {5, 4, 4};

        int answer = solution(A, B);
        System.out.println("answer = " + answer);

    }
}
