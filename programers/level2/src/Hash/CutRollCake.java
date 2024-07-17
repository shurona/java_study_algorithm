package Hash;

import utils.AlgoStudy;

import java.util.HashMap;

public class CutRollCake implements AlgoStudy {

    HashMap<Integer, Integer> leftTopping = new HashMap<>();
    HashMap<Integer, Integer> rightTopping = new HashMap<>();

    public int solution(int[] topping) {

        int answer = 0;
        for (int i = 0; i < 1; i++) {
            leftTopping.put(topping[0], 1);
        }

        for (int i = 1; i < topping.length; i++) {
            Integer output = rightTopping.getOrDefault(topping[i], 0);

            rightTopping.put(topping[i], output + 1);
        }

        if (leftTopping.keySet().size() == rightTopping.keySet().size()) {
            answer +=1;
        }

        for (int i = 1; i < topping.length; i++) {

            int currentTopping = topping[i];

            Integer leftInputCount = leftTopping.getOrDefault(currentTopping, 0);
            leftTopping.put(currentTopping, leftInputCount + 1);

            Integer rightToppingCount = rightTopping.get(currentTopping);
            if (rightToppingCount == 1) {
                rightTopping.remove(currentTopping);
            } else {
                rightTopping.put(currentTopping, rightToppingCount - 1);
            }

            if (leftTopping.keySet().size() == rightTopping.keySet().size()) {
                answer +=1;
            }

        }


        return answer;
    }

    @Override
    public void init() {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};

        int solution = solution(topping);
        System.out.println("답은 : " + solution);
    }
}
