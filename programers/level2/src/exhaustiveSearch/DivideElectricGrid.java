package exhaustiveSearch;

import java.util.*;

public class DivideElectricGrid {
    public Set<Integer> checkConnection(int[][] wires, boolean[] visit, int start, int depth, Set<Integer> yahoo) {
        if (yahoo.contains(start)) {
            return yahoo;
        }
        yahoo.add(start);

//        if (start == 4) {
//            System.out.println(Arrays.deepToString(wires) + "  start : " + start);
//        }

        for (int i = 0; i < wires.length; i++) {
            int[] wire = wires[i];
            if (!visit[i]) {
                if (start == wire[0] || start == wire[1]) {
                    // 찾으면 다시 거기부터 시작한다.
                    visit[i] = true;
                    checkConnection(wires, visit, wire[1], depth + 1, yahoo);
                    checkConnection(wires, visit, wire[0], depth + 1, yahoo);
                    visit[i] = false;
                }
            }
        }
        return yahoo;
    }

    public void solution() {
        int n = 9;
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};

//        int n = 4;
//        int[][] wires = {{1, 2}, {2, 3}, {3,4}};

//        int n = 7;
//        int[][] wires = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};

//        int n = 6;
//        int [][] wires = {{1, 2}, {3, 4}, {5, 6}, {1, 3}, {3, 5}};

//        int n = 4;
//        int[][] wires = {{1, 2}, {3, 4}, {2, 3}};

        Arrays.sort(wires, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
            }
        });

        boolean[] visitCheck =new boolean[wires.length];
        int[][] tempWires = new int[wires.length - 1][2];
        int answer = 9999;
        for (int i = 0; i < wires.length; i++) {
            int tempIndex = 0;
            for (int j = 0; j < wires.length; j++) {
                if (i == j) {
                    continue;
                }
                tempWires[tempIndex] = wires[j];

                tempIndex+=1;
            }

            Set<Integer> ddd = checkConnection(tempWires, visitCheck, tempWires[0][0], 0, new HashSet<Integer>());

            int diff = Math.abs((n - ddd.size()) - ddd.size());
            answer = Math.min(answer, diff);
        }

        System.out.println("Complete solution : " + answer);
    }
}
