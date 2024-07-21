package problem;

import utils.BaekAlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestWithDistance implements BaekAlgoStudy {

    public void solution() {

        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            String input = bfr.readLine();

            String[] div = input.split(" ");

            int row = Integer.parseInt(div[0]);
            int col = Integer.parseInt(div[1]);
            int rowDistance = Integer.parseInt(div[2]);
            int colDistance = Integer.parseInt(div[3]);

            int rrr = 1;
            int rowCount = 0;
            for (int r = 1; r < row; r++) {
                rowCount +=1;
                if (rowCount > rowDistance) {
                    rowCount = 0;
                    rrr +=1;
                }
            }

            int ccc = 1;
            int colCount = 0;
            for (int c = 1; c < col; c++) {
                colCount +=1;
                if (colCount > colDistance) {
                    colCount = 0;
                    ccc +=1;
                }
            }

            System.out.println(ccc * rrr);

            bfr.close();
        } catch (Exception e) {
            return;
        }
    }


    @Override
    public void init() throws IOException {
        solution();
    }
}
