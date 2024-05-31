package problem;

import utils.AlgoStudy;

import java.util.Arrays;

public class MultipleOfProcession implements AlgoStudy {
    public int[][] calculateMultiple(int[][] arr1, int[][] arr2) {
        System.out.println(Arrays.deepToString(arr1));
        int[][] answer = new int[arr1.length][arr2[0].length];
        for (int row = 0; row < answer.length; row++) {
            for (int col = 0; col < answer[0].length; col++) {
                int sum = 0;
                for (int index = 0; index < arr2.length; index++) {
                    sum += arr1[row][index] * arr2[index][col];
                    System.out.println(arr1[row][index] + " * " + arr2[index][col]);
                }
                System.out.println();
                answer[row][col] = sum;
            }
        }
        return answer;
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {

        int oneRow = arr1.length;
        int oneCol = arr1[0].length;

        int twoRow = arr2.length;
        int twoCol = arr2[0].length;

        if (oneCol == twoRow) {
            return calculateMultiple(arr1, arr2);
        } else {
            System.out.println("여기");
            return calculateMultiple(arr2, arr1);
        }
    }

    @Override
    public void init() {
//        int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
//
//        int[][] arr2 = {{3, 3}, {3, 3}};

//        int[][] arr1 = {{1,2}, {2,1}};
//        int[][] arr2 = {{1, 1, 1, 1}, {2, 2, 2, 2}};

        int[][] arr1 = {{1, 2, 3}, {3, 2, 1}};

        int[][] arr2 = {{1, 2}, {2, 1}, { 1, 2}};

        System.out.println(Arrays.deepToString(solution(arr1, arr2)));
    }
}
