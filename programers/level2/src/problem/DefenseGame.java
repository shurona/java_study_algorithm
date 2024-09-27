package problem;

import java.util.PriorityQueue;
import java.util.Queue;
import utils.AlgoStudy;

public class DefenseGame implements AlgoStudy {

//    https://school.programmers.co.kr/learn/courses/30/lessons/142085

    // k => 무적권 , n => 갯수
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        Queue<Integer> queue = new PriorityQueue<>();

        // 일단 앞에서 부터 k개 넣어준다.
        for (int i = 0; i < k; i++) {
            if (i == enemy.length) {
                break;
            }

            queue.add(enemy[i]);
        }

        // k 번째 부터 시작
        for (int i = k; i < enemy.length; i++) {
            // 제일 앞에 있는 값
            Integer current = queue.peek();

            // 현재 enemy
            Integer ene = enemy[i];

            // 크면 현재를 queue에 넣고 작은 수를 빼준다.
            if (ene > current) {
                queue.add(ene);
                ene = queue.poll();
            }

            n -= ene;
            if (n < 0) {
                break;
            }

            answer += 1;
        }

        return answer + queue.size();
    }


    @Override
    public void init() {
        int n = 1;
        int k = 3;
        int[] enemy = {4};

        int solution = solution(n, k, enemy);
        System.out.println("정답은 : " + solution);
    }
}
