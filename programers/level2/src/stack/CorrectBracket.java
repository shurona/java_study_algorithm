package stack;

import java.util.Stack;

public class CorrectBracket {
    public void solution() {

//        String s = "()()";
        String s = ")()(";
//        String s = "(()(";

        boolean answer = true;
        int checkInt = 0;

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                checkInt += 1;
            } else if(s.charAt(i) == ')') {
                checkInt -= 1;
            }

            //
            if (checkInt < 0) {
                break;
            }
        }

        answer = checkInt == 0;

        System.out.println(checkInt);
        System.out.println("정답은 : " + answer);
    }

    public void useStack() {
//        String s = "()()";
//        String s = ")()(";
//        String s = "(()(";
        String s = "((((()))))";

        Stack<Integer> stack = new Stack<>();
        boolean answer = true;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.add(1);
            } else if (s.charAt(i) == ')') {
                if (stack.size() == 0) {
                    answer = false;
                    break;
                }
                stack.pop();
            }
        }

        if(!stack.isEmpty()) answer = false;

        System.out.println(answer);

    }
}
