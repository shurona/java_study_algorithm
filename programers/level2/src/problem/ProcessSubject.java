package problem;
import java.util.*;

public class ProcessSubject {
    private int convertHourToMin(String time) {
        String[] splitedTime = time.split(":");

        int hour =  Integer.parseInt(splitedTime[0]);
        int min =  Integer.parseInt(splitedTime[1]);

        return (hour * 60) + min;
    }
    
    public void solution() {
        String[][] plans = {
                {"korean", "11:40", "30"},
                {"english", "12:10", "20"},
                {"math", "12:30", "40"}
        };
        
        String[] answer = new String[plans.length];
        int answerIndex = 0;

        Stack<String[]> job = new Stack<>();


        Arrays.sort(plans, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                String[] o1TimeString = o1[1].split(":");
                String[] o2TimeString = o2[1].split(":");

                int o1Hour =  Integer.parseInt(o1TimeString[0]);
                int o2Hour =  Integer.parseInt(o2TimeString[0]);

                int o1Min = Integer.parseInt(o1TimeString[1]);
                int o2Min = Integer.parseInt(o2TimeString[1]);

                if(o1Hour == o2Hour) {
                    return o1Min - o2Min;
                } else {
                    return o1Hour - o2Hour;
                }
            }
        });


        int currentTime = convertHourToMin(plans[0][1]);
        int remainTime = Integer.parseInt(plans[0][2]);
        String subject = plans[0][0];

        int index = 1;
        while(index <= plans.length) {

            if(index == plans.length) {
                answer[answerIndex] = subject;
                answerIndex += 1;
                break;
            }

            // 현재
            int newTime = convertHourToMin(plans[index][1]);
            int newRemain = Integer.parseInt(plans[index][2]);

            // 앞에 확인
            if(newTime < currentTime + remainTime) {

                // new Remain 계산
                int newRemainTime = remainTime - (newTime - currentTime);

                String[] forStack = {subject, "asdf", newRemainTime + ""};
                job.push(forStack);

                currentTime = newTime;
                remainTime = newRemain;
                subject = plans[index][0];
            } else if(!job.isEmpty() && newTime >= (currentTime + remainTime)) {
                // 여유 있는데 Queue가 있으면 queue시간 우선 적용
                String[] tp = job.pop();

                answer[answerIndex] = subject;
                answerIndex += 1;

                currentTime = currentTime + remainTime;
                subject = tp[0];
                remainTime = Integer.parseInt(tp[2]);
                continue;

            } else{
                //
                answer[answerIndex] = subject;
                answerIndex += 1;

                currentTime = newTime;
                remainTime = newRemain;
                subject = plans[index][0];
            }

            index+=1;

        }


        while(!job.isEmpty()) {
            String[]  yahoo = job.pop();
            answer[answerIndex] = yahoo[0];
            answerIndex += 1;
        }

        System.out.println("ans = " + Arrays.toString(answer));
    }
}
