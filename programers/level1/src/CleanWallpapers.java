public class CleanWallpapers {
    public int[] solution(String[] wallpaper) {

        int[] minIndex = { wallpaper.length , wallpaper[0].length()};
        int[] maxIndex = { 0 , 0 };

        for(int row = 0 ; row < wallpaper.length; row++) {
            for(int col = 0; col < wallpaper[row].length(); col++) {
                if(wallpaper[row].charAt(col) == '#') {
                    minIndex[0] = Math.min(minIndex[0], row);
                    minIndex[1] = Math.min(minIndex[1], col);

                    maxIndex[0] = Math.max(maxIndex[0], row);
                    maxIndex[1] = Math.max(maxIndex[1], col);
//                    System.out.printf("로케이션 %d, %d\n", row, col);
                }
            }
        }

//        System.out.printf("로케이션 %d, %d, %d, %d\n", minIndex[0], minIndex[1], maxIndex[0] + 1, maxIndex[1] + 1);

        int[] answer = { minIndex[0], minIndex[1], maxIndex[0] + 1, maxIndex[1] + 1 };
        return answer;
    }
}
