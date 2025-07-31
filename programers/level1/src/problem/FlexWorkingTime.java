package problem;

public class FlexWorkingTime {

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int index = 0; index < schedules.length; index++) {
            int output = calculateTime(schedules[index]);
            boolean check = true;
            for (int i = 0; i < 7; i++) {
                if (((startday - 1) + i) % 7 > 4) {
                    // System.out.println(i);
                    continue;
                }

                if (timelogs[index][i] > output) {
                    check = false;
                    break;
                }
            }

            if (check) {
                answer += 1;
            }
        }

        return answer;
    }

    private int calculateTime(int time) {

        int hour = time / 100;
        int min = time % 100;

        int upMin = min + 10;
        if (upMin >= 60) {
            hour += 1;
            upMin -= 60;
        }

        return (hour * 100) + upMin;
    }

}
