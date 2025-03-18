package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2179

    접근 방법
    정렬 이후에 자신 인덱스의 이후만 조사하면 되기 때문에 O(nlogn)이 가능하다.
    조건에 맞게 인덱스를 조사하기 위해 Index도 저장해주는 static class를 만들어서 같이 조건문으로 활용한다.
 */
public class SimilarWordTwo implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

        List<Data> store = new ArrayList<>();

        for (int i = 0; i < ct; i++) {
            store.add(new Data(reader.readLine(), i));
        }

        store.sort((o1, o2) -> o1.line.compareTo(o2.line));

        Data[] answer = new Data[2];
        int answerSim = 0;
        for (int i = 0; i < store.size(); i++) {
            int simCt = 0;
            Data sim = null;
            String gizun = store.get(i).line;
            for (int j = i + 1; j < store.size(); j++) {
                String forCp = store.get(j).line;
                int tpSimCt = 0;
                if (gizun.equals(forCp)) { // 같으면 패스
                    continue;
                }
                for (int index = 0; index < Math.min(gizun.length(), forCp.length()); index++) {
                    if (gizun.charAt(index) != forCp.charAt(index)) {
                        break;
                    }
                    tpSimCt += 1;
                }
                if (tpSimCt > simCt) {
                    sim = store.get(j);
                    simCt = tpSimCt;
                } else if (tpSimCt == simCt) {
                    if (sim != null && store.get(j).index < sim.index) {
                        sim = store.get(j);
                    }
                }
            }
//            System.out.println(store.get(i) + " : " + sim + " : " + simCt);
            if (sim != null) {
                Data before = sim.index < store.get(i).index ? sim : store.get(i);
                Data after = sim.index < store.get(i).index ? store.get(i) : sim;
                if (simCt > answerSim) {
                    answer[0] = before;
                    answer[1] = after;
                    answerSim = simCt;
                } else if (simCt == answerSim && answer[0] != null) {
                    if (before.index < answer[0].index) {
                        answer[0] = before;
                        answer[1] = after;
                    } else if (before.index == answer[0].index
                        && after.index < answer[1].index) {
                        answer[0] = before;
                        answer[1] = after;
                    }
                }
            }


        }

//        System.out.println(store);

        if (answer[0] == null) {
            System.out.println(store.get(0).line);
            System.out.println(store.get(1).line);
        } else {
            System.out.println(answer[0].line);
            System.out.println(answer[1].line);
        }

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Data {

        String line;
        int index;

        public Data(String line, int index) {
            this.line = line;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Data{" +
                "line='" + line + '\'' +
                ", index=" + index +
                '}';
        }
    }
}
