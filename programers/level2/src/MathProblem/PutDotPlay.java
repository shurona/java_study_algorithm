package MathProblem;

import utils.AlgoStudy;

public class PutDotPlay implements AlgoStudy {
    public long solution(int k, int d) {
        long answer = 0;

        long doubleD = (long) d * d;
        // x는 d, y는 i일때 찍을 수 있는 최대 값
        for (int i = 0; i <= d; i += k) {
            long doubleI = (long) i * i;
            int diff = (int) Math.sqrt(doubleD - doubleI);
            answer += (diff / k) + 1;

        }

        return answer;
    }

    @Override
    public void init() {

        long solution = solution(1, 5);
        System.out.println("정답은 : " + solution);

    }
}
