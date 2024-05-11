package problem;

import java.util.*;

public class PrivacyPeriod {
    public boolean calDistance(String today, String input, int limit) {

        String[] todayDate = today.split("\\.");
        String[] inputDate = input.split("\\.");
        boolean isAfter = false;

        int afterMonth = Integer.parseInt(inputDate[1]) + limit;

        if (afterMonth > 12) {
            int m = afterMonth % 12;
            int y = afterMonth / 12;

            if (m == 0) {
                m = 12;
                y -= 1;
            }

            int afterYear = Integer.parseInt(inputDate[0]) + y;
            inputDate[0] = String.valueOf(afterYear);
            inputDate[1] = String.valueOf(m);

        } else {
            inputDate[1] = String.valueOf(afterMonth);
        }
        System.out.println("inputDate = " + Arrays.toString(inputDate));

        int todayYear = Integer.parseInt(todayDate[0]);
        int todayMonth = Integer.parseInt(todayDate[1]);
        int todayDay = Integer.parseInt(todayDate[2]);

        int checkYear = Integer.parseInt(inputDate[0]);
        int checkMonth = Integer.parseInt(inputDate[1]);
        int checkDay = Integer.parseInt(inputDate[2]);

        //년
        if(checkYear < todayYear) {
            return true;
        }
        //월
        else if (todayYear == checkYear && checkMonth < todayMonth) {
            return true;
        }
        //일
        else if(todayYear == checkYear && todayMonth == checkMonth && checkDay <= todayDay) {
            return true;
        }


        return isAfter;
    }

    public boolean convertDay(String today, String input, int limit) {
        String[] todayDate = today.split("\\.");
        String[] inputDate = input.split("\\.");

        boolean isAfter = false;

        //
        int todayDateToDay = (Integer.parseInt(todayDate[0]) * 12 * 28)
                + (Integer.parseInt(todayDate[1]) * 28)
                + Integer.parseInt(todayDate[2]);

        int inputDateDay = (Integer.parseInt(inputDate[0]) * 12 * 28)
                + (Integer.parseInt(inputDate[1]) * 28)
                + (limit * 28)
                + Integer.parseInt(inputDate[2]);

//        System.out.println("todayDateToDay = " + todayDateToDay);
//        System.out.println("inputDateDay = " + inputDateDay);
//        System.out.println("year = " + (Integer.parseInt(todayDate[0]) * 12 * 28));
//        System.out.println("month = " + Integer.parseInt(todayDate[1]) * 28);
//        System.out.println("day = " + Integer.parseInt(todayDate[2]));
//        System.out.println("limit = " + (limit * 28));
//        System.out.println();

        if (inputDateDay <= todayDateToDay) {
            return true;
        }

        return isAfter;
    }

    public void solution() {
        String today = "2022.05.19";
        String[] terms = {"A 12", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

        Map<String, Integer> nickWithPeriod = new HashMap<>();

        for (String term : terms) {
            String[] s = term.split(" ");
            nickWithPeriod.put(s[0], Integer.valueOf(s[1]));
        }

        List<Integer> answer = new ArrayList<>();
        int index = 1;
        for (String privacy : privacies) {
            String[] ss = privacy.split(" ");
            boolean isAfter = convertDay(today, ss[0], nickWithPeriod.get(ss[1]));
            if (isAfter) {
                answer.add(index);
            }
            index+=1;
//            break;
        }

        int[] array = answer.stream().mapToInt(i -> i).toArray();
        System.out.println("array = " + Arrays.toString(array));

    }
}
