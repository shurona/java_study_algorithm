package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/17615
 */
public class CollectBallOneWay implements BaekAlgoStudy {

    public static int collectFront(char color, String input) {

        int ct = 0;
        int middleIndex = input.length() + 1;
        char endChar = input.charAt(0);
        // 같은 지 확인한다.
        for (int i = 1; i < input.length(); i++) {
            if (endChar != input.charAt(i)) {
                middleIndex = i;
                break;
            }
        }

        for (int i = middleIndex; i < input.length(); i++) {
            if (color == input.charAt(i)) {
                ct += 1;
            }
        }

        return ct;
    }


    public static int collectBack(char color, String input) {

        int ct = 0;
        int middleIndex = -1;
        char endChar = input.charAt(input.length() - 1);
        // 같은 지 확인한다.
        for (int i = input.length() - 2; i >= 0; i--) {
            if (endChar != input.charAt(i)) {
                middleIndex = i;
                break;
            }
        }

        for (int i = middleIndex; i >= 0; i--) {
            if (color == input.charAt(i)) {
                ct += 1;
            }
        }

        return ct;
    }

    public void solution() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        String input = reader.readLine();

        int r = collectBack('R', input);
        int b = collectBack('B', input);
        int rf = collectFront('R', input);
        int bf = collectFront('B', input);
//        System.out.println(r + " : " + b);
//        System.out.println(rf + " : " + bf);

        System.out.println(Math.min(Math.min(r, b), Math.min(rf, bf)));

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
