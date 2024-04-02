package exhaustiveSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fatigue {

    List<List<Integer>> result = new ArrayList<>();

    public void permutation(List<Integer> startList, List<Integer> temp) {

        // 종료 기준
        if (temp.size() == startList.size()) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (Integer indexInfo : startList) {
            if (!temp.contains(indexInfo)) {
                temp.add(indexInfo);
                permutation(startList, temp);
                temp.remove(indexInfo);
            }
        }
    }

    public void solution() {

        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};

        // 123
        // 132
        // 213
        // 231
        // 312
        // 321

        List<Integer> tp = new ArrayList<>();
        for (int i = 0; i < dungeons.length; i++) {
            tp.add(i);
        }

        permutation(tp, new ArrayList<Integer>());

        int answer = -1;
        for (List<Integer> dp : result) {
            int tpK = 80;
            int subCheck = 0;
            for (Integer i : dp) {
                int[] dungeon = dungeons[i];
                // 피로도 확인 후 차감
                if (dungeon[0] <= tpK) {
                    tpK -= dungeon[1];
                    subCheck += 1;
                } else {
                    break;
                }
            }
            answer = Math.max(answer, subCheck);
        }

        System.out.println(answer);
    }


    // 다른 사람 풀이
    public int anotherDfs(int k, int[][] dungeons) {
        int cnt = 0;
        for(int[] d : dungeons) {
            // a는 필요 필요도, b 소모 피로도
            int a = d[0], b = d[1];
            // 필요 피로도가 현재 피로도 보다 이상인 경우에만 진입한다.
            if (a <= k) {
                // 이미 본 던전은 다시 안보도록 넘긴다.
                d[0] = 9999;
                cnt = Math.max(1 + anotherDfs(k - b, dungeons), cnt);
                d[0] = a;
            }
        }
        return cnt;
    }

    public void anotherSolution() {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};

        int output = anotherDfs(k, dungeons);
        System.out.println("output = " + output);

        System.out.println("k = " + k);

    }
}
