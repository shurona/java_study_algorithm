package dfsbfs;

import utils.AlgoStudy;

public class TargetNumber implements AlgoStudy {
    int answer = 0;
    int target = 0;
    public int solution(int[] numbers, int target) {
        boolean[] visit = new boolean[numbers.length];
        this.target = target;
        dfs(numbers, visit, 0, 0);
        return this.answer;
    }

    private void dfs(int[] numbers, boolean[] visit, int count, int current) {
        if (count == numbers.length) {
//            answer += 1;
            if (target == current) {
                answer +=1;
            }
            return;
        }

        int nowData = numbers[count];

        dfs(numbers, visit, count + 1, current + nowData);
        dfs(numbers, visit, count + 1, current - nowData);


    }

    @Override
    public void init() {
        int[] numbers = {1, 1, 1, 1, 1};

        int target = 3;
        int solution = solution(numbers, target);
        System.out.println("정답은 : " + solution);

    }
}
