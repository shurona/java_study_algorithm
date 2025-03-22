package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/10989

    해결 과정
    처음에 입력값이 많은 정렬문제라고 판단 하고 Priority Queue를 사용해서 풀어보려고 하였으나 메모리 초과가 발생
    입력의 최대값이 10000이라서 배열에 수치를 저장함으로써 메모리 사용을 최소화해서 진행하였음.
 */
public class SortingManyNumbers implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

//        int[] arr = new int[10];
        int[] arr = new int[10001];

        for (int i = 0; i < ct; i++) {
            arr[Integer.parseInt(reader.readLine())] += 1;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > 0) {
                sb.append(i).append("\n");
                arr[i] -= 1;
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
