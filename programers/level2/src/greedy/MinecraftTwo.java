package greedy;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinecraftTwo implements AlgoStudy {
    static class Node {
        int loc;
        int point;

        int step;

        public Node(int loc, int point, int step) {
            this.loc = loc;
            this.point = point;
            this.step = step;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "loc=" + loc +
                    ", point=" + point +
                    ", step=" + step +
                    '}';
        }
    }

    PriorityQueue<Node> a = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.point - o1.point;
        }
    });

    public int solution(int[] picks, String[] minerals) {

        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];

        int answer = 0;

        int maxIndex = ((dia + iron + stone) * 5 / 5) + 1;


        int index = 0;
        int ct = 0;
        int damage = 0;
        int mineCt = 0;
        for (String mineral : minerals) {
            if (mineral.equals("diamond")) {
                damage += 25;
            } else if (mineral.equals("iron")) {
                damage += 5;
            } else {
                damage += 1;
            }



            if (mineCt == 5 * (dia + iron + stone)) {
                break;
            }
            mineCt +=1;

            ct +=1;
            if (ct == 5) {
                a.add(new Node(index, damage, 5));
                index +=1;
                ct = 0;
                damage = 0;
            }
        }

        if (ct != 0) {
            a.add(new Node(index, damage, ct));
        }

        System.out.println(a);

//        System.out.println(a);

        int currentPick = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (a.isEmpty()) {
                break;
            }
            Node yahoo = a.poll();
            System.out.println(Arrays.toString(picks));
            for (int loop = currentPick; loop < 3; loop++) {
                if (picks[loop] == 0) {
                    continue;
                }
                currentPick = loop;
                break;
            }
            if (currentPick == 2 && picks[currentPick] == 0) {
                break;
            }

            System.out.println(yahoo);
            for (int mineralIndex = yahoo.loc * 5; mineralIndex < (yahoo.loc * 5) + yahoo.step; mineralIndex++) {

                System.out.println(currentPick + " : " + mineralIndex);

                String mineral = minerals[mineralIndex];
                if (mineral.equals("diamond")) {
                    if (currentPick == 0) {
                        answer += 1;
                    } else if (currentPick == 1) {
                        answer += 5;
                    } else {
                        answer += 25;
                    }
                } else if (mineral.equals("iron")) {
                    if (currentPick == 0) {
                        answer += 1;
                    } else if (currentPick == 1) {
                        answer += 1;
                    } else {
                        answer += 5;
                    }
                } else {
                    if (currentPick == 0) {
                        answer += 1;
                    } else if (currentPick == 1) {
                        answer += 1;
                    } else {
                        answer += 1;
                    }
                }
            }
            picks[currentPick] -= 1;
        }


        return answer;
    }

    @Override
    public void init() {
//        int[] picks = {1, 3, 2};
//        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};

        int[] picks = {0, 1, 1};
        String[] minerals = {"iron", "iron", "iron", "iron", "iron", "stone", "stone", "stone", "stone", "stone", "diamond", "diamond", "diamond", "diamond",};

//        int[] picks = {0, 1, 1};
//        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond","diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
//        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};

        int solution = solution(picks, minerals);
        System.out.println("정답은 : " + solution);
    }
}
