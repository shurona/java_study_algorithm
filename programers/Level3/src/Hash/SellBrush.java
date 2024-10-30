package Hash;

import java.util.HashMap;
import utils.AlgoLevel3Study;

public class SellBrush implements AlgoLevel3Study {

    private final String blank = "-";
    private HashMap<String, String> checkReferral = new HashMap<>();
    private HashMap<String, Integer> store = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        int[] answer = new int[enroll.length];

        // 초기의 세팅
        for (int i = 0; i < enroll.length; i++) {
            checkReferral.put(enroll[i], referral[i]);
            store.put(enroll[i], i);
        }

        // System.out.println(checkReferral);

        // seller 계산
        for (int i = 0; i < seller.length; i++) {
            String person = seller[i];
            int price = amount[i] * 100;

            do {
                // 먼저 10보다 큰지 확인한다.
                if (price < 10) {
                    answer[store.get(person)] += price;
                    break;
                }

                int originPrice = price;
                price = price / 10;

                answer[store.get(person)] += originPrice - price;
                person = checkReferral.get(person);

                if (person.equals(blank)) {
                    break;
                }

            } while (price > 0);
        }

        return answer;
    }

    @Override
    public void init() {

    }
}
