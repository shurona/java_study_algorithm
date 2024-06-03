package queue;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.HashMap;

public class DiscountEvent implements AlgoStudy {
    public int solution(String[] want, int[] number, String[] discount) {

        HashMap<String, Integer> wantGoods = new HashMap<>();
        HashMap<String, Integer> tpGoods = new HashMap<>();
        for (int index = 0; index < want.length; index++) {
            wantGoods.put(want[index], number[index]);
            tpGoods.put(want[index], number[index]);
        }
        System.out.println(wantGoods);

        int answer = 0;
        for (int i = 0; i <= discount.length - 10; i++) {
            int count = 0;
            for (int day = 0; day < 10; day++) {
                Integer isThisWant = tpGoods.getOrDefault(discount[i + day], null);
                if (isThisWant == null || isThisWant == 0) {
                    break;
                }
                tpGoods.put(discount[i + day], isThisWant - 1);
                count +=1;
            }
            if (count == 10) {
                answer += 1;
            }
            // init
            tpGoods.replaceAll((s, v) -> wantGoods.get(s));
        }
        return answer;
    }

    @Override
    public void init() {

        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        int answer = solution(want, number, discount);
        System.out.println("answer = " + answer);

    }
}
