import java.util.Arrays;
import java.util.Comparator;

public class Circle {

    void tptp() {

//        int[][] targets = { {4, 5}, {4, 8}, { 10, 14 }, {11, 13}, {5, 12}, {3, 7}, { 1, 4}};
        int[][] targets = {{1, 2}, {3, 100}, {4, 5}, {6, 7}};

        Arrays.sort(targets, Comparator.comparingInt((int[] o) -> o[0]));

        long answer = 0;

        int r1 = 2;
        int r2 = 3;
        double big_y;
        double small_y;

        long floorBig;
        long ceilSmall;


        for (long i = (-r2) + 1; i < r2; i++) {

            if (i == 0) continue;
            big_y = Math.sqrt((Math.pow(r2, 2)) - (i * i));

            small_y = ((Math.pow(r1, 2)) - (i * i)) < 0 ? 0 : Math.sqrt((Math.pow(r1, 2)) - (i * i));

            floorBig = (long) Math.floor(big_y);

            ceilSmall = Math.ceil(small_y) == 0 ? 1 : (long) Math.ceil(small_y);


            if (floorBig == ceilSmall) {
//                System.out.println(2);
                answer += 2;
            } else {
//                System.out.println(2 * (floorBig - ceilSmall + 1));
                answer += 2 * (floorBig - ceilSmall + 1);
            }

//            System.out.println(big_y - small_y);

//            System.out.println(i);
//            System.out.println(i + " =>" +" " + floorBig + "  " + ceilSmall);
        }

        System.out.println("원 위 숫자 : " + (r2 - r1 + 1) * 4);

        System.out.println(answer + (r2 - r1 + 1) * 4);

        return;
    }

}
