package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/13549
 */
public class HideAndSeekThree implements BaekAlgoStudy {

    static int[] store = new int[100001];

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        // 최대로 채워주고
        Arrays.fill(store, Integer.MAX_VALUE);

        int me = Integer.parseInt(input[0]);
        int sister = Integer.parseInt(input[1]);

        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.step - o2.step;
            }
        });
        queue.add(new Node(0, me));
        store[me] = 0;

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            Node now = queue.poll();
//            System.out.println("??? : " + queue.size());
            if (now.loc == sister) {
//                System.out.println("??? : " + now.step);
                break;
            }

            if (now.loc == 0) {
                int next = store[1];
                // 스텝이 적으면 넣어준다
                if (now.step + 1 < next) {
                    store[1] = now.step + 1;
                    queue.add(new Node(now.step + 1, 1));
                }
                continue;
            }

            for (int i = now.loc; i <= 100001; i = i * 2) {
                // 만약 지금이 더 작으면 넣어준다
                if (now.step < store[i]) {
                    store[i] = now.step;
                }

                // 범위 확인 후
                if (i - 1 >= 0) {
                    int before = store[i - 1];
                    // 스텝이 적으면 넣어준다.
                    if (now.step + 1 < before) {
                        store[i - 1] = now.step + 1;
                        queue.add(new Node(now.step + 1, i - 1));
                    }


                }

                // 범위 확인 후
                if (i + 1 < 100001) {
                    int next = store[i + 1];
                    // 스텝이 적으면 넣어준다
                    if (now.step + 1 < next) {
                        store[i + 1] = now.step + 1;
                        queue.add(new Node(now.step + 1, i + 1));
                    }
                }
            }
//            break;

        }

        System.out.println(store[sister]);
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int step;
        int loc;

        public Node(int step, int loc) {
            this.step = step;
            this.loc = loc;
        }

        @Override
        public String toString() {
            return "Node{" +
                "step=" + step +
                ", loc=" + loc +
                '}';
        }
    }


}
