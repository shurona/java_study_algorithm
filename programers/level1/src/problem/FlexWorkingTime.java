package problem;

/**
 * 근무자들이 출근 시간을 지켰는 지 확인하는 문제로 중요한 %를 사용한 나머지 추출이 관건인 문제
 * https://school.programmers.co.kr/learn/courses/30/lessons/388351
 */
public class FlexWorkingTime {

    private static final int DAYS_IN_WEEK = 7;
    private static final int WEEKEND_START = 5; // 토요일(5), 일요일(6)
    private static final int MINUTES_TO_ADD = 10;
    private static final int MINUTES_IN_HOUR = 60;

    public int solution(int[] schedules, int[][] timelogs, int startDay) {
        int validCount = 0;

        for (int employee = 0; employee < schedules.length; employee++) {
            int allowedTime = addMinutes(schedules[employee], MINUTES_TO_ADD);
            boolean isValid = true;

            for (int day = 0; day < DAYS_IN_WEEK; day++) {
                int weekDay = ((startDay - 1) + day) % DAYS_IN_WEEK;
                if (weekDay >= WEEKEND_START) {
                    continue;
                }
                if (timelogs[employee][day] > allowedTime) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                validCount++;
            }
        }

        return validCount;
    }

    private int addMinutes(int time, int minutesToAdd) {
        int hour = time / 100;
        int min = time % 100;

        min += minutesToAdd;
        if (min >= MINUTES_IN_HOUR) {
            hour += min / MINUTES_IN_HOUR;
            min = min % MINUTES_IN_HOUR;
        }

        return (hour * 100) + min;
    }

}
