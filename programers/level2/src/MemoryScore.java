import java.util.HashMap;
import java.util.ArrayList;

public class MemoryScore {
    void memoryStart() {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3,};

        String[][] Photo = {
                {"may", "kein", "kain", "radi"},
                {"may", "kein", "brin", "deny"},
                {"kon", "kain", "may", "coni"}
        };

        HashMap<String, Integer> match = new HashMap<String, Integer>();

        for(int index = 0; index < name.length; index++) {
            match.put(name[index], yearning[index]);
        }
        ArrayList<Integer> tp = new ArrayList<Integer>();

        for(String[] ph: Photo) {
            int as = 0;
            for(String pt: ph) {

                int memory = match.getOrDefault(pt, 0);
                as += memory;

            }
            tp.add(as);
        }

        System.out.println(tp.toArray());

        tp.stream().mapToInt(i -> i).toArray();
    }

}
