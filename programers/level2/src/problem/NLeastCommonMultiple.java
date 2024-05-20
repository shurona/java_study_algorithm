package problem;


import utils.AlgoStudy;

import java.util.Arrays;

public class NLeastCommonMultiple implements AlgoStudy {

    public int findGCD(int n, int m) {

        int minData = Math.min(n, m);

        int gcd = 0;
        for (int i = 1; i <= minData; i++) {
            if (n % i == 0 && m % i == 0) {
                gcd = i;
            }
        }

        return (n * m) / gcd;
    }

    public int solution(int[] arr) {
        int beforeLCM = 1;
        for (int i = 0; i < arr.length; i++) {
            beforeLCM = findGCD(beforeLCM, arr[i]);
        }

        return beforeLCM;
    }

    public void init() {
        int[] arr = {2, 6, 8, 14};
//        int[] arr = {3, 7, 14};
//        int[] arr = {2, 3, 4};
        int ans = solution(arr);
        System.out.println("ans = " + ans);

    }
}
