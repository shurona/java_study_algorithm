package Graph;

import utils.AlgoLevel3Study;

import javax.management.QueryEval;
import java.util.*;

public class WolfAndSheep implements AlgoLevel3Study {
    HashMap<Integer, Node> makeNode = new HashMap<>();
    int answer = 0;
    int infoLength;
    public static class Node {
        int node;
        int isSheep;
        boolean visit;

        Node leftNode;
        Node rightNode;

        public Node(int node, int isSheep) {
            this.node = node;
            this.isSheep = isSheep;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "node=" + node +
                    ", isSheep=" + isSheep +
//                    ", visit=" + visit +
                    ", leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    '}';
        }
    }

    public void dfs(ArrayList<Node> remains, Node current, int sheepCount, int wolfCount, int count) {
        int currentSheepCount = sheepCount;
        int currentWolfCount = wolfCount;
        if (current.isSheep == 0) {
            currentSheepCount += 1;
            this.answer = Math.max(this.answer, currentSheepCount);
        } else {
            currentWolfCount += 1;
        }

        // 양보다 늑대가 같거나 더 많으면
        if (currentSheepCount <= currentWolfCount) {
            return;
        }
        // 모든 노드 탐색시
        if (count == this.infoLength) {
            return;
        }

        // 두 개 넣어준다.
        if (current.leftNode != null) {
            remains.add(current.leftNode);
        }
        if (current.rightNode != null) {
            remains.add(current.rightNode);
        }

        System.out.println(remains);

        for(int i = 0 ; i < remains.size() ; i++){
            ArrayList<Node> cloneArrayList = new ArrayList<>();
            for (int choose = 0; choose < remains.size(); choose++) {
                if (choose != i) {
                    // 하나 씩 제외하고 복사한다.
                    cloneArrayList.add(remains.get(choose));
                }
            }
            dfs(cloneArrayList, remains.get(i), currentSheepCount, currentWolfCount, count + 1);
        }
    }

    public int solution(int[] info, int[][] edges) {
        this.infoLength = info.length;

//        // edge sort
        Arrays.sort(edges, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        for (int[] edge : edges) {
//            if(this.makeNode.getOrDefault(edge[0], null) != null) continue;
            this.makeNode.put(edge[0], new Node(edge[0], info[edge[0]]));
            this.makeNode.put(edge[1], new Node(edge[1], info[edge[1]]));


        }

        for (int[] edge : edges) {
            Node node = this.makeNode.get(edge[0]);

            if (node.leftNode == null) {
                node.leftNode = this.makeNode.get(edge[1]);
            } else if (node.rightNode == null) {
                node.rightNode = this.makeNode.get(edge[1]);
            }
        }


//        System.out.println(this.makeNode.get(0));
        this.makeNode.get(0).visit = true;
        dfs(new ArrayList<>(), this.makeNode.get(0), 0, 0, 1);


        return this.answer;
    }

    @Override
    public void init() {
        int[] info = {0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1};
//        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int[] info = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//        int[] info = {0, 0};

        int[][] edges = {
                {0, 2},
                {2, 1},
                {2, 4},
                {0, 8},
                {8, 7},
                {9, 10},
                {9, 11},
                {4, 3},
                {6, 5},
                {4, 6},
                {8, 9}
        };

//    int[] info = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
//        int[][] edges = {{0, 1},
//                {0, 2},
//                {1, 3},
//                {1, 4},
//                {2, 5},
//                {2, 6},
//                {3, 7},
//                {4, 8},
//                {6, 9},
//                {9, 10}};
//
//        int[][] edges = {{0, 1}, {0, 4}, {1, 2}, {1, 3}, {4, 5}, {4, 6}};
//
//        int[] info = {0, 1, 1, 1, 1, 0, 0};


//        int[][] edges = {
//                {0, 1},
//                {0, 2},
//                {1, 3},
//                {2, 4},
//                {3, 5},
//                {3, 6},
//                {4, 7},
//                {6, 8}
//        };
//        int[] info = {0, 1, 0, 1, 1, 0, 0, 0, 0};

//        int[] info = {0, 0, 1, 1, 0, 0, 0};
//        int[][] edges = {
//                {0, 1},
//                {1, 2},
//                {1, 3},
//                {2, 4},
//                {3, 5},
//                {3, 6}
//        };

        int output = solution(info, edges);
        System.out.println(output);

    }
}
