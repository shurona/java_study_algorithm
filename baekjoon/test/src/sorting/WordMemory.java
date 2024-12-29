package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.BaekAlgoStudy;

public class WordMemory implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        int length = Integer.parseInt(input[0]);
        int size = Integer.parseInt(input[1]);

        Map<String, Integer> store = new HashMap<>();

        for (int i = 0; i < length; i++) {
            String word = reader.readLine();

            if (word.length() >= size) {
                store.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        List<String> arr = new ArrayList<>(store.keySet());
        arr.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (store.get(o1) == store.get(o2)) {
                    if (o1.length() == o2.length()) {
                        return o1.compareTo(o2);
                    } else {
                        return o2.length() - o1.length();
                    }
                } else {
                    return store.get(o2) - store.get(o1);
                }
            }
        });

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s).append("\n");
            
        }

        System.out.println(sb.toString());
        reader.close();


    }

    @Override
    public void init() throws IOException {
        solution();
    }


}
