package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2493
 */
public class KoiTower implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

        int[] store = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        Stack<Node> dp = new Stack<>();

        int[] answer = new int[ct];
        dp.add(new Node(ct - 1, store[ct - 1]));
        for (int i = store.length - 2; i >= 0; i--) {
            // 확인한다.
            while (!dp.isEmpty()) {
                if (dp.peek().data > store[i]) {
                    break;
                }
                Node popNode = dp.pop();
//                System.out.println(popNode);
                answer[popNode.loc] = i + 1;

            }
            dp.add(new Node(i, store[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int loc;
        int data;

        public Node(int loc, int data) {
            this.loc = loc;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                "loc=" + loc +
                ", data=" + data +
                '}';
        }
    }
}
