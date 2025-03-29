package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import utils.BaekAlgoStudy;

/*
    건물 보기 문제

    문제 링크
    https://www.acmicpc.net/problem/22866

    문제 설명
    좌우의 빌딩에서 볼 수 있는 빌딩을 확인하는 문제이다.
    자세한 설명은 아래의 링크에서 확인 가능하다.
    https://docs.shrona.com/00.-Tech/001.-algorithm/%EC%8A%A4%ED%83%9D-%EB%B0%8F-%ED%81%90%EC%99%80-%EA%B0%99%EC%9D%80-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0
 */
public class WatchBuilding implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int store = Integer.parseInt(reader.readLine());

        int[] buildings = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s))
            .toArray();

        Stack<Node> stack = new Stack<>();

        // index => 가까운 인덱스 , height => 보이는 건물 합산
        Node[] answer = new Node[buildings.length];

        int max = 987654321;
        for (int i = 0; i < answer.length; i++) {
            answer[i] = new Node(max, 0);
        }

        stack.add(new Node(0, buildings[0]));

        // 왼쪽 부터 순차적으로 빌딩 크기를 비교하면서 만약 중간에 낮은 빌딩들은 없앤다.
        for (int i = 1; i < buildings.length; i++) {
            while (!stack.isEmpty()) {
                // 반복문을 돌면서 현재 위치에서 높이가 낮은 건물들을 빼놓는다.
                if (stack.peek().height <= buildings[i]) {
//                    System.out.println(i + " : " + stack.pop());
                    stack.pop();
                } else {
                    break;
                }
            }

            // 만약 스택이 비어 있지 않으면 현재 중에서 가장 가까운 인덱스를 저장해 준다.
            if (!stack.isEmpty()) {
                answer[i].index = stack.peek().index + 1;
            }

            answer[i].height += stack.size();
            stack.add(new Node(i, buildings[i]));
        }

//        for (Node node : answer) {
//            System.out.println(node);
//        }

        // 스택 초기화
        stack.clear();

        // 이번에는 오른쪽에서 부터 높은 건물들을 확인한다.
        stack.add(new Node(buildings.length - 1, buildings[buildings.length - 1]));
        for (int i = buildings.length - 2; i >= 0; i--) {
            while (!stack.isEmpty()) {
                Node top = stack.peek();
                if (top.height <= buildings[i]) {
//                    System.out.println(i + " : " + stack.pop());
                    stack.pop();
                } else {
                    break;
                }
            }

            if (!stack.isEmpty()) {
                // left 거리와 비교해서 가까운 거리를 비교 한다.
                int rightDistance = Math.abs(stack.peek().index - i);
                int leftDistance = Math.abs(answer[i].index - i) + 1;

//                System.out.println(i + " : " + leftDistance + " : " + rightDistance);

                if (rightDistance < leftDistance) {
                    answer[i].index = stack.peek().index + 1;
                } else if (rightDistance == leftDistance) {
                    answer[i].index = Math.min(stack.peek().index + 1, answer[i].index);
                }
            }

            answer[i].height += stack.size();
            stack.add(new Node(i, buildings[i]));
//            System.out.println();
        }

//        for (Node node : answer) {
//            System.out.println(node);
//        }

        // 출력 ( 건물 갯수 : 최대 근접 인덱스)
        for (Node node : answer) {
            if (node.index == max) {
                System.out.println(0);
            } else {
                System.out.println(node.height + " " + node.index);
            }
        }

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        int index;
        int height;

        public Node(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Node{" +
                "index=" + index +
                ", height=" + height +
                '}';
        }
    }
}
