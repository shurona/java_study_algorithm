package stack;

import java.util.HashSet;
import java.util.Stack;

public class OilDrilling {
    int count = 0;
    HashSet<Integer> selected;
    public void findOil(int startRow, int startCol,int[][] land) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startRow, startCol});

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int row = pos[0];
            int col = pos[1];

            if (land[row][col] == 1) {
                land[row][col] = 0; // 방문 표시
                selected.add(col);
                count += 1;

                // 인접한 영역을 스택에 추가
                if (row + 1 < land.length && land[row + 1][col] == 1) {
                    stack.push(new int[]{row + 1, col});
                }
                if (row - 1 >= 0 && land[row - 1][col] == 1) {
                    stack.push(new int[]{row - 1, col});
                }
                if (col + 1 < land[0].length && land[row][col + 1] == 1) {
                    stack.push(new int[]{row, col + 1});
                }
                if (col - 1 >= 0 && land[row][col - 1] == 1) {
                    stack.push(new int[]{row, col - 1});
                }
            }
        }
    }


    public int solution(int[][] land) {
        int[] matchOil = new int[land[0].length];

        // 파츠를 나눈 다음
        for (int col = 0 ; col < land[0].length; col++) {
            for (int row = 0; row < land.length; row++) {
                if (land[row][col] == 1) {
                    selected = new HashSet<>();
                    count = 0;
                    findOil(row, col, land);

                    for (Integer i : selected) {
                        matchOil[i] += count;
                    }
                }
            }
        }

        // 정답 선택
        int answer = -1;
        for (int value : matchOil) {
            answer = Math.max(answer, value);
        }

        return answer;
    }

    public void yahoo() {
        int[][] land = {
                {1, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}
        };

        int[][] land1 = {
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}
        };

        int[][] land2 = {
                {0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] land3 = {
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 0, 0, 1, 0},
                {1, 1, 0, 1, 0}
        };

        int[][] land4 = {
                {1, 1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1},
                {0, 0, 1, 1, 0, 0}
        };

        int solution = solution(land);
        System.out.println("solution = " + solution);
    }
}
