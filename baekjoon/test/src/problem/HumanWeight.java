package problem;

import utils.BaekAlgoStudy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HumanWeight implements BaekAlgoStudy {

    /**
     * 5
     * 55 185
     * 58 183
     * 88 186
     * 60 175
     * 46 155
     */
    public void solution() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(bufferedReader.readLine());

        ArrayList<String> inputs = new ArrayList<>();

        while (ct > 0) {

            inputs.add(bufferedReader.readLine().strip());
            ct--;
        }

//
//        inputs.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                String[] o11 = o1.split(" ");
//                String[] o22 = o2.split(" ");
//
//                int o1Weight = Integer.parseInt(o11[0]);
//                int o2Weight = Integer.parseInt(o22[0]);
//                int o1Height = Integer.parseInt(o11[1]);
//                int o2Height = Integer.parseInt(o22[1]);
//
//                if (o1Weight > o2Weight && o1Height > o2Height) {
//                    return -1;
//                }
//
//                if ((o1Weight + o1Height) > (o2Weight + o2Height)) {
//                    return -1;
//                } else {
//                    return 1;
//                }
//            }
//        });

        int[] answer = new int[inputs.size()];
        for (int i = 0; i < inputs.size(); i++) {
            int rank = 1;
            String[] gizun = inputs.get(i).split(" ");
            for (int j = 0; j < inputs.size(); j++) {
                String[] compare = inputs.get(j).split(" ");

                if (Integer.parseInt(compare[0]) > Integer.parseInt(gizun[0])
                        && Integer.parseInt(compare[1]) > Integer.parseInt(gizun[1])) {
                    rank++;
                }
            }
            answer[i] = rank;
        }

        StringBuilder output = new StringBuilder();
        for (int i : answer) {
            output.append(i).append(" ");
        }

        System.out.println(output);
        bufferedReader.close();

    }


    @Override
    public void init() throws IOException {
        solution();
    }
}
