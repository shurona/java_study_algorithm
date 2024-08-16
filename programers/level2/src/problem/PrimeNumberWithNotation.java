package problem;

import utils.AlgoStudy;

public class PrimeNumberWithNotation implements AlgoStudy {
    private boolean checkPrimeNumber(Long input) {
        if (input == 1) {
            return false;
        }

        if (input == 0) {
            return false;
        }

        Long until = input / 2;
        for (int start = 2; start < until; start++) {
            if (input % start == 0) {
                return false;
            }
        }
        return true;
    }

    public int solution(int n, int k) {
        // 진법 변경
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }

        String afterChange = sb.reverse().toString();
        System.out.println(afterChange);


        int answer = 0;
        sb.setLength(0);
        for (int index = 0; index < afterChange.length(); index++) {
            if (afterChange.charAt(index) == '0' && sb.length() > 0) {
                Long mid = Long.parseLong(sb.toString());
                System.out.println(mid);
                // 소수인지 확인한다.
                if (checkPrimeNumber(mid)) {
                    answer += 1;
                }
                sb.setLength(0);
            }
            sb.append(afterChange.charAt(index));
        }

        if (sb.length() > 0) {
            Long mid = Long.parseLong(sb.toString());
            if (checkPrimeNumber(mid)) {
                answer += 1;
            }
//            System.out.println(mid);
        }

        return answer;
    }

    @Override
    public void init() {
        int solution = solution(797161, 3);
        System.out.println("정답은 : " + solution);
    }
}
