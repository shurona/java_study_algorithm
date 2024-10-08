package problem;

import utils.AlgoStudy;

public class TicTacTok implements AlgoStudy {

    private boolean checkCompletion(String[] board, char O) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == O) {
                if (board[i].charAt(1) == O && board[i].charAt(2) == O) {
                    return true;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == O) {
                if (board[1].charAt(i) == O && board[2].charAt(i) == O) {
                    return true;
                }
            }
        }

        if (board[0].charAt(0) == O && board[1].charAt(1) == O && board[2].charAt(2) == O) {
            return true;
        }

        if (board[0].charAt(2) == O && board[1].charAt(1) == O && board[2].charAt(0) == O) {
            return true;
        }

        return false;
    }

    public int solution(String[] board) {
        int answer = 1;

        char O = 'O';
        char X = 'X';

        int oCount = 0;
        int xCount = 0;

        for (String row : board) {
            for (int col = 0; col < row.length(); col++) {
                if (row.charAt(col) == O) {
                    oCount++;
                } else if (row.charAt(col) == X) {
                    xCount++;
                }

            }
        }

        // X의 갯수가 O보다 많은 경우
        if (xCount > oCount || (oCount - xCount > 1)) {
            return 0;
        }

        if (checkCompletion(board, O) && checkCompletion(board, X)) {
            return 0;
        }

        // 완성되었는지
        if (xCount == oCount && checkCompletion(board, O)) {
            return 0;
        }

        if (oCount > xCount && checkCompletion(board, X)) {
            return 0;
        }

        return answer;
    }

    @Override
    public void init() {

//        String[] arr = {"O.X", ".O.", "..X"};
//        String[] arr = {"...", "...", "..."};
//        String[] arr = {"OOO", "...", "XXX"};
//        String[] arr = {"...", "...", "..."};
        String[] arr = {"OO.", "XXX", "OO."};

        int solution = solution(arr);
        System.out.println("정답은 : " + solution);

    }
}
