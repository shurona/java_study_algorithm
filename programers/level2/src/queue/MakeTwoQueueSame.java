package queue;

import utils.AlgoStudy;

import java.util.LinkedList;
import java.util.Queue;

public class MakeTwoQueueSame implements AlgoStudy {
    public int solution(int[] queue1, int[] queue2) {
        long oneSum = 0;
        long twoSum = 0;

        LinkedList<Integer> queueOne = new LinkedList<>();
        for (int q1 : queue1) {
            queueOne.add(q1);
            oneSum += q1;
        }

        LinkedList<Integer> queueTwo = new LinkedList<>();
        for (int q2 : queue2) {
            queueTwo.add(q2);
            twoSum += q2;
        }

        int targetNumber = (int)((oneSum + twoSum) / 2);
        int count = 0;
        int maxCount = queue1.length + queue2.length;
        System.out.println(queueOne + " : " + queueTwo);
        while (oneSum != twoSum) {
            if (count == maxCount + 1) {
                count  = -1;
                break;
            }
            Integer pollValue;
            if (oneSum > twoSum) {
                pollValue = queueOne.poll();
                queueTwo.add(pollValue);
                oneSum -= pollValue;
                twoSum += pollValue;
            } else {
                pollValue = queueTwo.poll();
                queueOne.add(pollValue);
                twoSum -= pollValue;
                oneSum += pollValue;
            }
            System.out.println(queueOne + " : " + queueTwo);
            count +=1;
        }

//        System.out.println(queueOne + " : " + queueTwo);
        System.out.println(targetNumber);
        return count;
    }

    @Override
    public void init() {
        int[] queue1 = {1, 1, 1, 1};
        int[] queue2 = {4, 8};

        int output = solution(queue1, queue2);
        System.out.println(output);
    }
}
