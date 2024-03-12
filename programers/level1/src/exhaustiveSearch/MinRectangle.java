package exhaustiveSearch;

import java.util.Arrays;

public class MinRectangle {
    public void solution() {

        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

        int garo = 0;
        int sero = 0;
        for (int[] oneSize : sizes) {
            garo = Math.max(Math.max(oneSize[0], oneSize[1]), garo);
            sero = Math.max(Math.min(oneSize[0], oneSize[1]), sero);
        }

        System.out.println("가로 : " + garo + "  세로 : " + sero);

        System.out.println(Arrays.toString(sizes[0]));
    }
}
