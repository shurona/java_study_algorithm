package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import utils.BaekAlgoStudy;

public class SimpleCardGame implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Queue<Integer> store = new LinkedList<>();

        int number = Integer.parseInt(reader.readLine());

        for (int i = 1; i <= number; i++) {
            store.add(i);
        }

        while (store.size() > 1) {
            Integer trash = store.poll();
            Integer save = store.poll();

            store.add(save);
        }

        System.out.println(store.poll());

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
