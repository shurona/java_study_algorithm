package problem;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.HashMap;

public class QuadCompression implements AlgoStudy {
    HashMap<Integer, Integer> store = new HashMap<>();
    public int checkNumberInDiv(int[][] arr, int startRow, int startCol, int length) {
        // 점검한다.
        if (length == 2) {
            int leftUp = arr[startRow][startCol];
            int rightUp = arr[startRow + 1][startCol];
            int leftDown = arr[startRow][startCol + 1];
            int rightDown = arr[startRow + 1][startCol + 1];
            if (leftUp == 1 && rightUp == 1 && leftDown == 1 && rightDown == 1) {

                return 1;
            } else if (leftUp == 0 && rightUp == 0 && leftDown == 0 && rightDown == 0) {

                return 0;
            } else {
                this.store.put(leftUp, this.store.get(leftUp) + 1);
                this.store.put(rightUp, this.store.get(rightUp) + 1);
                this.store.put(leftDown, this.store.get(leftDown) + 1);
                this.store.put(rightDown, this.store.get(rightDown) + 1);

                return -1;
            }
        }

        int currentLength = length / 2;

        // 왼쪽 위
        int lu = checkNumberInDiv(arr, startRow, startCol, currentLength);
        // 오른쪽 위
        int ru = checkNumberInDiv(arr, startRow + currentLength, startCol, currentLength);
        // 왼쪽 아래
        int ld = checkNumberInDiv(arr, startRow, startCol + currentLength, currentLength);
        // 오른쪽 아래
        int rd = checkNumberInDiv(arr, startRow + currentLength, startCol + currentLength, currentLength);

        // 올라온 값 중에서 다 같은지 확인
        if (lu == 1 && ru == 1 && ld == 1 && rd == 1) {
            return 1;
        } else if (lu == 0 && ru == 0 && ld == 0 && rd == 0) {
            return 0;
        } else {
            if (lu != -1) {
                this.store.put(lu, this.store.get(lu) + 1);
            }
            if (ru != -1) {
                this.store.put(ru, this.store.get(ru) + 1);
            }
            if (ld != -1) {
                this.store.put(ld, this.store.get(ld) + 1);
            }
            if (rd != -1) {
                this.store.put(rd, this.store.get(rd) + 1);
            }

            return -1;
        }

    }

    public int[] solution(int[][] arr) {
        int[] answer = {};

        store.put(1, 0);
        store.put(0, 0);

        // div and conquer
        // 절반씩 나눠서 확인한다.
        // 7 -> 3

        if (arr.length < 2) {
            int[] tp = new int[2];
            tp[arr[0][0]] += 1;
            return tp;

        }

        int i = checkNumberInDiv(arr, 0, 0, arr.length);

        if (i != -1) {
            this.store.put(i, this.store.get(i) + 1);
        }

        return new int[]{this.store.get(0), this.store.get(1)};
    }

    @Override
    public void init() {

        int[][] data = {
                {1, 1, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        };

        int[][] data2 = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1}
        };

        int[][] data3 = {
                {0, 0},
                {0, 0}
        };

        int[] solution = solution(data3);
        System.out.println("solution = " + Arrays.toString(solution));

        System.out.println("QuadCompression.init");
    }
}
