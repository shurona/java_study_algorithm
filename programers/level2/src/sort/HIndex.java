package sort;

import java.util.Arrays;
import java.util.Comparator;

public class HIndex {
    public void solution() {
//        int[] citations = {3, 0, 6, 1, 5};
//        int[] citations = {8, 5, 3, 4, 25};
//        int[] citations = {9, 7, 6, 1, 2};
        int[] citations = {5, 6, 7};
//        int[] citations = {100};

        Integer[] tp = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        int answer = 0;

        Arrays.sort(tp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int index = 0;
        int count = 0;
        while (count <= tp[tp.length -1]) {
            if (index == tp.length ) {
                break;
            }

            // 만약 확인하는 count 값이 크면 증가시켜준다.
            if (tp[index] < count) {
                index +=1;
                continue;
            }

            // 남은 갯수와 count 를 비교한다.
            if (count <= (tp.length - index)) {
                answer = count;
            }
            count += 1;
        }

        System.out.println(Arrays.toString(tp));
        System.out.println(answer);
    }

    public void anothersolution() {
//        int[] citations = {8, 5, 3, 4, 25};
        int[] citations = {5, 6, 7};
//        int[] citations = {10, 10, 10, 10};
        Arrays.sort(citations);

        int max = 0;
        for (int i = citations.length - 1; i > -1; i--) {
            int min = (int)Math.min(citations[i], citations.length - i);
            if(max < min) max = min;
        }

        System.out.println(max);
    }
}
