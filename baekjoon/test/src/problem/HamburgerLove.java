package problem;

import utils.BaekAlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HamburgerLove implements BaekAlgoStudy {

    public void solution() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String input = bufferedReader.readLine();

        String[] sp = input.split(" ");

        int min = Math.min(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]));
        int max = Math.max(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]));

        int time = Integer.parseInt(sp[2]);

        int minCount = time / min;

        int answer = minCount;
        int cola = time;
        for (int i = minCount; i >=0; i--) {
            for (int j = 0; j <= i; j++) {
                int bigBurgerCount = max * j;
                int smallBurgerCount = min * (i - j);
                if (bigBurgerCount + smallBurgerCount > time) {
                    break;
                }

//                1001 2192 10000
                if (time - (bigBurgerCount + smallBurgerCount) < cola) {
//                    System.out.println(j + " === " + (i - j) + " ===" + i);
                    answer  = j + (i - j);
                    cola = time - (bigBurgerCount + smallBurgerCount);
                } else if (time - (bigBurgerCount + smallBurgerCount) == cola) {
                    answer  = Math.max(answer, j + (i - j));
                }
            }
        }
        System.out.println(answer + " " + cola);

        bufferedReader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
