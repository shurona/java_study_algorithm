package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import utils.BaekAlgoStudy;

public class NMaxNumber implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        int[][] board = new int[n][n];

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < n; i++) {
            Arrays.stream(reader.readLine().split(" "))
                .forEach(dd -> queue.add(Integer.parseInt(dd)));

        }

        int answer = 0;
        int index = 0;
        while (!queue.isEmpty()) {
            if (index == n) {
                break;
            }
            answer = queue.poll();
            index++;
        }

        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int line;
        int data;
    }
}
