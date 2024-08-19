package problem;

import utils.AlgoStudy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ConvertNumber implements AlgoStudy {

    private Map<Integer, Boolean> found = new HashMap<>();

    private class QueueNode {
        int value;
        int count;

        public QueueNode(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public String toString() {
            return "QueueNode{" +
                    "value=" + value +
                    ", count=" + count +
                    '}';
        }
    }

    public int bfs(int now, int target, int n) {
        Queue<QueueNode> queue = new LinkedList<>();
        int answer = -1;
        found.put(now, true);
        queue.add(new QueueNode(now, 0));
        while (!queue.isEmpty()) {
//            System.out.println(queue);
            QueueNode currentNode = queue.poll();
            Integer currentValue = currentNode.value;
            if (currentValue == target) {
                answer = currentNode.count;
                break;
            }
            if (currentValue > target) {
                continue;
            }
            for (int i = 0; i < 3; i++) {
                Integer newValue = currentValue;
                switch (i) {
                    case 0:
                        newValue = currentValue + n;
                        break;

                    case 1:
                        newValue = currentValue * 2;
                        break;

                    case 2:
                        newValue = currentValue * 3;
                        break;
                }

                if (!found.getOrDefault(newValue, false)) {
                    found.put(newValue, true);
                    queue.add(new QueueNode(newValue, currentNode.count + 1));
                }
            }
        }

        return answer;
    }

    public int solution(int x, int y, int n) {

        int bfs = bfs(x, y, n);
        return bfs;
    }

    @Override
    public void init() {

        int x = 10;
        int y = 40;
        int n = 5;
        int solution = solution(x, y, n);
        System.out.println("정답은 : " + solution);

    }
}
