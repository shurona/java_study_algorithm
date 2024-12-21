package Procession;

import utils.AlgoLevel3Study;

/*
 * 문제 링크
 * https://school.programmers.co.kr/learn/courses/30/lessons/60059
 */
public class LockAndKey implements AlgoLevel3Study {

    public int findZeroCount(int[][] lock) {
        int count = 0;
        for (int row = 0; row < lock.length; row++) {
            for (int col = 0; col < lock.length; col++) {
                if (lock[row][col] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public int[][] rotate(int[][] key) {

        int[][] rotate = new int[key.length][key.length];

        for (int row = 0; row < key.length; row++) {
            for (int col = 0; col < key.length; col++) {
                rotate[row][col] = key[key.length - 1 - col][row];
            }
        }

        return rotate;
    }

    public int checking(int[][] lock, int[][] key, int r, int c) {
        int n = lock.length;
        int m = key.length;

        // 시작부분
        int startRow = (m - 1) * (-1);
        int startCol = (m - 1) * (-1);

        int zeroCount = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < m; col++) {
                int lockRow = startRow + row + r;
                int lockCol = startCol + col + c;
                if (lockRow < 0 || lockCol < 0 || lockRow >= n || lockCol >= n) {
                    continue;
                }

                // 겹치는 부분
                int keyData = key[row][col];
                int lockData = lock[lockRow][lockCol];

                // 겹치면 안됌
                if (keyData + lockData == 2) {
                    return -1;
                    // 둘 다 빈 경우
                } else if (keyData + lockData == 0) {
                    return -1;
                } else if (lockData == 0 && keyData == 1) {
                    zeroCount++;
                } else {
                    //
                }
            }
        }
        return zeroCount;
    }

    public boolean solution(int[][] key, int[][] lock) {

        int zeroCount = findZeroCount(lock);
        int n = lock.length;
        int m = key.length;
        int maxSliding = n + m - 1;

        boolean answer = false;

        // sliding
        for (int i = 0; i < 4; i++) {
            for (int r = 0; r < maxSliding; r++) {
                for (int c = 0; c < maxSliding; c++) {
                    int fillBlank = checking(lock, key, r, c);

                    if (fillBlank == zeroCount) {
                        answer = true;
                    }
                }
            }
            key = rotate(key);
        }

        return answer;
    }

    @Override
    public void init() {

    }
}
