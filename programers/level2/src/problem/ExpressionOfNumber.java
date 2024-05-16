package problem;

public class ExpressionOfNumber {
    public void solution() {

        int n = 15;

        int left = 1;
        int gizun = left;
        int sum = left;
        boolean reset = false;
        while (left <= n) {
            if (reset) {
//                System.out.println("sum = " + sum);
                left += 1;
                gizun = left;
                sum = left;
                reset = false;
            }
            // 연속합을 구한다.
            gizun += 1;
            sum += gizun;

            if (sum == n) {
                reset = true;
            } else if (sum > n) {
                reset = true;
            }
        }

        System.out.println("ExpressionOfNumber.solution");
    }
}
