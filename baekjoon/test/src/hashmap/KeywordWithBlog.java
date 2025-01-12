package hashmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import utils.BaekAlgoStudy;

public class KeywordWithBlog implements BaekAlgoStudy {

    public void solution() throws IOException {

        Map<String, Integer> store = new HashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();

        int keywordCt = firstLine[0];
        int writtenKeywordCt = firstLine[1];

        for (int i = 0; i < keywordCt; i++) {

            String keyword = reader.readLine();

            Integer ct = store.getOrDefault(keyword, 0);
            store.put(keyword, ct + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < writtenKeywordCt; i++) {
            String written = reader.readLine();
            Arrays.stream(written.split(","))
                .forEach(keyword -> {
                    Integer check = store.getOrDefault(keyword, -1);
                    if (check < 0) {
                        return;
                    } else if (check == 1) {
                        store.remove(keyword);
                    } else {
                        store.put(keyword, check - 1);
                    }
                });

//            System.out.println(store);
//            System.out.println(store.values().size());
            sb.append(store.values().size()).append("\n");
        }

        System.out.println(sb.toString());

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
