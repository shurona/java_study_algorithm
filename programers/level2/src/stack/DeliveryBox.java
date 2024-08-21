package stack;

import utils.AlgoStudy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeliveryBox implements AlgoStudy {
    public int solution(int[] order) {

        Stack<Integer> subContainer = new Stack<>();
        Queue<Integer> mainContainer = new LinkedList<>();

        for (int i = 1; i <= order.length; i++) {
            mainContainer.add(i);
        }

        int answer = 0;
        int currentIndex = 0;
        while (true) {
//            System.out.println(mainContainer + "   :   " + subContainer + "     :    " + order[currentIndex]);
            if (!mainContainer.isEmpty()) {
                // 메인컨테이너에서 갖고 옴
                if (mainContainer.peek() == order[currentIndex]) {
                    mainContainer.poll();
                    answer += 1;
                    currentIndex += 1;
                    // 메인컨테이너 에서 다른 경우
                } else {
                    if (!subContainer.isEmpty()) {
                        // 서브 컨테이너에서 갖고 옴
                        if (subContainer.peek() == order[currentIndex]) {
                            subContainer.pop();
                            answer += 1;
                            currentIndex += 1;
                        } else {
                            // 만약 서브와 메인의 값이 다 크면
                            if (mainContainer.peek() > order[currentIndex] && subContainer.peek() > order[currentIndex]) {
                                break;
                            }
                            // 둘 다 다르면 옮겨준다.
                            subContainer.add(mainContainer.poll());
                        }
                    } else {
                        // 서브 컨테이너가 빈 경우
                        subContainer.add(mainContainer.poll());
                    }
                }
            } else {
                if (!subContainer.isEmpty()) {
                    // 서브 컨테이너에서 갖고 옴
                    if (subContainer.peek() == order[currentIndex]) {
                        subContainer.pop();
                        answer += 1;
                        currentIndex += 1;
                        continue;
                    } else {
                        // 메인컨테이너가 비었고 서브 컨테이너에서 다른 경우
                        break;
                    }
                } else {
                    break;
                }
            }

        }

        return answer;
    }

    @Override
    public void init() {
//        int[] order = {4, 3, 1, 2, 5};
        int[] order = {5, 4, 3, 2, 1};
        int solution = solution(order);
        System.out.println("정답은 : " + solution);
    }
}
