package problem;

import java.util.*;
import java.util.stream.Collectors;

public class TaxiSharedRide {
    final int REACH_PLACE = 0;
    final int TAXI_COST = 1;
    HashMap<Integer, PriorityQueue<ArrayList<Integer>>> nodeConnection;

    PriorityQueue<ArrayList<Integer>> generateQueue() {
        return new PriorityQueue<>(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer>o2) {
                return o1.get(TAXI_COST) - o2.get(TAXI_COST);
            }
        });
    }

    public int[] dijkstraAlgorithm(int start, int n) {
        // nodeConnection 갖고 오자
        int[] dpTable = new int[n];
        Arrays.fill(dpTable, Integer.MAX_VALUE);
        dpTable[start - 1] = 0;
        int st = start;
        HashSet<Integer> visit = new HashSet<>();
        while (visit.size() < n - 1) {
            int nowDistance = dpTable[st - 1];
            // 시작 노드에서 연결된 부분을 찾는다.
            PriorityQueue<ArrayList<Integer>> arrivedList = nodeConnection.getOrDefault(st, new PriorityQueue<>());
            for (ArrayList<Integer> info : arrivedList) {
                Integer cost = info.get(TAXI_COST);
                Integer reach = info.get(REACH_PLACE);

                if (nowDistance == Integer.MAX_VALUE) {
                    break;
                }

                int currentCost = dpTable[reach - 1];
                dpTable[reach - 1] = Math.min(cost + nowDistance, currentCost);
            }
            visit.add(st);
            int minData = Integer.MAX_VALUE;
            for (int i = 0; i < dpTable.length; i++) {
                if (visit.contains(i + 1)) {
                    continue;
                }
                if (dpTable[i] <= minData) {
                    st = i + 1;
                    minData = dpTable[i];
                }

            }
            // 이걸 선택 안 한 지점으로 바꿔줘야함
//            System.out.println("dpTable = " + Arrays.toString(dpTable));
//            System.out.println("st = " + st);
        }
        return dpTable;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        nodeConnection = new HashMap<>();
        // 가는 종류로 계산
        for (int[] fare : fares) {
            if (nodeConnection.getOrDefault(fare[0], null) == null) {
                nodeConnection.put(fare[0], generateQueue());
            }

            if (nodeConnection.getOrDefault(fare[1], null) == null) {
                nodeConnection.put(fare[1], generateQueue());
            }

            // 첫 번재 도착지점 , 두 번째 Cost
            ArrayList<Integer> first = new ArrayList<>();
            ArrayList<Integer> second = new ArrayList<>();

            first.add(fare[1]);
            first.add(fare[2]);
            second.add(fare[0]);
            second.add(fare[2]);
            nodeConnection.get(fare[0]).add(first);
            nodeConnection.get(fare[1]).add(second);
        }


        // 다익스탈 알고리즘
        // s -> x , x -> a , x -> b
        // s -> x로 가는 경우의 수
        int[] one = dijkstraAlgorithm(s, n);
        // x -> a로 가는 경우의 수
        int[] two = dijkstraAlgorithm(a, n);
        // x -> b로 가는 경우의 수
        int[] three = dijkstraAlgorithm(b, n);

        // 방문 횟수
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, one[i] + two[i] + three[i]);
//            System.out.println("dd : " + a);
        }

        return answer;
    }

    public void init() {
        System.out.println("TaxiSharedRide.init");

        // 갯수, 시작, A, B
        int[] input = {6, 4, 6, 2};
//        int[] input = {7, 3, 4, 1};

        int[][] fares = {
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}
        };

//        int[][] fares = {
//                {5, 7, 9},
//                {4, 6, 4},
//                {3, 6, 1},
//                {3, 2, 3},
//                {2, 1, 6}
//        };

        int answer = anotherSolution(input[0], input[1], input[2], input[3], fares);
        System.out.println("answer = " + answer);

    }

    /**
     * 다른 사람 풀이
     */

    ArrayList<ArrayList<Edge>> graph;
    class Edge implements Comparable<Edge>{
        int next;
        int cost;
        Edge(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "next=" + next +
                    ", cost=" + cost +
                    '}';
        }
    }
    int[] dijkstra(int start, int[] costs){
        PriorityQueue<Edge> pq = new PriorityQueue<>(graph.get(start));
        for(Edge e : graph.get(start)) costs[e.next] = e.cost;
        costs[start] = 0;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            System.out.println(e);
            if(costs[e.next] < e.cost) continue;
            for(Edge ne : graph.get(e.next)){
                if(costs[ne.next] > e.cost + ne.cost){
                    costs[ne.next] = e.cost + ne.cost;
                    pq.add(new Edge(ne.next, e.cost + ne.cost));
                }
            }
            System.out.println(pq);
            System.out.println();
//            break;
        }
        return costs;
    }
    public int anotherSolution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        graph = new ArrayList<>();
        for(int i = 0 ; i < n ; i ++) graph.add(new ArrayList<>());
        for(int[] i : fares){
            graph.get(i[0] - 1).add(new Edge(i[1] - 1, i[2]));
            graph.get(i[1] - 1).add(new Edge(i[0] - 1, i[2]));
        }
        int[] startA = new int[n];
        int[] startB = new int[n];
        int[] start = new int[n];

        Arrays.fill(startA, 100000 * n);
        Arrays.fill(startB, 100000 * n);
        Arrays.fill(start, 100000 * n);

//        startA = dijkstra(a - 1, startA);
//        startB = dijkstra(b - 1, startB);
        start = dijkstra(s - 1, start);
//
//        for(int i = 0 ; i < n ; i ++) answer = Math.min(answer, startA[i] + startB[i] + start[i]);
        return answer;
    }
}
