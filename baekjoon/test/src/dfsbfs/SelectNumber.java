package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2668
 */
public class SelectNumber implements BaekAlgoStudy {

    static boolean[] visited;
    static List<Integer> store = new ArrayList<>();

    // arr: 입력값, start: 목표 값,  input 배열 값
    static void findCycle(int[] arr, int start, int input, boolean[] inner) {
        // 마지막에 도달했으면 패스
        // 만약 시작 값하고 밸류 값이 같으면 넘긴다.
        if (start == arr[input]) {
            inner[input] = true;
            for (int index = 1; index < arr.length; index++) {
                if (inner[index]) {
                    store.add(index);
                    visited[index] = true;
                }
            }
            return;
        }

        if (!visited[input] && !inner[input]) {
            inner[input] = true;
            findCycle(arr, start, arr[input], inner);
        }

    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

        int[] arr = new int[ct + 1];
        visited = new boolean[ct + 1];

        for (int i = 1; i <= ct; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 1; i <= ct; i++) {
            if (!visited[i]) {
//                System.out.println(i);
//                System.out.println(Arrays.toString(visited));
                findCycle(arr, i, i, new boolean[ct + 1]);
            }

        }

        StringBuilder sb = new StringBuilder();

        sb.append(store.size()).append("\n");

        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
