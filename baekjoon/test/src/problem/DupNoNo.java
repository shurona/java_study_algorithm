package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/20922
 */
public class DupNoNo implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");

        int size = Integer.parseInt(firstLine[0]);
        int wishDup = Integer.parseInt(firstLine[1]);

        int[] inputs = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int left = 0;
        int right = 0;

        int[] numberCounter = new int[100001];
//        int[] numberCounter = new int[20];
        int answer = -1;
        while (left < inputs.length) {
            int target = 0;
            for (int rc = right; rc < inputs.length; rc++) {
                int now = inputs[rc];
                numberCounter[now] += 1;

                if (numberCounter[now] > wishDup) {
                    right = rc + 1;
                    target = now;
                    break;
                }

//                System.out.println(answer + " : " + rc + " : " + left);
                answer = Math.max(answer, rc - left + 1);
            }

            while (numberCounter[target] > wishDup) {
                int now = inputs[left];
                numberCounter[now] -= 1;
                left += 1;
            }

            if (target == 0) {
                break;
            }

//            System.out.println(Arrays.toString(numberCounter));
//            System.out.println(left + " : " + right);
//            System.out.println();
//            break;
        }

        System.out.println(answer);
    }

    /*
        추후 문제를 다시 확인해 볼 때 주의하라는 의미에서 남겨놓음
        하나의 숫자의 갯수만 고려하였음
     */
    public void wrongSolution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");

        int size = Integer.parseInt(firstLine[0]);
        int wishDup = Integer.parseInt(firstLine[1]);

        int[] inputs = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        Map<Integer, List<Node>> store = new HashMap<>();
        int[] leftPointer = new int[100001];
        Set<Integer> set = new HashSet<>();

        int answer = -1;
        for (int i = 0; i < inputs.length; i++) {
            int currentValue = inputs[i];
            List<Node> check = store.getOrDefault(currentValue, new ArrayList<>());

            if (check.isEmpty()) {
                check.add(new Node(currentValue, 0));
            }

//            System.out.println(
//                "지금 : " + (check.size() - leftPointer[currentValue]) + " : " + currentValue);
//            //
            if (check.size() - leftPointer[currentValue] <= wishDup) {
                // 비어있으면 넣어준다.
                check.add(new Node(currentValue, i + 1));
                store.put(currentValue, check);
                continue;
            }

            set.add(currentValue);

            // 왼쪽이 가리키는 곳
            Node front = check.get(leftPointer[currentValue]);

            // 제일 뒷 부분
            Node back = new Node(currentValue, i + 1);

            System.out.println(front.loc + " : " + i + " : " + (back.loc - front.loc));
            answer = Math.max(answer, back.loc - front.loc - 1);

            check.add(back);
            leftPointer[currentValue] += 1;

        }

        for (Integer i : set) {
            int loc = store.get(i).get(leftPointer[i]).loc;

//            System.out.println(
//                store.get(i).get(leftPointer[i]) + " ??? " + leftPointer[i] + " ???? " + (size
//                    - loc));
            answer = Math.max(answer, size - loc);
        }

        System.out.println(store);
        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int value;
        int loc;

        public Node(int value, int loc) {
            this.value = value;
            this.loc = loc;
        }

        @Override
        public String toString() {
            return "Node{" +
                "value=" + value +
                ", loc=" + loc +
                '}';
        }
    }
}
