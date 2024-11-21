package StringControl;

import utils.AlgoStudy;

/*
    문제 링크
    https://school.programmers.co.kr/learn/courses/30/lessons/12951#
 */
public class JaDenCase implements AlgoStudy {

    public String solution(String s) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String uppderCase = lowerCase.toUpperCase();

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == ' ') {
                isFirst = true;
                sb.append(" ");
                continue;
            }

            if (isFirst) {
                if (lowerCase.contains(String.valueOf(current))) {
                    sb.append(Character.toUpperCase(current));
                } else {
                    sb.append(current);
                }

            } else {
                if (uppderCase.contains(String.valueOf(current))) {
                    sb.append(Character.toLowerCase(current));
                } else {
                    sb.append(current);
                }
            }

            // 처음이 아님을 알려준다.
            isFirst = false;

        }

        // System.out.println(UppderCase);

        return sb.toString();
    }

    @Override
    public void init() {
        solution("3people unFollowed me");
    }
}
