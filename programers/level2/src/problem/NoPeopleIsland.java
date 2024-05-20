package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NoPeopleIsland {
    public int findMaps(int row, int col, String[][] input) {
        // 동서 남북으로 찾기
        int value = 0;
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{row, col});
        while (!stack.isEmpty()) {
            int[] loc = stack.pop();
            int locRow = loc[0];
            int locCol = loc[1];
            if (input[locRow][locCol].equals("X")) {
                continue;
            }
            value += Integer.parseInt(input[locRow][locCol]);
            input[locRow][locCol] = "X";
            if (locRow + 1 < input.length) {
                if (!input[locRow + 1][locCol].equals("X")) {
                    stack.add(new int[]{locRow + 1, locCol});
                }
            }
            if (locRow - 1 > -1) {
                if (!input[locRow - 1][locCol].equals("X")) {
                    stack.add(new int[]{locRow - 1, locCol});
                }
            }
            if (locCol + 1 < input[0].length) {
                if (!input[locRow][locCol + 1].equals("X")) {
                    stack.add(new int[]{locRow, locCol + 1});
                }
            }
            if (locCol - 1 > -1) {
                if (!input[locRow][locCol - 1].equals("X")) {
                    stack.add(new int[]{locRow, locCol - 1});
                }
            }
        }
        return value;
    }

    public int[] solution(String[] maps) {
        String[][] output = new String[maps.length][maps[0].length()];

        // X로 둘러싸여져 있지 않았는지 확인한다.
        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[0].length(); col++) {
                output[row][col] = String.valueOf(maps[row].charAt(col));
            }
        }

        ArrayList<Integer> tp = new ArrayList<>();
        for (int row = 0; row < output.length; row++) {
            for (int col = 0; col < output[0].length; col++) {
                //X가 아니면
                if (!output[row][col].equals("X")) {
                    tp.add(findMaps(row, col, output));
                }
            }
        }

        if (tp.isEmpty()) {
            tp.add(-1);
        }

        int[] answer = tp.stream().mapToInt(i -> i).toArray();
        Arrays.sort(answer);

        return answer;
    }

    public void init() {
        String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};

        int[] answer = solution(maps);
        System.out.println("answer = " + Arrays.toString(answer));

    }
}
