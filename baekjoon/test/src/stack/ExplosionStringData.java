package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/9935
 */
public class ExplosionStringData implements BaekAlgoStudy {


    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split("");
        String bombString = reader.readLine();

        Stack<String> stack = new Stack<>();
//        for (int i = line.length - 1; i >= 0; i--) {
//            stack.add(line[i]);
//        }

        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < line.length) {
            stack.add(line[index]);
//            System.out.println(stack);
            if (stack.size() < bombString.length()) {
                index += 1;
                continue;
            }
            if (stack.peek().equals(String.valueOf(bombString.charAt(bombString.length() - 1)))) {
                List<String> list = new ArrayList<>();
                for (int i = bombString.length() - 1; i >= 0; i--) {
                    String st = stack.pop();
                    String bm = String.valueOf(bombString.charAt(i));
                    list.add(st);
                    if (!st.equals(bm)) {
                        for (int j = list.size() - 1; j >= 0; j--) {
                            stack.add(list.get(j));
                        }
                        break;
                    }

                }
            }
            index += 1;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.reverse());

        }

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
