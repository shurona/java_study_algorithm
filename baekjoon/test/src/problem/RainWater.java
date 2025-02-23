package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/14719
 */
public class RainWater implements BaekAlgoStudy {

    static int findNextTarget(Queue<Integer> queue, Map<Integer, Integer> store) {
        int output = 0;
        while (!queue.isEmpty()) {
            int peek = queue.peek();
            if (store.getOrDefault(peek, 0) > 0) {
                output = peek;
                break;
            }
            queue.poll();

        }

        return output;
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        Map<Integer, Integer> store = new HashMap<>();

        Queue<Integer> dp = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int[] board = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> {
                int dd = Integer.parseInt(s);
                dp.add(dd);
                Integer orDefault = store.getOrDefault(dd, 0);
                store.put(dd, orDefault + 1);

                return dd;
            })
            .toArray();

        int answer = 0;
        // 내려가고 올라와야 한다.
        for (int leftPoint = 0; leftPoint < board.length - 1; leftPoint++) {
//            System.out.println(":: " + leftPoint);
            int leftSide = board[leftPoint];
            // 왼쪽 차감
            store.put(leftSide, store.get(leftSide) - 1);

            if (leftSide <= board[leftPoint + 1]) {
                continue;
            }

            int nextTarget = findNextTarget(dp, store);
            Stack<Integer> stack = new Stack<>();

            // 다음의 가장 긴 부분을 찾는다.
            for (int rightPoint = leftPoint + 1; rightPoint < board.length; rightPoint++) {
                int rightSide = board[rightPoint];
                stack.add(rightSide);

                // count 차감
                store.put(rightSide, store.get(rightSide) - 1);
                if (rightSide == nextTarget || rightSide >= leftSide) {
                    int gizun = Math.min(leftSide, rightSide);
//                    System.out.println(stack);
                    while (!stack.isEmpty()) {
                        Integer pop = stack.pop();
                        if (gizun - pop > 0) {
                            answer += (gizun - pop);
                        }
                    }

//                    System.out.println(
//                        leftPoint + " : " + rightPoint + " : " + gizun + " : " + answer);
                    leftPoint = rightPoint - 1;
                    store.put(rightSide, store.get(rightSide) + 1);
                    break;
                }

            }

        }
        System.out.println(answer);
//        System.out.println(store);

        reader.close();
    }

    public void wrongSolution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int row = firstLine[0];
        int col = firstLine[1];

        int[] board = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int answer = 0;
        // 내려가고 올라와야 한다.
        for (int leftPoint = 0; leftPoint < board.length; leftPoint++) {
//            System.out.println(":: " + leftPoint);
            int leftSide = board[leftPoint];
            Stack<Integer> stack = new Stack<>();
            boolean isUnder = false;
            int tpSum = 0;
            // 앞에를 확인한다.
            for (int rightPoint = leftPoint; rightPoint < board.length; rightPoint++) {
                int rightSide = board[rightPoint];
                stack.add(rightSide);
                // 내려간 상태에서 확인할 것
                if (isUnder) {
                    // 마지막이거나 다음 칸이 내려가면
                    if (rightPoint == board.length - 1
                        || board[rightPoint] > board[rightPoint + 1]) {
                        System.out.println(stack + " : " + leftSide + " : " + rightSide);
                        int gizun = Math.min(rightSide, leftSide);
                        while (!stack.isEmpty()) {
                            int i = gizun - stack.pop();
                            if (i > 0) {
//                                System.out.println(i);
                                answer += i;
                            }
                        }
                        leftPoint = rightPoint - 1;
//                        System.out.println(tpSum);
                        break;
                    }

                }
                // 파인 부분이 있어야 한다.
                if (leftSide > rightSide) {
                    isUnder = true;
                }
            }

        }
        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
