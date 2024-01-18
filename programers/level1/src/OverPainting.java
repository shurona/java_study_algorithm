public class OverPainting {

    void DoPaint() {
        int n = 8;
        int m = 4;
        int[] section = {2, 3, 6};
//        int[] section = {1, 2, 3, 4};
        int answer = 1;

        int endPaint = section[0] + m;
        for(int sec: section) {
            System.out.printf("%d, %d\n",sec, endPaint);
            // section이 초과했으면 다시 칠한다.
            if(sec >= endPaint ) {
                endPaint= sec + m;
                answer+=1;
            }
        }

        System.out.println("몇 번 칠했냐 : " + answer);
    }
}
