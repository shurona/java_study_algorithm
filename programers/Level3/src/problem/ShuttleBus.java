package problem;

import utils.AlgoLevel3Study;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShuttleBus implements AlgoLevel3Study {
    boolean compareTime(String currentTime, String firstMemberTime) {
        String[] output = currentTime.split(":");

        int currentHour = Integer.parseInt(output[0]);
        int currentMin = Integer.parseInt(output[1]);

        String[] memberTime = firstMemberTime.split(":");

        int memberHour = Integer.parseInt(memberTime[0]);
        int memberMin = Integer.parseInt(memberTime[1]);

        // 온 사람이 현재 시간 보다 작으면
        if (memberHour < currentHour) {
            return true;
        }
        if (memberHour == currentHour && memberMin <= currentMin) {
            return true;
        }

        // 탈 수 없음
        return false;
    }

    String addTime(String currentTime, int t) {

        String[] output = currentTime.split(":");

        int hour = Integer.parseInt(output[0]);
        int min = Integer.parseInt(output[1]);

        int afterMin = min + t;

        if (afterMin >= 60) {
            afterMin -= 60;
            hour +=1;
        }

        return (hour < 10 ? ("0" + hour) : hour) + ":" + (afterMin < 10 ? ("0" + afterMin) : afterMin);
    }

    /**
     *
     * @param n 셔틀버스가 오는 횟수
     * @param t 인터벌 시간
     * @param m 태울 수 있는 사람 수
     */
    public String solution(int n, int t, int m, String[] timetable) {

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] o1Split = o1.split(":");
                String[] o2Split = o2.split(":");

                int o1Hour = Integer.parseInt(o1Split[0]);
                int o2Hour = Integer.parseInt(o2Split[0]);
                int o1Minutes = Integer.parseInt(o1Split[1]);
                int o2Minutes = Integer.parseInt(o2Split[1]);

                if (o1Hour == o2Hour) {
                    return o1Minutes - o2Minutes;
                } else {
                    return o1Hour - o2Hour;
                }
            }
        });

        priorityQueue.addAll(Arrays.asList(timetable));

        String lastTime = "";
        String checkLastTime = "";
        String startTime = "09:00";
        for(int shuttleCount = 0 ;  shuttleCount < n; shuttleCount++){
//            System.out.println(startTime);
            for (int takeMember = 0; takeMember < m; takeMember++) {
                String topTime = priorityQueue.peek();
                if (topTime == null) {
                    lastTime = startTime;
                    break;
                }
                if (compareTime(startTime, topTime)) {
                    checkLastTime = priorityQueue.poll();
                } else {
                    checkLastTime = "";
                }
            }

            // 마지막은 안 더해 준다.
            if (shuttleCount == n - 1) {
                break;
            }
            startTime = addTime(startTime, t);
        }

        if (checkLastTime.isEmpty()) {
            System.out.println(startTime);
            return startTime;
        }


        if (lastTime.isEmpty()) {
            String[] lt = checkLastTime.split(":");
            int ltHour = Integer.parseInt(lt[0]);
            int ltMin = Integer.parseInt(lt[1]);

            ltMin -= 1;

            if (ltMin < 0) {
                ltMin += 60;
                ltHour -= 1;
            }
            lastTime = (ltHour < 10 ? ("0" + ltHour) : ltHour) + ":" + (ltMin < 10 ? ("0" + ltMin) : ltMin);;
        }

        System.out.println("마지막 : " + lastTime);
        return lastTime;
    }

    @Override
    public void init() {
//        int n = 1;
//        int t = 1;
//        int m = 5;
//        String[] timetable = {"08:00", "08:02", "08:01", "08:03"};

//        int n = 2;
//        int t = 10;
//        int m = 2;
//        String[] timetable = {"09:10", "09:09", "08:00"};

//        int n = 1;
//        int t = 1;
//        int m = 1;
//        String[] timetable = {"23:59"};

//        int n = 1;
//        int t = 1;
//        int m = 5;
//        String[] timetable = {"00:01", "00:01", "00:01", "00:01", "00:02"};

        int n = 10;
        int t = 60;
        int m = 1;
        String[] timetable = {
                "09:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"
        };

        solution(n, t, m, timetable);

    }
}
