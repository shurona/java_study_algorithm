package Hash;

import utils.AlgoStudy;

import java.util.*;

public class VisitLength implements AlgoStudy {
    private static class AfterLocation {
        int row;
        int col;
        boolean isMove;

        public AfterLocation(int row, int col, boolean isMove) {
            this.row = row;
            this.col = col;
            this.isMove = isMove;
        }
    }

    private boolean checkYahoo(HashMap<String, Boolean> dp, int row, int col, int afterRow, int afterCol) {
        int[] ft = {col, row};
        int[] at = {afterCol, afterRow};

        int[][] merge = {ft, at};

        Arrays.sort(merge, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        String str = Arrays.deepToString(merge);
        Boolean isOn = dp.getOrDefault(str, false);
        dp.put(str, true);
        return isOn;
    }

    private AfterLocation step(HashMap<String, Boolean> dp, int row, int col, char dir) {

        boolean isMove = true;

        int afterRow;
        int afterCol;
        switch(dir) {
            case 'U':
                afterRow = Math.min(row + 1, 5);
                afterCol = col;
                if (afterRow == row || checkYahoo(dp, row, col, afterRow, afterCol)) {
//                    afterRow = row;
                    isMove = false;
                }
                break;

            case 'D':
                afterRow = Math.max(row - 1, -5);
                afterCol = col;
                if (afterRow == row || checkYahoo(dp, row, col, afterRow, afterCol)) {
//                    afterRow = row;
                    isMove = false;
                }
                break;

            case 'R':
                afterRow = row;
                afterCol = Math.min(col + 1, 5);
                if (afterCol == col || checkYahoo(dp, row, col, afterRow, afterCol)) {
                    isMove = false;
                }
                break;

            case 'L':
                afterRow = row;
                afterCol = Math.max(col - 1, -5);
                if (afterCol == col || checkYahoo(dp, row, col, afterRow, afterCol)) {
//                    afterCol = col;
                    isMove = false;
                }
                break;

            default:
                return null;

        }

        return new AfterLocation(afterRow, afterCol, isMove);
    }

    public int solution(String dirs) {

        HashMap<String, Boolean> dp = new HashMap<>();
        int row = 0;
        int col = 0;

        int answer = 0;
        for (int i = 0; i < dirs.length(); i++) {
            AfterLocation step = step(dp, row, col, dirs.charAt(i));
            // 점 두 개를 확인
            row = step.row;
            col = step.col;

//            System.out.println(step.col + "," + step.row + "," + step.isMove);

            if (step.isMove) {
                answer += 1;
            }

        }

        return answer;
    }

    @Override
    public void init() {
        String dirs = "ULURRDLLU";

        int solution = solution(dirs);
        System.out.println(solution);
    }
}
