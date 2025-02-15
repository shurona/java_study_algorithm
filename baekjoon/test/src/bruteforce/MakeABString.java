package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/12919
 */
public class MakeABString implements BaekAlgoStudy {

    static String input;
    static int answer = 0;

    public static void dfs(String data, HashSet<String> store) {
        if (data.length() >= input.length()) {
//            System.out.println(data);
            if (data.equals(input)) {
                answer = 1;
            }
            return;
        }

        // a
        String aPlus = data + "A";
        boolean aContains = input.contains(aPlus);
        boolean aa = input.contains(new StringBuffer(aPlus).reverse().toString());
        if (!store.contains(aPlus) && (aContains || aa)) {
//            System.out.println(aPlus);
            dfs(aPlus, store);
        }

        // b
        String bPlus = new StringBuffer(data + "B").reverse().toString();
        boolean bContains = input.contains(bPlus);
        boolean bb = input.contains(data + "B");
        if (!store.contains(bPlus) && (bContains || bb)) {
//            System.out.println(bPlus);
            dfs(bPlus, store);
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String first = reader.readLine();
        // 목표
        input = reader.readLine();

        HashSet<String> store = new HashSet<>();
        store.add(first);

        dfs(first, store);

        System.out.println(answer);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
