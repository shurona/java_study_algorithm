package stack;

import java.util.ArrayList;
import java.util.List;

public class DevelopSolution {
    public void solution() {
//        int[] progresses = { 93, 30, 55 };
//        int[] speeds = { 1, 30, 5};
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};

        int maxPercent = 100;
        double remainDate = -1;
        double[] remainDateList = new double[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
//            System.out.println((maxPercent - progresses[i]));
            remainDate = Math.ceil((double) (maxPercent - progresses[i]) / speeds[i]);
            remainDateList[i] = remainDate;
        }

        List<Integer> output = new ArrayList<>();
        double remainMaxDate = remainDateList[0];
        int stackCount = 0;
        for (double remain : remainDateList) {
            // 만약 남은 기간 보다 큰 숫자를 보면 합쳐서 배포 후 리셋
            if (remainMaxDate < remain) {
                remainMaxDate = remain;
//                System.out.println(remainMaxDate);
                output.add(stackCount);
                stackCount = 0;
            }

            stackCount += 1;
        }

        if (stackCount > 0) output.add(stackCount);
        int[] answer = output.stream().mapToInt(i -> i).toArray();
    }
}
