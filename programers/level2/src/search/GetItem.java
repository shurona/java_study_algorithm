package search;

import utils.AlgoStudy;

import java.util.*;

public class GetItem implements AlgoStudy {

    int[][] polygonMap = new int[100][100];

    HashMap<String, Boolean> visited = new HashMap<>();

    public void makePolygon(int[][] rectangle) {
        for (int[] rec : rectangle) {
            int recStartX = rec[0] * 2;
            int recEndX = rec[2] * 2;

            int recStartY = rec[1] * 2;
            int recEndY = rec[3] * 2;
            for (int row = recStartY; row <= recEndY; row++) {
                for (int col = recStartX; col <= recEndX; col++) {
                    if (this.polygonMap[row][col] != 2
                            && (row == recStartY || row == recEndY || col == recStartX || col == recEndX)) {
                        this.polygonMap[row][col] = 1;
                    } else {
                        this.polygonMap[row][col] = 2;
                    }
                }
            }

        }

    }

    public boolean checkOnRectangle(int row, int col) {
//        System.out.println(row + " : " + col);
        boolean isOn = false;

        if (visited.getOrDefault((row + "" + col), false)) {
            return isOn;
        }

        if (row < 0 || col < 0 || row == this.polygonMap.length || col == this.polygonMap[0].length) {
            return isOn;
        }

        if(this.polygonMap[row][col] == 1) isOn = true;

//        System.out.println(row + " : " + col + " => " + isOn);

        return isOn;
    }


    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = -1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        makePolygon(rectangle);
        for (int i = this.polygonMap.length - 1; i >= 0; i--) {
            System.out.println(Arrays.toString(polygonMap[i]));
        }

        Queue<ArrayList<Integer>> bfs = new LinkedList<>();
        bfs.add(new ArrayList<>(Arrays.asList(characterY * 2, characterX * 2, 0)));

        int checkText = 0;
        while (!bfs.isEmpty()) {
            ArrayList<Integer> currentLoc = bfs.poll();
            System.out.println(currentLoc.get(0) + " : " + currentLoc.get(1));

            if (currentLoc.get(0) == (itemY * 2) && currentLoc.get(1) == (itemX * 2)) {
                answer = currentLoc.get(2);
                break;
            }
            int count = currentLoc.get(2);
            for (int i = 0; i < 4; i++) {
                int nextRow = currentLoc.get(0) + dx[i];
                int nextCol = currentLoc.get(1) + dy[i];
                if (!checkOnRectangle(nextRow, nextCol)) continue;
                this.visited.put(nextRow + "" + nextCol, true);
                bfs.add(new ArrayList<>(Arrays.asList(nextRow, nextCol, count + 1)));
            }
        }

//        System.out.println(visited);

        return answer / 2;
    }

    @Override
    public void init() {
//        [[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]
//        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
//        int characterX = 1;
//        int characterY = 3;
//        int itemX = 7;
//        int itemY = 8;
//
        int[][] rectangle = {{1, 1, 5, 7}};
        int characterX = 1;
        int characterY = 1;
        int itemX = 4;
        int itemY = 7;

        System.out.println(solution(rectangle, characterX, characterY, itemX, itemY));

    }
}
