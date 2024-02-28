package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ScovilleScale {
    public void solution() {

        int[] scoville = { 1, 1 };
//        int K = 7;
        int K = 2;
        int answer = 0;

        PriorityQueue<Integer> scovilleHeap = new PriorityQueue<>();

        for (int tp : scoville) {
            scovilleHeap.add(tp);
        }

        int firstScoville;
        int secondScoville;
        int newScoville;
        while (true) {
            firstScoville = scovilleHeap.poll();

            if (firstScoville >= K) {
                break;
            }

            secondScoville = scovilleHeap.poll();

            newScoville = firstScoville + secondScoville * 2;
            answer +=1;

            scovilleHeap.add(newScoville);

            if (scovilleHeap.size() == 1) {
                if (newScoville > K) {
                    //
                } else {
                    answer = -1;
                }
                break;
            }
        }

        System.out.println("정답 : " + answer);
        System.out.println(scovilleHeap);

    }

    public void anotherSolution() {
        int[] scoville = { 1, 2, 3, 9, 10, 12 };
        int K = 7;
//        int K = 2;
        int answer = 0;

        PriorityQueue<Integer> scovilleHeap = new PriorityQueue<>();

        for (int tp : scoville) {
            scovilleHeap.add(tp);
        }

        Comparator<Integer> ap = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-  o1;
            }
        };

        Arrays.sort(Arrays.stream(scoville).boxed().toArray(Integer[]::new), ap);

        System.out.println(Arrays.toString(scoville));
    }
}
