package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/2304
 */
public class MakeCeil implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int ct = Integer.parseInt(reader.readLine());

        List<ColumnInfo> store = new ArrayList<>();

        int maxHeightLoc = -1;
        int maxHeight = -1;
        int startLoc = 10000;
        int endLoc = 0;
        for (int i = 0; i < ct; i++) {
            String[] input = reader.readLine().split(" ");
            int loc = Integer.parseInt(input[0]);
            int height = Integer.parseInt(input[1]);

            if (maxHeight < height) {
                maxHeight = height;
                maxHeightLoc = loc;
            }

            startLoc = Math.min(loc, startLoc);
            endLoc = Math.max(loc, endLoc);

            store.add(new ColumnInfo(loc, height));

        }

        store.sort(new Comparator<ColumnInfo>() {
            @Override
            public int compare(ColumnInfo o1, ColumnInfo o2) {
                return o1.loc - o2.loc;
            }
        });

        // 왼쪽
        int answer = 0;
        int gizunHeight = store.get(0).height;
        int gizunLoc = store.get(0).loc;
        int index = 0;
        while (index < store.size() && store.get(index).loc <= maxHeightLoc) {
            if (store.get(index).height >= gizunHeight) {
                // 이전까지 계산한다.
//                System.out.println(store.get(index).loc + " : " + gizunLoc + " : " + gizunHeight);
//                System.out.println(answer);

                answer += (store.get(index).loc - gizunLoc) * gizunHeight;
                gizunHeight = store.get(index).height;
                gizunLoc = store.get(index).loc;
            }
            index += 1;
        }

        // 오른쪽
        gizunHeight = store.get(store.size() - 1).height;
        gizunLoc = store.get(store.size() - 1).loc;
        index = store.size() - 1;
        while (index >= 0 && store.get(index).loc >= maxHeightLoc) {
            if (store.get(index).height >= gizunHeight) {
//                System.out.println(store.get(index).loc + " : " + gizunLoc);
//                System.out.println(answer);
                // 이전까지 계산한다.
                answer += (gizunLoc - store.get(index).loc) * gizunHeight;
                gizunHeight = store.get(index).height;
                gizunLoc = store.get(index).loc;
            }
            index -= 1;
        }

        System.out.println(answer + maxHeight);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class ColumnInfo {

        int loc;
        int height;

        public ColumnInfo(int loc, int height) {
            this.loc = loc;
            this.height = height;
        }
    }
}
