package search;

import utils.AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DiscountEmoticon implements AlgoStudy {
    PriorityQueue<EmoticonServiceSales> queue;
    int[] percentage = {10, 20, 30, 40};
    static class EmoticonServiceSales {
        int membership;
        int sales;

        public EmoticonServiceSales(int membership, int sales) {
            this.membership = membership;
            this.sales = sales;
        }

        @Override
        public String toString() {
            return "EmoticonServiceSales{" +
                    "membership=" + membership +
                    ", sales=" + sales +
                    '}';
        }
    }

    int yahoo = 0;
    public void checkDiscount(int[][] users, int[] emoticons, int[] currentDiscountRate) {
        int membership = 0;
        int sales = 0;
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for(int[] user: users){
            int userWithDiscount = user[0];
            int maxMoney = user[1];
            int sum = 0;
            ArrayList<Integer> dd = new ArrayList<>();
            dd.add(maxMoney);


            // 할인율이 이하면 바로 구입
            // maxMoney 초과하면 임티플 구입
            for (int i = 0; i < currentDiscountRate.length; i++) {
                if (userWithDiscount <= currentDiscountRate[i]) {
                    int d = (int)(emoticons[i] - (emoticons[i] * ((double)currentDiscountRate[i] / 100)));

                    dd.add(d);
                    sum += d;
                }
            }
            if (maxMoney <= sum) {
                membership+=1;

            } else {
                sales += sum;
                temp.add(dd);
            }
        }

        if (membership != 0 && sales != 0) {
//            System.out.println("currentDiscountRate = " + Arrays.toString(currentDiscountRate));
//            System.out.println("dd = " + dd);
//            System.out.println();
            queue.add(new EmoticonServiceSales(membership, sales));
//            yahoo++;
        }



    }


    public void dfs(int[][] users, int[] emoticons,  int[] emoticonRate, int start, int count) {
        if (count == emoticons.length) {
            if(yahoo == 2) return;
            checkDiscount(users, emoticons, emoticonRate);
            return;
        }


        for (int i = start + 1; i < emoticonRate.length; i++) {
            for (int per : percentage) {
                emoticonRate[i] = per;
                dfs(users, emoticons, emoticonRate, i, count + 1);
            }

        }


    }

    public int[] solution(int[][] users, int[] emoticons) {
        queue  = new PriorityQueue<>(new Comparator<EmoticonServiceSales>() {
            @Override
            public int compare(EmoticonServiceSales o1, EmoticonServiceSales o2) {
                if(o1.membership == o2.membership) return o2.sales - o1.sales;
                return o2.membership - o1.membership;
            }
        });

        int[] emoticonsRate = new int[emoticons.length];
        // 퍼센트와 emoticons 가격을 확인해본다.
        dfs(users, emoticons, emoticonsRate, -1, 0);

        System.out.println("queue = " + queue);
        EmoticonServiceSales output = queue.poll();

        if (output == null) {
            return new int[]{0, 0};
        }

        return new int[]{output.membership, output.sales};
    }

    @Override
    public void init() {
        int[] emoticons = {1000, 1000, 1000};
        int[][] users = {{1, 1200}};
//
//        int[] emoticons = {1300, 1500, 1600, 4900};
//
//        int[][] users = {
//                {40, 2900},
//                {23, 10000},
//                {11, 5200},
//                {5, 5900},
//                {40, 3100},
//                {27, 9200},
//                {32, 6900}
//        };
//        int[] emoticons = {7000, 9000};
//        int[][] users = {{40, 10000}, {25, 10000}};

        System.out.println(Arrays.toString(solution(users, emoticons)));
    }
}
