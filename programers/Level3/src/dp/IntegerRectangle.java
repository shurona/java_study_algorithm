package dp;

import java.util.*;

public class IntegerRectangle {
    public void solution() {
        //
        System.out.println("IntegerRectangle.solution");

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        int[][] sumTriangle = new int[triangle.length][];

        // 거쳐간 숫자의 합이 가장 큰 경우
        // 첫번째 줄 부터 아래로 합을 기록한다.

        int index = 1;

        sumTriangle[0] = new int[]{triangle[0][0]};
        int checkMax = 0;
        for (int i = 1; i <triangle.length; i++) {
            int[] triNumbers = triangle[i];
            sumTriangle[index] = new int[triNumbers.length];
            for (int triIndex = 0; triIndex < triNumbers.length; triIndex++) {
                if (triIndex == 0) {
                    sumTriangle[index][triIndex] = sumTriangle[index-1][triIndex] + triNumbers[triIndex];
//                    if (i == triangle.length - 1) {
//                        checkMax = Math.max(sumTriangle.get(index - 1).get(0) + triNumbers[0], checkMax);
//                    }
                } else if (triIndex == triNumbers.length - 1) {
                    sumTriangle[index][triIndex] = sumTriangle[index-1][triIndex-1] + triNumbers[triIndex];
//                    if (i == triangle.length - 1) {
//                        checkMax = Math.max(sumTriangle.get(index - 1).get(triIndex - 1) + triNumbers[triIndex], checkMax);
//                    }
                } else {
                    int leftValue = sumTriangle[index -1][triIndex - 1];
                    int rightValue = sumTriangle[index -1][triIndex];

                    sumTriangle[index][triIndex] = (Math.max(leftValue + triNumbers[triIndex], rightValue + triNumbers[triIndex]));
//                    if (i == triangle.length - 1) {
//                        checkMax = Math.max(Math.max(leftValue + triNumbers[triIndex], rightValue + triNumbers[triIndex]), checkMax);
//                    }
                }
            }
            index += 1;
        }

        OptionalInt max = Arrays.stream(sumTriangle[index - 1]).max();
        int answer = max.getAsInt();
        System.out.println("answer = " + answer);

    }
}
