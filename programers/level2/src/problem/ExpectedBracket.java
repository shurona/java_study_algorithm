package problem;

import utils.AlgoStudy;

public class ExpectedBracket implements AlgoStudy {
    private boolean checkIn(int check, int left, int right) {
        if (check < right + 1 && check >= left + 1) {
            return true;
        }
        return false;
    }

    public int solution(int n, int a, int b)
    {
        int answer = 0;

        int step = (int) (Math.log(n) / Math.log(2));

        int now = n;
        for (int i = 1; i < step + 1; i++) {
            now /= 2;
            System.out.println(i + " " + now);
            boolean success = false;
            int range = n / now;
            for (int jump = 0; jump < now; jump++) {
                System.out.println(jump * range + " : " + (jump + 1) * range);
                if(checkIn(a, jump * range, (jump+1) * range) && checkIn(b, jump * range, (jump+1) * range)) {
                    answer = i;
                    success = true;
                    break;
                }
            }
            if (success) {
                break;
            }
            System.out.println();
        }

        return answer;
    }

    @Override
    public void init() {

//        int n = 8;
//        int a = 4;
//        int b = 7;

        int n = 16;
        int a = 1;
        int b = 9;


        int solution = solution(n, a, b);

        System.out.println(solution);

    }
}
