package Hash;


import java.util.*;

import static java.util.stream.Collectors.*;

public class ChooseClothes {
    public void choose() {
        String[][] clothes= {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        Map<String, List<String>> output = new HashMap<>();

        for (String[] item : clothes) {
            String key = item[1];
            String value = item[0];

            if (!output.containsKey(key)) {
                output.put(key, new ArrayList<>());
            }

            output.get(key).add(value);
        }

        int answer = 1;
        for (String key : output.keySet()) {
            int curr = output.get(key).size() + 1;
            answer *= curr;

        }

        System.out.println(answer - 1);
    }

    public int anotherSolution() {
        {
            String[][] clothes= {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
            int answer = Arrays.stream(clothes)
                    .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                    .values()
                    .stream()
                    .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;

            System.out.println(answer);

            return answer;
        }
    }
}
