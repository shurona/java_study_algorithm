package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

public class TikTakTok implements BaekAlgoStudy {

    static int findCrossCheck(String[][] arr, String gizun) {

        int output = 0;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (arr[i][i].equals(gizun)) {
                count += 1;
            }
        }
        if (count == 3) {
            output += 1;
        }

        count = 0;
        for (int i = 0; i < 3; i++) {
            if (arr[i][2 - i].equals(gizun)) {
                count += 1;
            }
        }
        if (count == 3) {
            output += 1;
        }

        return output;
    }

    static int findColCorrect(String[][] arr, String gizun) {
        int output = 0;
        for (int col = 0; col < 3; col++) {
            int count = 0;
            for (int row = 0; row < 3; row++) {
                if (arr[row][col].equals(gizun)) {
                    count += 1;
                }
            }
            if (count == 3) {
                output += 1;
            }
        }

        return output;
    }

    static int findRowCorrect(String[][] arr, String gizun) {
        int output = 0;
        for (int row = 0; row < 3; row++) {
            int count = 0;
            for (int col = 0; col < 3; col++) {
                if (arr[row][col].equals(gizun)) {
                    count += 1;
                }
            }
            if (count == 3) {
                output += 1;
            }
        }

        return output;
    }

    static boolean findTikTakTok(String[][] arr, int xCount, int oCount) {

//        for (String[] strings : arr) {
//            System.out.println(Arrays.toString(strings));
//        }

        // 3개가 연속인게 하나만 있어야 한다.
        int xLine = 0;
        int oLine = 0;
        oLine += findColCorrect(arr, "O");
        xLine += findColCorrect(arr, "X");

        oLine += findRowCorrect(arr, "O");
        xLine += findRowCorrect(arr, "X");

        oLine += findCrossCheck(arr, "O");
        xLine += findCrossCheck(arr, "X");
        // X의 갯수가 O보다 하나 더 많아야 한다.

//        System.out.println(oLine + " : " + xLine);

        // 하나의 경우의 수
        if (xCount == 5 && oCount == 4 && xLine == 2 && oLine == 0) {
            return true;
        }

        // 완성 된 것이 여러개인 경우
        if (xLine + oLine > 1) {
            return false;
        }

        // x의 갯수가 더 많은데 0가 완성되어 있는 경우
        if (xCount > oCount && oLine == 1) {
            return false;
        }

        if (xCount == oCount && xLine == 1) {
            return false;
        }

        // 다 안찼는데 완성이 안된 경우
        if ((xCount + oCount < 9) && (xLine + oLine == 0)) {
            return false;
        }

        return true;
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[][] store = new String[3][3];

        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = reader.readLine();
            if (s.equals("end")) {
                break;
            }

            store = new String[3][3];
            int index = 0;
            int oCount = 0;
            int xCount = 0;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    String tp = Character.toString(s.charAt(index));
                    if (tp.equals("X")) {
                        xCount += 1;
                    } else if (tp.equals("O")) {
                        oCount += 1;
                    }
                    store[row][col] = tp;
                    index += 1;
                }
            }

            boolean output;
            if (oCount == xCount || xCount == oCount + 1) {
                output = findTikTakTok(store, xCount, oCount);
            } else {
                output = false;
            }

            if (output) {
                sb.append("valid");
            } else {
                sb.append("invalid");
            }

//            System.out.println();
            sb.append("\n");


        }

        System.out.println(sb);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
