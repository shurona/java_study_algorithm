package search;

import utils.AlgoStudy;

import java.util.*;

public class BasicDelivery implements AlgoStudy {
    HashMap<Integer,HashMap<Integer, Integer>> mapWithTime = new HashMap<>();

    static class VisitNode {
        int arrival;
        int cost;

        public VisitNode(int arrival, int cost) {
            this.arrival = arrival;
            this.cost = cost;
        }
    }

    public void calculateDistance(int[] dijkstraArr, int startNode) {
        Queue<VisitNode> visitQueue = new PriorityQueue<>(new Comparator<VisitNode>() {
            @Override
            public int compare(VisitNode o1, VisitNode o2) {
                return o1.cost - o2.cost;
            }

        });
        visitQueue.add(new VisitNode(startNode, 0));
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        while (visited.size() < dijkstraArr.length) {
            visited.add(startNode);
            // 현재 위치 숫자
            int currentLoc = dijkstraArr[startNode];

            // 갈 수 있는 장소를 최 솟 값으로 업데이트 해준다.
            for (Integer targetPlace : this.mapWithTime.get(startNode).keySet()) {
                Integer cost = this.mapWithTime.get(startNode).get(targetPlace);

                dijkstraArr[targetPlace] = Math.min(dijkstraArr[targetPlace], currentLoc + cost);
            }

            int minValue = Integer.MAX_VALUE;
            for (int i = 1; i < dijkstraArr.length; i++) {
                if (visited.contains(i)) {
                    continue;
                }
                if (dijkstraArr[i] < minValue) {
                    startNode = i;
                    minValue = dijkstraArr[i];
                }
            }
        }

    }

    public int solution(int N, int[][] road, int K) {

        // 갈 수 있는 곳 말고는 최대값으로 표시해놓는다.
        for (int[] roadInfo : road) {
            if (this.mapWithTime.getOrDefault(roadInfo[0], null) == null) {
                this.mapWithTime.put(roadInfo[0], new HashMap<>());
            }

            if (this.mapWithTime.getOrDefault(roadInfo[1], null) == null) {
                this.mapWithTime.put(roadInfo[1], new HashMap<>());
            }

            int minZero = Math.min(this.mapWithTime.get(roadInfo[0]).getOrDefault(roadInfo[1], Integer.MAX_VALUE), roadInfo[2]);
            this.mapWithTime.get(roadInfo[0]).put(roadInfo[1], minZero);

            int minOne = Math.min(this.mapWithTime.get(roadInfo[1]).getOrDefault(roadInfo[0], Integer.MAX_VALUE), roadInfo[2]);
            this.mapWithTime.get(roadInfo[1]).put(roadInfo[0], minOne);


        }
//        System.out.println(this.mapWithTime);

        int[] distanceMapping = new int[N + 1];
        // 초기는 최대값으로 채워준다.
        Arrays.fill(distanceMapping, Integer.MAX_VALUE);
        distanceMapping[1] = 0;

        calculateDistance(distanceMapping, 1);
        System.out.println(Arrays.toString(distanceMapping));


        int answer = 0;
        for (int i : distanceMapping) {
            if (i <= K) {
                answer +=1;
            }
        }


        return answer;
    }

    @Override
    public void init() {
//        int[][] data = {
//                {1, 2, 1},
//                {2, 3, 3},
//                {5, 2, 2},
//                {1, 4, 2},
//                {5, 3, 1},
//                {5, 4, 2}
//        };

        int[][] data = {
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 2},
                {3, 4, 3},
                {3, 5, 2},
                {3, 5, 3},
                {5, 6, 1}
        };

        System.out.println(solution(6, data, 4));
    }
}
