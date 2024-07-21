package problem;

import utils.AlgoStudy;

import java.util.*;

public class PlayAloneMaster implements AlgoStudy {

    public int solution(int[] cards) {
        int[] dp = Arrays.copyOf(cards, cards.length);

        ArrayList<ArrayList<Integer>> boxSet = new ArrayList<>();

        int zeroCount = 0;
        ArrayList<Integer> tp = new ArrayList<>();
        int currentIndex = cards[0] - 1;
        while (zeroCount < cards.length) {
            // 다음 인덱스 확인한다.
            int nextIndex = dp[currentIndex] - 1;
            // dp에서 0인지 확인
            if (nextIndex < 0) {
                boxSet.add(tp);
                tp = new ArrayList<>();
                System.out.println();
                for (int i = 0; i < dp.length; i++) {
                    if (dp[i] != 0) {
                        currentIndex = i;
                        break;
                    }
                }
            } else {
                tp.add(currentIndex + 1);
                dp[currentIndex] = 0;
                currentIndex = nextIndex;
                zeroCount +=1;
            }
//            System.out.println(currentIndex);
//            System.out.println(Arrays.toString(dp));
//            System.out.println(tp);
        }

        if (!tp.isEmpty()) {
            boxSet.add(tp);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);



        if (boxSet.size() < 2) {
            return 0;
        }

        ArrayList<Integer> yahoo = new ArrayList<>();

        for (int i = 0; i < boxSet.size(); i++) {
            yahoo.add(boxSet.get(i).size());
        }


        yahoo.sort(Comparator.reverseOrder());

//
//        Arrays.sort(yahoo, new Comparator<Integer>(){
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });

        return yahoo.get(0) * yahoo.get(1);
    }

    @Override
    public void init() {
//        int[] cards = {2, 3, 1, 5, 6, 4, 8, 9, 7, 11, 12, 10};
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        int solution = solution(cards);
        System.out.println("정답은 : " + solution);
    }
}
