package problem;
import java.util.*;
import java.util.stream.Collectors;


public class DonutBlockGraph {
    public void solution() {
        System.out.println("DonutBlockGraph.solution");

//        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};

//        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 9}, {5, 3}, {11, 1}, {3, 8}, {2, 15}};

        int[][] edges = {
                {1, 12}, {8, 3}, {12, 7}, {7, 11}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}, {4, 11}, {4, 8}, {4, 13}
        };

        // 정점, 도넛, 막대, 8자
        int[] answer = {0, 0, 0, 0};
        Map<Integer, List<List<Integer>>> edgeMap = new HashMap<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> outPoint = new HashSet<>();
        for (int[] e : edges) {
            // 정점 찾기위한 선작업
            map.putIfAbsent(e[0], new HashSet<>());
            map.get(e[0]).add(e[1]);
            outPoint.add(e[1]);

            List<List<Integer>> output = edgeMap.getOrDefault(e[0], null);
            List<Integer> list = Arrays.stream(e).boxed().collect(Collectors.toList());

            if (output == null) {
                List<List<Integer>> tp = new ArrayList<>();
                tp.add(list);
                edgeMap.put(e[0], tp);
            } else{
                output.add(list);
            }

        }

        // 정점 설정
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if(outPoint.contains(entry.getKey())) continue;
            if (entry.getValue().size() > 1) {
                answer[0] = entry.getKey();
            }
        }

        // 순회를 해본다.
        for (List<Integer> startEdge : edgeMap.get(answer[0])) {
            // 정점에서의 다음 node
            Integer init = startEdge.get(1);
            Set<Integer> visit = new HashSet<>();
            int count = 0;
            int starting = startEdge.get(1);
            while (true) {
                int index = 0;
                List<Integer> tp = null;
                List<List<Integer>> nodes = edgeMap.get(init);
                visit.add(init);
                // 해당 번호에 연결된 노드가 없는 경우 break
                if (nodes == null || nodes.isEmpty()) {
                    answer[2] += 1;
                    break;
                }

                // 시작 점 에서 다음 지역 확인
                if (nodes.size() > 1) {
                    for (int j = 0; j < nodes.size(); j++) {
                        List<Integer> node = nodes.get(j);
                        // 도착지가 있으면 없는 곳으로
                        if (!visit.contains(node.get(1))) {
                            tp = node;
                            index = j;
                            break;
                        }
                    }
                    if (tp == null) {
                        tp = nodes.get(0);
                    }
                    answer[3] +=1;
                    break;
                } else {
                    tp = nodes.get(0);
                }
                // 사용한 간선 지우고
                // 다음 노드로 이동
                count +=1;
                init = tp.get(1);
                if (starting == init) {
                    answer[1] += 1;
                    break;
                }
            }
//            System.out.println("visit = " + visit);
//            System.out.println("count = " + count);
        }

        System.out.println("edgeMap = " + edgeMap);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}
