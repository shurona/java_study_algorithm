package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1927
 */
public class PriorityQueueTest implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int rowCt = Integer.parseInt(reader.readLine());

        int zeroCt = 0;
        for (int i = 0; i < rowCt; i++) {
            int now = Integer.parseInt(reader.readLine());
            if (now == 0) {
                if (queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.add(now);
            }
        }

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
