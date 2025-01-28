package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import utils.BaekAlgoStudy;


/*
    문제 링크
    https://www.acmicpc.net/problem/1446
 */
public class FindShortPathHighway implements BaekAlgoStudy {

    static Node[] store;
    static int answer = Integer.MAX_VALUE;

    public static void dfs(int loc, int current, int cost, int finish) {
        // 넘는건 불가능
        if (current > finish) {
            return;
        }

        // 여기서 끝까지 고속도로만 탄 경우를 구해준다.
        answer = Math.min(cost + (finish - current), answer);

        for (int start = loc; start < store.length; start++) {
            Node tp = store[start];

            // 시작 위치가 현재보다 뒤인 경우에만
            if (tp.start >= current) {

                // 시작 위치와 current 사이의 값을 더해준다.
                int highwayOn = tp.start - current;
                dfs(start + 1, tp.end, cost + tp.time + highwayOn, finish);
            }
        }
    }

    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] first = reader.readLine().split(" ");
        int size = Integer.parseInt(first[0]);
        int targetLoc = Integer.parseInt(first[1]);

        store = new Node[size];

        for (int i = 0; i < size; i++) {
            int[] tp = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(d -> Integer.parseInt(d)).toArray();

            store[i] = new Node(tp[0], tp[1], tp[2]);
        }

        Arrays.sort(store, new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return o1.start - o2.start;
            }
        });

        dfs(0, 0, 0, targetLoc);

//        System.out.println(Arrays.toString(store));
        System.out.println(answer);

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int start;
        int end;
        int time;

        public Node(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                "start=" + start +
                ", end=" + end +
                ", time=" + time +
                '}';
        }
    }
}
