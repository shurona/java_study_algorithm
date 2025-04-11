package queue;

import java.util.LinkedList;
import java.util.Queue;
import utils.AlgoStudy;

/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/389479

    문제 풀이
    Queue를 이용해서 증설된 서버의 양을 확인하고 끝나는 시간을 넣어서 선입 선출로 처리해준다.
    주의사항은 Queue와 같이 자료구조의 크기가 변화하는 저장소의 크기를 조건으로 For문을 만들지 맙시다.
 */
public class ScaleOutServer implements AlgoStudy {

    public int solution(int[] players, int m, int k) {

        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;

        for (int i = 0; i < players.length; i++) {
            while (!queue.isEmpty()) {
                if (i < queue.peek()) {
                    break;
                }
                queue.poll();
            }

            int needServer = players[i] / m;

//            if (i == 18) {
//            }

            if (needServer > queue.size()) {
                int queueSize = queue.size();
                for (int j = 0; j < needServer - queueSize; j++) {
                    answer += 1;
//                    System.out.println(i);
                    queue.add(i + k);
                }
            }
//            System.out.println(i + " : " + queue + " : " + needServer);

        }

        return answer;
    }

    @Override
    public void init() {
//        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int[] players = {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0,
            0};
        int solution = solution(players, 5, 1);
        System.out.println("정답은 : " + solution);

    }


}
