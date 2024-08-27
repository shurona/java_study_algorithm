package problem;

import java.util.Arrays;
import java.util.Comparator;
import utils.AlgoStudy;

public class RentHotel implements AlgoStudy {

    public int compareTime(String left, String right) {

        String[] leftTime = left.split(":");
        String[] rightTime = right.split(":");

        if (leftTime[0].equals(rightTime[0])) {
            int leftMin = Integer.parseInt(leftTime[1]);
            int rightMin = Integer.parseInt(rightTime[1]);

            return rightMin - leftMin;

        } else {
            int leftHour = Integer.parseInt(leftTime[0]);
            int rightHour = Integer.parseInt(rightTime[0]);
            return rightHour - leftHour;
        }
    }

    public int stringToTimeInt(String timeString) {
        String[] split = timeString.split(":");
        return Integer.parseInt(split[1]) + (Integer.parseInt(split[0]) * 60);
    }

    public int solution(String[][] book_time) {

        int[] timetable = new int[1500];

        int answer = 0;
        for (int i = 0; i < book_time.length; i++) {
            int startTime = stringToTimeInt(book_time[i][0]);
            int endTime = stringToTimeInt(book_time[i][1]) + 10;

            timetable[startTime] += 1;
            timetable[endTime] -=1;
        }

        int temp = 0;
        for (int i : timetable) {
            temp += i;
            answer = Math.max(temp, answer);
        }

        return answer;
    }

    @Override
    public void init() {
        String[][] bookTime = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {
            "14:10", "19:20"}, {
            "18:20", "21:20"
        }};

        int solution = solution(bookTime);
        System.out.println("정답은 : " + solution);

    }
}
