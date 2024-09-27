package MathProblem;

import utils.AlgoStudy;

public class DivideNumberCard implements AlgoStudy {

    private int findGcd(int bigNum, int smallNum) {
        if (bigNum % smallNum == 0) {
            return smallNum;
        }
        return findGcd(smallNum, bigNum%smallNum);

    }

    private int[] findGCDFromArray(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        int gcd = findGcd(Math.max(arr[0], arr[1]), Math.min(arr[0], arr[1]));
//        long lcm = ((long)arr[0] * arr[1]) / gcd;

        for (int i = 2; i < arr.length; i++) {
            gcd = findGcd(Math.max(gcd, arr[i]), Math.min(gcd, arr[i]));
//            lcm = (lcm * arr[i]) / gcd;
        }

        return new int[]{gcd, 0};
    }

    public int solution(int[] arrayA, int[] arrayB) {


        int[] gcdLcmA = findGCDFromArray(arrayA);
        int[] gcdLcmB = findGCDFromArray(arrayB);

        int gcdA = gcdLcmA[0];
//        int lcmA = gcdLcmA[1];

        int gcdB = gcdLcmB[0];
//        int lcmB = gcdLcmB[1];

        if (gcdA == 1 && gcdB == 1) {
            return 0;
        }

//        System.out.println(gcdA + " : " + lcmA);
//        System.out.println(gcdB + " : " + lcmB);

        for (int a : arrayA) {
            if (a % gcdB == 0) {
                gcdB = 0;
                break;
            }
        }

        for (int b : arrayB) {
            if (b % gcdA == 0) {
                gcdA = 0;
                break;
            }
        }

        return Math.max(gcdA, gcdB);
    }

    @Override
    public void init() {
        int solution = solution(new int[]{1}, new int[]{5, 17});
        System.out.println("정답은 : " + solution);

    }
}
