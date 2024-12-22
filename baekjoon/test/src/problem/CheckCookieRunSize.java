package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

public class CheckCookieRunSize implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());

        String[][] board = new String[size][size];

        int heartRow = 0;
        int heartCol = 0;
        boolean checkHead = false;
        for (int i = 0; i < size; i++) {
            String[] line = reader.readLine().split("");
            board[i] = line;

            if (!checkHead) {

                for (int col = 0; col < line.length; col++) {
                    if (line[col].equals("*")) {
                        heartRow = i + 1;
                        heartCol = col;
                        checkHead = true;
                    }
                }
            }
        }

        int leftArm = 0;
        for (int col = heartCol - 1; col >= 0; col--) {
            if (board[heartRow][col].equals("_")) {
                break;
            }
            leftArm += 1;
        }

        int rightArm = 0;
        for (int col = heartCol + 1; col < size; col++) {
            if (board[heartRow][col].equals("_")) {
                break;
            }
            rightArm += 1;
        }

        int legStartRow = 0;

        int waistLen = 0;
        for (int row = heartRow + 1; row < size; row++) {
            if (board[row][heartCol].equals("_")) {
                legStartRow = row;
                break;
            }
            waistLen += 1;
        }

        int leftLeg = 0;
        for (int row = legStartRow; row < size; row++) {
            if (board[row][heartCol - 1].equals("_")) {
                break;
            }
            leftLeg += 1;
        }

        int rightLeg = 0;
        for (int row = legStartRow; row < size; row++) {
            if (board[row][heartCol + 1].equals("_")) {
                break;
            }
            rightLeg += 1;
        }

        System.out.println((heartRow + 1) + " " + (heartCol + 1));
        System.out.println(
            leftArm + " " + rightArm + " " + waistLen + " " + leftLeg + " " + rightLeg);

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
