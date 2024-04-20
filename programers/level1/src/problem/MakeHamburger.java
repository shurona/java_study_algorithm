package problem;

import java.util.*;

public class MakeHamburger {
    // hamIndex를 체크한다.
    private int checkCurrentHam(Stack<Integer> leftQueue) {

        int hamIndex = 0;
        int stackSize = leftQueue.size();

        if(stackSize == 1){
            Integer now = leftQueue.get(0);
            if(now == 1){
                hamIndex = 1;
            }
        } else if (stackSize > 1){
            Integer right =  leftQueue.get(leftQueue.size() - 1);
            Integer left = leftQueue.get(leftQueue.size() - 2);

            if(right == 2 && left == 1) {
                hamIndex = 2;
            } else if (right == 1) {
                hamIndex = 1;
            }
        }

        // System.out.println(leftQueue);
        // System.out.println(hamIndex);

        return hamIndex;

    }

    public void solution() {

        int[] ingredient = {2, 1, 1, 2, 3, 1, 2, 3, 1};

        int answer = 0;

        int[] hamburger = {1, 2, 3, 1};
        int hamIndex = 0;

        int ingreIndex = 0;
        Stack<Integer> leftQueue = new Stack<>();

        // 반복해서 Stack에 넣어준다.
        while(ingreIndex < ingredient.length) {
            int currentIngre = ingredient[ingreIndex];
            // 햄버거와 비교 같으면 하나씩 더하기
            if(hamburger[hamIndex] == currentIngre) {
                // System.out.println("여기 : " + currentIngre + " : " + ingreIndex);
                // 마지막 빵이면 마무리 처리
                if(hamIndex == 3 && currentIngre == 1) {
                    answer +=1;
                    for(int roll = 0; roll < 3; roll++){
                        leftQueue.pop();
                    }

                    hamIndex = checkCurrentHam(leftQueue);
                    ingreIndex +=1;
                    continue;
                } else {
                    hamIndex +=1;
                }
            } else if (currentIngre == 1) {
                hamIndex = 1;
            } else {
                hamIndex = 0;
            }
            leftQueue.add(currentIngre);
            ingreIndex +=1;
        }
        System.out.println("answer = " + answer);
    }
}
