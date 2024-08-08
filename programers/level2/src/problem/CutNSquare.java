package problem;

import utils.AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;

public class CutNSquare implements AlgoStudy {
    public int[] solution(int n, long left, long right) {


        int[] answer = new int[(int)(right - left + 1)];
        int startRow = (int) (left / n);
        long count = (long) startRow * n;
        int index = 0;
        for (int row = startRow + 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (count >= left && count <= right) {
                    System.out.println(row + " : " + col);
                    answer[index] = Math.max(row, col);
                    index+=1;
                }
                count +=1;
                if(count > right) {
                    break;
                }
            }
            if(count > right) {
                break;
            }

        }

        return answer;
    }

    @Override
    public void init() {
//        int n = 3;
//        int left = 2;
//        int right = 5;

        int n = 4;
        int left = 7;
        int right = 14;

        int[] solution = solution(n, left, right);
        System.out.println("정답은 : " + Arrays.toString(solution));
    }
}
