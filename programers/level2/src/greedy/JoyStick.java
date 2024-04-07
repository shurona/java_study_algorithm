package greedy;

import java.util.Arrays;

public class JoyStick {
    private int finalSum = 10000000;
    static class Temp {
        int moveCount;
        int selectedIndex;

        public Temp(int moveCount, int selectedIndex) {
            this.moveCount = moveCount;
            this.selectedIndex = selectedIndex;
        }

        public int getMoveCount() {
            return moveCount;
        }

        public int getSelectedIndex() {
            return selectedIndex;
        }
    }
    public Temp findNearFalse(int currentIndex, boolean[] isComplete, boolean isLeft) {

        int moveCount = 0;
        int selectedIndex = -1;
        int leftIndex = currentIndex;
        int rightIndex = currentIndex;

        while (moveCount < isComplete.length) {
            moveCount +=1;

            //right index
            rightIndex = (rightIndex + 1) % isComplete.length;

            // left index
            leftIndex = (leftIndex - 1 + isComplete.length) % isComplete.length;

            // 오른쪽으로 간 경우
            if (!isComplete[rightIndex] && !isLeft) {
                selectedIndex = rightIndex;
                break;
            }

            // 왼쪽으로 간 경우
            if (!isComplete[leftIndex] && isLeft) {
                selectedIndex = leftIndex;
                break;
            }
        }

        return new Temp(moveCount, selectedIndex);
    }

    public int findAlphabet(char alpha) {
        int a;
        if (alpha < 'N') {
            a = alpha - 'A';
        } else {
            a = 'Z' - alpha + 1;
        }
        return a;
    }

    public void dfs(String name, boolean[] isComplete, int start, int sum) {
        if (start < 0) {
            this.finalSum = Math.min(sum, this.finalSum);
            return;
        }

        char c = name.charAt(start);
        int button = findAlphabet(c);
        sum += button;

        Temp leftTemp = findNearFalse(start, isComplete, true);
        // 왼쪽
        if (leftTemp.selectedIndex > 0) {
            isComplete[leftTemp.selectedIndex] = true;
            dfs(name, isComplete, leftTemp.selectedIndex, sum + leftTemp.moveCount);
            isComplete[leftTemp.selectedIndex] = false;
        } else{
            dfs(name, isComplete, leftTemp.selectedIndex, sum);
        }

        // 오른쪽
        Temp rightTemp = findNearFalse(start, isComplete, false);
        if (rightTemp.selectedIndex > 0) {
            isComplete[rightTemp.selectedIndex] = true;
            dfs(name, isComplete, rightTemp.selectedIndex, sum + rightTemp.moveCount);
            isComplete[rightTemp.selectedIndex] = false;
        } else {
            dfs(name, isComplete, rightTemp.selectedIndex, sum);
        }
    }


    public void solution() {
//        String name = "JEROEN";
//        String name = "JAN";
        String name = "ABABAAAAAAABA";
//        String name = "FAWJAAAFV";
//        String name = "GTAASKKAE";

        boolean[] isComplete = new boolean[name.length()];

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 'A') {
                isComplete[i] = true;
            }
        }

        isComplete[0] = true;
        System.out.println("isComplete = " + Arrays.toString(isComplete));
        dfs(name, isComplete, 0, 0);

        System.out.println("결과 = " + this.finalSum);
    }
}
