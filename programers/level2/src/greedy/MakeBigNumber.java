package greedy;

import java.util.Arrays;
import java.util.Stack;

public class MakeBigNumber {
    public void solution() {
//        String number = "1924";
//        int k = 2;

//        String number = "1231234";
//        int k = 3;

//        String number = "4177252841";
//        int k = 4;

//        String number = "4177252841";
//        int k = 1;

        String number = "39393939";
        int k = 14;


        StringBuilder targetNumber = new StringBuilder();

        int stepK = number.length() - k - 1;
        int maxLeftIndex = -1;
        int checkNum;
        // 최소 숫자를 하나씩 제거 한다.
        while (targetNumber.length() < (number.length() - k)) {
            checkNum = -1;

            // k개의 이전에 한 번 확인 한다.
            for (int i = (maxLeftIndex + 1); i < (number.length() - stepK); i++) {
                int currentNum = Integer.parseInt(String.valueOf(number.charAt(i)));

                // 현재 숫자가 가장 큰 경
                if (currentNum > checkNum) {
                    // 선택된 숫자
                    checkNum = currentNum;
                    // 현재 인덱스
                    maxLeftIndex = i;
                }

            }
            stepK -=1;
            targetNumber.append(String.valueOf(checkNum));
        }

        System.out.println("answer : " + targetNumber.toString());
        System.out.println("MakeBigNumber.solution");
    }

    public void anotherSolution() {
        String number = "4177252841";
        int k = 4;

        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            /*
             * 반복을 도는 조건
             * 1. 스택이 남아 있거나
             * 2. stack 의 최근 값이 현재 i보다 작거나
             * 3. k가 0보다 크거나
            */
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
            System.out.println("stack = " + stack);
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);
        }

        System.out.println("result = " + Arrays.toString(result));
    }
}
