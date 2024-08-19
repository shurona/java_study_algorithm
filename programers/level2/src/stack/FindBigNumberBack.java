package stack;

import utils.AlgoStudy;
import java.util.Stack;

public class FindBigNumberBack implements AlgoStudy {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        Stack<Integer> check = new Stack<>();

        for(int i = 0 ; i < numbers.length; i++) {
            while(!check.isEmpty() && numbers[check.peek()] < numbers[i]) {
                Integer tp = check.pop();
                answer[tp] = numbers[i];
            }
            check.add(i);
            System.out.println(check);
        }

        while(!check.isEmpty()){
            answer[check.pop()] = -1;
        }

        return answer;
    }

    @Override
    public void init() {
        int[] a = {9, 1, 5, 3, 6, 2};
        solution(a);
    }
}
