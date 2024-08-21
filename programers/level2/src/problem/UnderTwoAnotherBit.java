package problem;

import utils.AlgoStudy;

import java.util.Arrays;

public class UnderTwoAnotherBit implements AlgoStudy {
    private long convertToDec(int[] input) {
//        System.out.println(Arrays.toString(input));
        long output = 0;
        int step = 1;
        for (int i = input.length - 1; i >= 0; i--) {
            output += (long) input[i] * step;
            step *= 2;
        }
        return output;
    }

    private int[] convertToBinary(long input) {

        int len = 1;
        int check = 1;
        while (check < input) {
            check *= 2;
            len += 1;
        }

        int[] output = new int[len];

        int index = output.length - 1;
        while (input != 0) {
            output[index] = (int )input % 2;
            input /= 2;
            index -=1;
        }


        return output;
    }

    public long[] solution(long[] numbers) {

        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
                continue;
            }

            if (numbers[i] == 1) {
                answer[i] = 2;
                continue;
            }

            int[] afterConvert = convertToBinary(numbers[i]);
//            System.out.println(Arrays.toString(afterConvert));

            int convertIndex = 1;
            for (int j = afterConvert.length - 1; j > 0; j--) {
                if (afterConvert[j] == 0) {
                    convertIndex = j;
                    break;
                }
            }

            if (convertIndex == 1) {
                afterConvert[0] = 1;
                afterConvert[1] = 0;
                answer[i] = (convertToDec(afterConvert));
            } else {
                afterConvert[convertIndex] = 1;
                afterConvert[convertIndex + 1] = 0;
                System.out.println(convertIndex);
                System.out.println(Arrays.toString(afterConvert));
                answer[i] = (convertToDec(afterConvert));
            }
        }

        return answer;
    }

    @Override
    public void init() {
//        long[] numbers = {15,127,179,3783,819199,9126805503,38588121087,8796093022207,17592186044415};
        long[] numbers = {39};

        long[] solution = solution(numbers);
        System.out.println("정답은 : " + Arrays.toString(solution));

    }
}
