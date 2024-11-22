package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import utils.BaekAlgoStudy;

public class AntHappiness implements BaekAlgoStudy {

    public void printAnswer(Map<Integer, Map<String, TreeSet<String>>> graph, int loc,
        String before, String prefix) {

        if (graph.getOrDefault(loc, null) == null) {
            return;
        }

        String step = "--";

        TreeSet<String> nodes = graph.get(loc).get(before);

        if (nodes == null) {
//            System.out.println(loc + " : " + before + " : " + prefix);
            return;
        }

        for (String node : nodes) {
            System.out.println(prefix + node);
            printAnswer(graph, loc + 1, before + "-" + node, prefix + step);
        }

    }

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Map<String, TreeSet<String>>> graph = new HashMap<>();

        try {
            String inputLine = reader.readLine();

            for (int i = 0; i < Integer.parseInt(inputLine); i++) {
                String input = reader.readLine();
                String[] data = input.split(" ");
                int depthSize = Integer.parseInt(data[0]);
                String before = "none";
                for (int depth = 1; depth <= depthSize; depth++) {
                    // 없는 depth면 넣어준다.
                    if (graph.getOrDefault(depth, null) == null) {
                        graph.put(depth, new HashMap<>());
                    }

                    String current = data[depth];
                    // depth에 없는 String이면 넣어준다.
                    if (graph.get(depth).getOrDefault(before, null) == null) {
                        graph.get(depth).put(before, new TreeSet<>());
                    }

                    graph.get(depth).get(before).add(current);
                    before = before + "-" + current;

                }
            }

            printAnswer(graph, 1, "none", "");

//            System.out.println(graph);

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
