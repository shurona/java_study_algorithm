package Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.AlgoStudy;

public class CheckCollision implements AlgoStudy {

    List<List<List<Integer>>> shortCut = new ArrayList<>();

    public int findShortcut(int[][] points, int[] routes) {
        List<List<Integer>> path = new ArrayList<>();

        for (int rIndex = 0; rIndex < routes.length - 1; rIndex++) {
            int[] point = points[routes[rIndex] - 1];
            int[] target = points[routes[rIndex + 1] - 1];

//            System.out.println(Arrays.toString(point) + " : " + Arrays.toString(target));

            int yMove = point[0] - target[0];
            int xMove = point[1] - target[1];

            // System.out.println(yMove + " : " + xMove);

            int currentY = point[0];
            int currentX = point[1];

            if (rIndex == 0) {
                path.add(Arrays.asList(currentY, currentX));
            }

            // 먼저 yMove부터
            for (int y = 0; y < Math.abs(yMove); y++) {
                currentY = yMove > 0 ? currentY - 1 : currentY + 1;
                path.add(Arrays.asList(currentY, currentX));
            }

            // xMove
            for (int x = 0; x < Math.abs(xMove); x++) {
                currentX = xMove > 0 ? currentX - 1 : currentX + 1;
                path.add(Arrays.asList(currentY, currentX));
            }

        }

        shortCut.add(path);
        return path.size();
    }

    public int solution(int[][] points, int[][] routes) {
        int maxSize = 0;
        for (int i = 0; i < routes.length; i++) {
            int currSize = findShortcut(points, routes[i]);
            maxSize = Math.max(maxSize, currSize);
        }

        System.out.println(shortCut);
//         System.out.println(maxSize);
        int answer = 0;
        int index = 0;
        while (index < maxSize) {
            int[][] map = new int[101][101];
            for (List<List<Integer>> tp : shortCut) {
                if (tp.size() <= index) {
                    continue;
                }
                List<Integer> integers = tp.get(index);
                map[integers.get(0)][integers.get(1)] += 1;
            }
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (map[i][j] > 1) {
                        answer++; // 충돌!
                    }
                }
            }
            index += 1;
        }

        return answer;
    }

    @Override
    public void init() {
//        int[][] array1 = {
//            {3, 2},
//            {6, 4},
//            {4, 7},
//            {1, 4}
//        };
//
//        int[][] array2 = {
//            {4, 2},
//            {1, 3},
//            {2, 4}
//        };

//        int[][] array1 = {
//            {2, 2},
//            {2, 3},
//            {2, 7},
//            {6, 6},
//            {5, 2}
//        };
//
//        int[][] array2 = {
//            {2, 3, 4, 5},
//            {1, 3, 4, 5}
//        };

        int[][] array1 = {
            {1, 1},
            {2, 2},
            {3, 3}
        };

        int[][] array2 = {
            {1, 2, 1, 2},
            {3, 2, 1, 2}
        };

        int solution = solution(array1, array2);
        System.out.println("정답은 : " + solution);
    }
}
