package problem;

import java.util.ArrayList;
import java.util.List;
import utils.AlgoStudy;


/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/70129
 */
public class BinaryConvert implements AlgoStudy {

    List<Integer> answer = new ArrayList<>();
    int zeroCount = 0;

    public int deleteZero(String s) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                sb.append(1);
            } else {
                zeroCount += 1;
            }

        }
        return sb.toString().length();
    }

    public String convertBinary(int number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(number % 2);
            number = number / 2;
        }

        return sb.reverse().toString();
    }

    public int[] solution(String s) {
        int count = 0;
        while (!s.equals("1")) {
            s = convertBinary(deleteZero(s));
            count += 1;
            // System.out.println(s);
            // if(count > 3) {
            //     break;
            // }
        }

        answer.add(count);
        answer.add(zeroCount);

        return answer.stream().mapToInt(i -> i).toArray();

    }

    @Override
    public void init() {

    }
}
