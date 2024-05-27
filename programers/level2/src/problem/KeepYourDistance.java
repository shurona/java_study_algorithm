package problem;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KeepYourDistance implements AlgoStudy {
    public int dfs(String[] place) {

        String[] newPlace = new String[place.length + 4];
        int width = place[0].length() + 4;
        // Padding 넣어 준다.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append('X');
        }

        newPlace[0] = sb.toString();
        newPlace[1] = sb.toString();
        newPlace[newPlace.length - 2] = sb.toString();
        newPlace[newPlace.length - 1] = sb.toString();

        for (int i = 0; i < place.length; i++) {
            newPlace[2 + i] = "XX" + place[i] + "XX";
        }

        for (int row = 0; row < newPlace.length; row++) {
//            System.out.println(newPlace[row]);
            for (int col = 0; col < newPlace[0].length(); col++) {
                if (newPlace[row].charAt(col) == 'P') {
                    // 좌
                    char leftOne = newPlace[row].charAt(col - 1);
                    char leftTwo = newPlace[row].charAt(col - 2);

                    if (leftOne == 'P') {
                        return 0;
                    }

                    if (leftOne != 'X' && leftTwo == 'P') {
                        return 0;
                    }

                    if (leftOne == 'O' && newPlace[row + 1].charAt(col - 1) == 'P') {
                        return 0;
                    }

                    if (leftOne == 'O' && newPlace[row - 1].charAt(col - 1) == 'P') {
                        return 0;
                    }

                    // 우
                    char rightOne = newPlace[row].charAt(col + 1);
                    char rightTwo = newPlace[row].charAt(col + 2);

                    if (rightOne == 'P') {
                        return 0;
                    }

                    if (rightOne != 'X' && rightTwo == 'P') {
                        return 0;
                    }

                    if (rightOne == 'O' && newPlace[row - 1].charAt(col + 1) == 'P') {
                        return 0;
                    }

                    if (rightOne == 'O' && newPlace[row + 1].charAt(col + 1) == 'P') {
                        return 0;
                    }

                    // 상
                    char upOne = newPlace[row - 1].charAt(col);
                    char upTwo = newPlace[row - 2].charAt(col);

                    if (upOne == 'P') {
                        return 0;
                    }

                    if (upOne != 'X' && upTwo== 'P') {
                        return 0;
                    }

                    if (upOne == 'O' && newPlace[row - 1].charAt(col - 1) == 'P') {
                        return 0;
                    }

                    if (upOne == 'O' && newPlace[row - 1].charAt(col + 1) == 'P') {
                        return 0;
                    }

                    // 하
                    char underOne = newPlace[row + 1].charAt(col);
                    char underTwo = newPlace[row + 2].charAt(col);

                    if (underOne == 'P') {
                        return 0;
                    }

                    if (underOne != 'X' && underTwo == 'P') {
                        return 0;
                    }

                    if (underOne == 'O' && newPlace[row + 1].charAt(col - 1) == 'P') {
                        return 0;
                    }

                    if (underOne == 'O' && newPlace[row + 1].charAt(col + 1) == 'P') {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int index = 0;
        for (String[] place : places) {
            answer[index] = dfs(place);
            index += 1;
        }
        return answer;
    }

    /**
     *  다른 사람 풀이
     */

    int[] dx = new int[]{-1,0,1,0};
    int[] dy = new int[]{0,1,0,-1};

    class node{
        int x;
        int y;
        int dist;
        node(int a,int b, int c){
            this.x = a;
            this.y = b;
            this.dist = c;
        }

        @Override
        public String toString() {
            return "node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dist=" + dist +
                    '}';
        }
    }

    int bfs(char[][] map){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(map[i][j] != 'P') continue;

                // P인 경우에만 진
                boolean[][] check = new boolean[5][5];
                Queue<node> q = new LinkedList<>();
                q.add(new node(i,j,0));
                check[i][j] = true;
                System.out.println(q);

                while(!q.isEmpty()){
                    node cur = q.poll();

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];
                        int ndist = cur.dist + 1;

                        // 두 칸 이상 안가고
                        if(ndist > 2) continue;
                        // 범위 확인하고
                        if(nx < 0 || nx > 4 || ny < 0 || ny > 4) continue;
                        // 이미 확인했는지 확인
                        if(check[nx][ny]) continue;

                        if(map[nx][ny] == 'P') return 0;
                        if(map[nx][ny] == 'O'){
                            check[nx][ny] = true;
                            q.add(new node(nx,ny,ndist));
                        }
                        System.out.println(q);
                    }
                }
                System.out.println();
            }
        }
        return 1;
    }

    public int[] anotherSolution(String[][] places) {
        int[] answer = new int[places.length];

        for (int x = 0; x < places.length; x++) {
            // place 하나 추출
            String[] place = places[x];
            char[][] map = new char[5][5];

            // String => 배열
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[i][j] = place[i].charAt(j);
                }
            }

            answer[x] = bfs(map);
            break;
        }

        return answer;
    }

    /**
     * 시작 값
     */
    @Override
    public void init() {
        String[][] data = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        System.out.println(Arrays.toString(anotherSolution(data)));

    }
}
