package stack;

import java.util.*;

public class ProcessJob {
    public void solution() {
        int[] priorities = { 2, 1, 3, 2 };
        int location = 2;
        int answer = 0;
        ArrayList<Integer> output = new ArrayList<>();

        Object[] newArray = Arrays.stream(priorities).boxed().map(i -> -i).sorted().map(i -> -i).toArray();

        Queue<Integer> queue = new LinkedList<>();

        // init arr
        for (int i = 0; i < priorities.length; i++) {
            queue.add(i);
        }

        int now = 0;

        int currentProcess;
        while(!queue.isEmpty()) {

            currentProcess =  queue.poll();

            // 현재 작동하는 것이 우선순위가 같으면
            if (priorities[currentProcess] == (Integer) (newArray[now])) {
                now+=1;
                output.add(currentProcess);
                if(currentProcess == location) answer = now;
            } else {
                queue.add(currentProcess);
            }

        }

        //arr 갖고
//        System.out.println("queue = " + output);
    }
}
