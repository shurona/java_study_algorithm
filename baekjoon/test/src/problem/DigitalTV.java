package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import utils.BaekAlgoStudy;

public class DigitalTV implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());

        List<String> store = new ArrayList<>();

        for (int i = 0; i < input; i++) {
            store.add(reader.readLine());
        }

        String kbs1 = "KBS1";
        String kbs2 = "KBS2";

        int loc = 0;
        StringBuilder sb = new StringBuilder();
        while (!store.get(0).equals(kbs1)) {
            String currentChannel = store.get(loc);
            if (!currentChannel.equals(kbs1)) {
                sb.append(1);
                loc++;
            } else if (currentChannel.equals(kbs1)) {
                sb.append(4);
                String tp = store.get(loc - 1);
                store.set(loc - 1, kbs1);
                store.set(loc, tp);
                loc--;
            }

        }
//
        while (!store.get(1).equals(kbs2)) {
            String currentChannel = store.get(loc);
            if (!currentChannel.equals(kbs2)) {
                sb.append(1);
                loc++;
            } else if (currentChannel.equals(kbs2)) {
                sb.append(4);
                String tp = store.get(loc - 1);
                store.set(loc - 1, kbs2);
                store.set(loc, tp);
                loc--;
            }

        }

//        System.out.println(store);
        System.out.println(sb);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
