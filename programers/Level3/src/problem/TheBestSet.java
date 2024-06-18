package problem;

import utils.AlgoLevel3Study;

import java.util.Arrays;

public class TheBestSet implements AlgoLevel3Study {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        if (s == 1 || n > s) {
            return new int[]{-1};
        }

        // s / n
        int mok = (int)(s / n);
        int mod = s % n;

        for (int i = 0; i < answer.length; i++) {
            if (mod > 0) {
                answer[i] = mok + 1;
                mod -=1;
            } else {
                answer[i] = mok;
            }
        }

        Arrays.sort(answer);

        return answer;
    }

    @Override
    public void init() {
        int n = 3;
        int s = 10;

        int[] solution = anotherSolution(n, s);
        System.out.println(Arrays.toString(solution));

    }

    public int[] anotherSolution(int n, int s) {
        int[] answer = null;
        if(n>s) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[n];
            int i = 0;
            // 하나씩 채워넣는거구만
            while(s>0) {
                answer[(i%n)]++;
                i++;
                s--;
            }

        }
        Arrays.sort(answer);
        return answer;
    }
}
