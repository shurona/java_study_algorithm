package problem;

import utils.AlgoStudy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RotateBracket implements AlgoStudy {
    public boolean checkBracket(String bracketList) {

        int small = 0;
        int middle = 0;
        int big = 0;

        Stack<String> queue = new Stack<>();
        String currentStatus = "";
        for (int index = 0; index < bracketList.length(); index++) {
            char c = bracketList.charAt(index);
            switch (c) {
                case '{':
                    middle +=1;
                    queue.add("mid");
                    break;

                case '[':
                    big += 1;
                    queue.add("big");
                    break;

                case '(':
                    small += 1;
                    queue.add("small");
                    break;

                case '}':
                    middle -= 1;
                    if (queue.isEmpty()) {
                        return false;
                    }
                    currentStatus = queue.peek();
                    if (!currentStatus.equals("mid")) {
                        return false;
                    }
                    queue.pop();
                    break;

                case ']':
                    big -= 1;
                    if (queue.isEmpty()) {
                        return false;
                    }
                    currentStatus = queue.peek();
                    if (!currentStatus.equals("big")) {
                        return false;
                    }
                    queue.pop();
                    break;

                case ')':
                    small -= 1;
                    if (queue.isEmpty()) {
                        return false;
                    }
                    currentStatus = queue.peek();
                    if (!currentStatus.equals("small")) {
                        return false;
                    }
                    queue.pop();
                    break;
            }

//            System.out.println(index + " " + c + " " + queue);
            if (small < 0 || middle < 0 || big < 0) {
                return false;
            }
        }
        return small == 0 && middle == 0 && big == 0;
    }

    public int solution(String s) {

        int answer = 0;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            sb.append(sb.charAt(0));
            sb.delete(0, 1);
            System.out.println(sb);
            if (checkBracket(sb.toString())) {
                answer += 1;
            }
            if (i == 0) {
//                break;
            }
        }
        return answer;
    }

    @Override
    public void init() {
        String s = "{(})";
        int solution = solution(s);
        System.out.println("정답은 : " + solution);

    }
}
