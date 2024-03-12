package exhaustiveSearch;

public class Carpet {
    public int[] getTestInput(int select) {
        int[] output = new int[2];
        switch(select) {
            case 1:
                output[0] = 10;
                output[1] = 2;
                break;
            case 2:
                output[0] = 8;
                output[1] = 1;
                break;
            case 3:
                output[0] = 24;
                output[1] = 24;
            default:
                break;

        }

        return output;
    }

    public void solution() {
        int[] testInput = getTestInput(2);
        int brown = testInput[0];
        int yellow = testInput[1];

        int area = brown + yellow;

        int checkNum = (int) area / 2;

        int width = brown;
        int height =yellow;
        for (int i = 2; i < checkNum; i++) {
            if (area % i == 0) {
                width = i;
                height = (int) (area / i);
                // 전체 갯수 - 갈색 갯수
                int brownCount = width * 2 + height * 2 - 4;

                if (area - brownCount == yellow) {
                    System.out.println(width + " " + height);
                    break;
                }
            }
        }

        System.out.println(Math.max(height, width));
        System.out.println(Math.min(height, width));

//        System.out.println(area);
//        System.out.println(checkNum);
    }
}
