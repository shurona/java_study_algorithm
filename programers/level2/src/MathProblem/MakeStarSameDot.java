package MathProblem;

import java.util.Arrays;
import java.util.HashSet;
import utils.AlgoStudy;

public class MakeStarSameDot implements AlgoStudy {

    public String[] solution(int[][] line) {

        HashSet<Node> dotSet = new HashSet<>();

        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;

        // 교점을 구한다.
        for (int i = 0; i < line.length; i++) {
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];
            //
            for (int j = i + 1; j < line.length; j++) {
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];

                // 교점에 없다.
                long denominator = a * d - b * c;
                if (a * d - b * c == 0) {
                    continue;
                }

                long numeratorX = b * f - e * d;
                long numeratorY = e * c - a * f;

                // 점은 정수만이 가능하다.
                if (numeratorX % denominator != 0 || numeratorY % denominator != 0) {
                    continue;
                }

                dotSet.add(new Node(numeratorX / denominator, numeratorY / denominator));

//                System.out.println(numeratorX / denominator + " : " + numeratorY / denominator);

                minX = Math.min(minX, numeratorX / denominator);
                minY = Math.min(minY, numeratorY / denominator);
                maxX = Math.max(maxX, numeratorX / denominator);
                maxY = Math.max(maxY, numeratorY / denominator);

            }

        }

//        System.out.println(minX + " : " + minY);
//        System.out.println(maxX + " : " + maxY);
        System.out.println(dotSet);

        String[][] board = new String[(int) (maxY - minY + 1)][(int) (maxX - minX + 1)];

        for (String[] strings : board) {
            Arrays.fill(strings, ".");
        }

        for (Node node : dotSet) {

            long xx;
            long yy;

            if (minX * maxX > 0) {
                xx = Math.abs(maxX - minX);
            } else {
                xx = Math.abs(minX);
            }

            if (maxY > 0) {
                yy = -maxY;
            } else {
                yy = -maxY;
            }

            int yDot = (int) (maxY - node.y);
            int xDot = (int) (node.x - minX);

            board[yDot][xDot] = "*";
//            System.out.println(node);
        }
//        for (String[] strings : board) {
//            System.out.println(Arrays.toString(strings));
//        }

        return Arrays.stream(board).map(row -> String.join("", row))
            .toArray(String[]::new);
    }

    @Override
    public void init() {
        int[][] line = {
            {2, -1, 4},
            {-2, -1, 4},
            {0, -1, 1},
            {5, -8, -12},
            {5, 8, 12}
        };

        int[][] line2 = {
            {0, 1, -1},
            {1, 0, -1},
            {1, 0, 1}
        };

        String[] solution = solution(line);
        for (String s : solution) {
            System.out.println(s);
        }

    }

    static class Node {

        long x;
        long y;

        public Node(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}
