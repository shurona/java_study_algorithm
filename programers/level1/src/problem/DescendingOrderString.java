package problem;

import java.util.Arrays;
import java.util.Comparator;

public class DescendingOrderString {
    public String solution(String s) {
        String answer = "";

        Character[] tp = new Character[s.length()];


        for (int i = 0; i < s.length(); i++) {
            tp[i] = s.charAt(i); // 각 문자를 String으로 변환하여 배열에 저장
        }

        Arrays.sort(tp, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                if (Character.isUpperCase(o1) && Character.isLowerCase(o2)) {
                    return 1;
                } else if (Character.isUpperCase(o2) && Character.isLowerCase(o1)) {
                    return -1;
                }
                return o2.compareTo(o1);
            }
        });


        StringBuilder sb = new StringBuilder();
        for (Character str : tp) {
            sb.append(str);
        }

        return sb.toString();
    }

    public void init() {
        String output = solution("Zbcdefg");
        System.out.println("output = " + output);

    }
}
