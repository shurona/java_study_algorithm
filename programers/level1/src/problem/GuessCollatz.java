package problem;

public class GuessCollatz {
    public void solution() {
        int answer = -1;
        int num = 1;

        int count = 0;
        while (count < 500) {

            if (num == 1) {
                break;
            }

            if (num % 2 == 1) {
                num = num * 3 + 1;
            } else {
                num /= 2;
            }

            count +=1;
        }

        if (count < 500) {
            answer = count;
        }

        System.out.println("정답은 : " + answer);

    }
}
