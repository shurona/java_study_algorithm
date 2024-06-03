package problem;

import utils.AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;

public class RotateProcession implements AlgoStudy {
    public int rotateTable(int leftRow, int leftCol, int rightRow, int rightCol, int[][] tables) {

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int nextRow = leftRow;
        int nextCol = leftCol;
        int leftMidTemp = tables[leftRow][leftCol];;
        int midMidTemp;
        int min = tables[leftRow][leftCol];
        for (int i = 0; i < 4; i++) {
            while (true) {
                if (i == 0 && nextCol == rightCol) {
                    break;
                } else if (i == 1 && nextRow == rightRow) {
                    break;
                } else if (i == 2 && nextCol == leftCol) {
                    break;
                } else if (i == 3 && nextRow == leftRow) {
                    break;
                }
                midMidTemp = tables[nextRow + dy[i]][nextCol + dx[i]];
                tables[nextRow + dy[i]][nextCol + dx[i]] = leftMidTemp;
                leftMidTemp = midMidTemp;
                min = Math.min(min, midMidTemp);

                nextCol = nextCol + dx[i];
                nextRow = nextRow + dy[i];

            }

        }


        return min;
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] tables = new int[rows][columns];

        // 테이블을 만든다.
        int indexData = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                tables[r][c] = indexData;
                indexData++;
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int[] query : queries) {
            int output = rotateTable(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1, tables);
            for (int[] tab : tables) {
                System.out.println(Arrays.toString(tab));
            }
            answer.add(output);
        }

        return answer.stream().mapToInt(data -> data).toArray();
    }

    public void init() {
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        int rows = 6;
        int col = 6;

        int[] tp = solution(rows, col, queries);
        System.out.println(Arrays.toString(tp));

    }
}
