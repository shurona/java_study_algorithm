package problem;

import utils.AlgoStudy;

public class ChuSeokTrafficControl implements AlgoStudy {

    public int solution(String[] lines) {
        int[][] duration = new int[lines.length][2];

        for (int i = 0; i < lines.length; i++) {
            String[] split = lines[i].split(" ");

            // 시간
            String[] timeTable = split[1].split(":");

            double hour = Double.parseDouble(timeTable[0]) * 60 * 60;
            double min = Double.parseDouble(timeTable[1]) * 60;
            double sec = Double.parseDouble(timeTable[2]);
            double plus = Double.parseDouble(split[2].substring(0, split[2].length() - 1));

            duration[i][1] = (int) ((hour + min + sec) * 1000);
            duration[i][0] = (duration[i][1] - (int) (plus * 1000)) + 2;
        }

        int answer = 0;
        for (int i = 0; i < duration.length; i++) {
            int testStart = duration[i][1];
            int testEnd = duration[i][1] + 1000;
            int count = 0;
            System.out.println(duration[i][1]);
            for (int j = i; j < duration.length; j++) {
                System.out.println(testStart + " : " + duration[j][1]);
//                System.out.println(duration[j][0] + " : " + testEnd);

                if (duration[j][0] <= testEnd && testStart <= duration[j][1]) {
                    count += 1;
                }
            }
            System.out.println();
            answer = Math.max(answer, count);
//            break;
        }

//        for (int[] doubles : duration) {
//            System.out.println(Arrays.toString(doubles));
//        }

        return answer;
    }

    @Override
    public void init() {
//        String[] arr = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        String[] arr = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
//        String[] arr = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
//            "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s",
//            "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s",
//            "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
//            "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};

        // 72000 + 3540 + 57.421

        int solution = solution(arr);
        System.out.println("정답은 : " + solution);

    }
}
