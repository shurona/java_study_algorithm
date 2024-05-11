package problem;

import java.util.*;

public class PersonalityType {
    public void solution() {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};

        List<HashMap<String, Integer>> personalList = new ArrayList<>();
        Map<String, Integer> matchNumber = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            personalList.add(new HashMap<>());
        }

        personalList.get(0).put("R", 0);
        personalList.get(0).put("T", 0);
        personalList.get(1).put("C", 0);
        personalList.get(1).put("F", 0);
        personalList.get(2).put("J", 0);
        personalList.get(2).put("M", 0);
        personalList.get(3).put("A", 0);
        personalList.get(3).put("N", 0);

        matchNumber.put("R", 0);;
        matchNumber.put("T", 0);;
        matchNumber.put("C", 1);;
        matchNumber.put("F", 1);;
        matchNumber.put("J", 2);;
        matchNumber.put("M", 2);;
        matchNumber.put("A", 3);;
        matchNumber.put("N", 3);;

        for (int index = 0 ; index < survey.length; index++) {
            int score = choices[index];
            // 5 6 7
            if (score > 4) {
                String two = String.valueOf(survey[index].charAt(1));
                Integer twoScore = personalList.get(matchNumber.get(two)).get(two);
                personalList.get(matchNumber.get(two)).put(two, twoScore + (score - 4));
            }

            // 1 2 3
            if (score < 4) {
                String one = String.valueOf(survey[index].charAt(0));
                Integer oneScore = personalList.get(matchNumber.get(one)).get(one);
                personalList.get(matchNumber.get(one)).put(one, oneScore + (4 - score));

            }

        }

        StringBuilder sb = new StringBuilder();
        for (HashMap<String, Integer> mapInfo : personalList) {
            List<Integer> tp = new ArrayList<>();
            List<String> str = new ArrayList<>();
            Set<String> strings = mapInfo.keySet();
            for (String string : strings) {
                str.add(string);
                tp.add(mapInfo.get(string));
            }
            if (tp.get(0) >= tp.get(1)) {
                sb.append(str.get(0));
            } else {
                sb.append(str.get(1));
            }
        }

        System.out.println("personalList = " + sb.toString());
    }
}
