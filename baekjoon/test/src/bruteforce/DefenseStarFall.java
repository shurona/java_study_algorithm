package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/14658

    문제 해결

 */
public class DefenseStarFall implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        int n = firstLine[0];
        int m = firstLine[1];
        int l = firstLine[2];
        int ct = firstLine[3];

        Node[] store = new Node[ct];

        for (int i = 0; i < ct; i++) {
            int[] ham = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

            store[i] = new Node(ham[0], ham[1]);
        }

        Arrays.sort(store, (o1, o2) -> o1.row - o2.row);

        int max = -1;
        for (int i = 0; i < store.length; i++) {
            for (int j = 0; j < store.length; j++) {
                int row = store[i].row;
                int col = store[j].col;
                int tp = 0;

                for (int index = 0; index < store.length; index++) {
                    Node node = store[index];
                    if (node.row >= row && node.col >= col
                        && node.row <= row + l && node.col <= col + l) {
                        tp += 1;
                    }
                }

                max = Math.max(max, tp);
            }
        }
        System.out.println(ct - max);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Node{" +
                "row=" + row +
                ", col=" + col +
                '}';
        }
    }
}
