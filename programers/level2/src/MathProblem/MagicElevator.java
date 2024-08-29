package MathProblem;

import utils.AlgoStudy;

public class MagicElevator implements AlgoStudy {
    public int solution(int storey) {

        int count = 0;
        int leftAnswer = 0;
        int leftStorey = storey;
        while (leftStorey > 0) {
            int mod = leftStorey % 10;
            leftStorey /= 10;

            if (mod < 5) {
                leftAnswer += mod;
            } else if (mod == 5) {
                if (leftStorey % 10 >= 5) {
                    leftAnswer += 10 - mod;
                    leftStorey += 1;
                } else {
                    leftAnswer += mod;
                }
            } else {
                leftAnswer += 10 - mod;
                leftStorey += 1;
            }
            count++;
        }

//        int rightAnswer = 0;
//        int rightStorey = storey;
//        while (count > 0) {
//            count--;
//            int pow = (int) Math.pow(10, count);
//            int mod = (int) (rightStorey / pow);
//
//            System.out.println(count + " : " + rightStorey);
//            if (count > 0 && mod >= 5) {
//                rightStorey = (pow * 10) - rightStorey;
//                rightAnswer += 1;
//                count++;
//                continue;
//            }
////
////            if (count > 0 && mod == 5) {
////                int underPow = (int) Math.pow(10, count - 1);
////                int underMod = (rightStorey % pow) / underPow;
//////                System.out.println("?? : " + count + " : " + (rightStorey % pow));
////                if (underMod == 5) {
////                    rightStorey = (pow * 10) - rightStorey;
////                    rightAnswer += 1;
////                    count++;
////                    continue;
////                }
////            }
//
//            rightStorey = rightStorey % pow;
//
////            System.out.println(mod + " : " + rightStorey);
//            rightAnswer += mod;
//        }

        return leftAnswer;
    }

    @Override
    public void init() {
        int solution = solution(555);
        System.out.println("정답은 : " + solution);
    }
}
