package problem;

import utils.BaekAlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class TriangleAndSides implements BaekAlgoStudy {

    public void solution() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            ArrayList<String> inputs = new ArrayList<>();
            String input = "";
            while (!input.equals("0 0 0")) {
                input = bufferedReader.readLine();
                if (input.equals("0 0 0")) {
                    break;
                }
                inputs.add(input);
            }
            bufferedReader.close();



            PriorityQueue<Integer> tp;
            for (String sss : inputs) {
                tp = new PriorityQueue<>();
                for (String s : sss.split(" ")) {
                    tp.add(Integer.parseInt(s));
                }

                int now = tp.size();
                int[] firstCheck = new int[2];
                for (int i = 0 ; i < 2; i++) {
                    firstCheck[i] = tp.poll();
                }

                int last = tp.poll();

                // 안되는 경우 패스
                if (firstCheck[0] + firstCheck[1] <= last) {
                    System.out.println("Invalid");
                    continue;
                }

                if (firstCheck[0] == firstCheck[1]) {
                    if (firstCheck[1] == last) {
                        System.out.println("Equilateral");
                    } else {
                        System.out.println("Isosceles");
                    }
                } else {
                    if (firstCheck[1] == last) {
                        System.out.println("Isosceles");
                    } else {
                        System.out.println("Scalene");
                    }
                }
            }
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
