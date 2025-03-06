package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1863
 */
public class EasySkyline implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());
        Node[] store = new Node[ct];

        for (int i = 0; i < ct; i++) {
            String[] tp = reader.readLine().split(" ");
            store[i] = new Node(Integer.parseInt(tp[0]), Integer.parseInt(tp[1]));
        }

        // 위로 올라갔다 내려가면 하나의 건물이다.
        Stack<Node> stack = new Stack<>();
        int peekY = 0;
        int answer = 0;
        for (int i = 0; i < store.length; i++) {
            Node current = store[i];
            if (current.y > peekY) {
                peekY = current.y;
                stack.add(current);
                continue;
            }

            if (!stack.isEmpty()) {
                int leftPeek = stack.peek().y;
                while (leftPeek > current.y) {
//                    System.out.println(leftPeek + " :: " + stack);
                    stack.pop();
                    answer += 1;
                    if (stack.isEmpty()) {
                        break;
                    }
                    leftPeek = stack.peek().y;
                }
            }

            if (current.y > 0 && (stack.isEmpty() || current.y != stack.peek().y)) {
//                System.out.println(i);
                stack.add(current);
            }
        }

        HashSet<Integer> set = new HashSet<>();
//        System.out.println(stack + " : " + answer);
        while (!stack.isEmpty()) {
            set.add(stack.pop().y);

        }

        System.out.println(answer + set.size());

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}
