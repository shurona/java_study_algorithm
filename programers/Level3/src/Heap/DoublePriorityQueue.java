package Heap;

import java.util.*;

public class DoublePriorityQueue {
    public void solution() {
//        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations = { "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};

        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });




        for (String operation : operations) {
            String action = operation.split(" ")[0];
            Integer number = Integer.valueOf(operation.split(" ")[1]);

            switch (action) {
                case "I":
                    maxQueue.add(number);
                    minQueue.add(number);
                    break;
                case "D":
//                     최소값 추출
                    if (number == -1) {
                        Integer pollMin = minQueue.poll();
                        maxQueue.remove(pollMin);
                    }
                    // 최대값 추출
                    else {
                        Integer pollMax = maxQueue.poll();
                        minQueue.remove(pollMax);
                    }
                    break;

                default:
                    System.out.println("야후");
            }
        }

        int[] answer;
        if (maxQueue.isEmpty()) {
            answer = new int[]{0, 0};
        } else {
            answer = new int[]{maxQueue.poll(), minQueue.poll()};
        }

        System.out.println(maxQueue);
        System.out.println(minQueue);

        System.out.println(Arrays.toString(answer));
    }
}
