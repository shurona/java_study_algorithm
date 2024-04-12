package problem;

import java.util.Arrays;

/**
 * 부분 수열의 합이 k인 데이터를 구해라
 * 가장 짧고 앞에 나온 수열을 구한다.
 */
public class SumOfSequence {
    static class InputInfo {
        int[] sequence;
        int output;

        public InputInfo(int[] sequence, int output) {
            this.sequence = sequence;
            this.output = output;
        }

        public int getOutput() {
            return output;
        }

        public int[] getSequence() {
            return sequence;
        }
    }
    InputInfo input(int select) {
        InputInfo tp = null;
        if (select == 2) {
            tp = new InputInfo(new int[]{1, 2, 3, 4, 5}, 7);
        } else if (select == 3) {
            tp = new InputInfo(new int[]{1, 1, 1, 2, 3, 4, 5}, 5);
        } else {
            tp = new InputInfo(new int[]{2, 2, 2, 2, 2}, 6);
        }

        if (tp == null) {
            tp = new InputInfo(new int[]{0}, -1);
        }

        return tp;
    }

    public int[] findAnswer(int[] answer, int leftIndex, int rightIndex) {

        if (answer[0] == -1) {
            return new int[]{leftIndex, rightIndex};
        }

        int distance = answer[1] - answer[0];
        // 두 개의 길이가 짧은 걸로 한다.
        if (rightIndex - leftIndex < distance) {
            return new int[]{leftIndex, rightIndex};
        } else if (rightIndex - leftIndex == distance && leftIndex < answer[0]) {
            return new int[]{leftIndex, rightIndex};
        } else {
            return new int[]{answer[0], answer[1]};
        }
    }

    public void solution() {
        InputInfo yahoo = input(3);

        int[] sequence = yahoo.getSequence();
        int k = yahoo.getOutput();

        int[] answer = {-1, -1};

        int leftIndex = 0;
        int rightIndex = 0;
        int sum = 0;
        // sequence의 leftIndex 값이 k보다 커지면 멈춘다.
        sum = sequence[leftIndex];
        while (sequence[leftIndex] <= k) {
            // 만약 합이 k보다 작으면 right index를 증가시킨다.
            if (sum < k) {
                rightIndex += 1;
                // 더 증가를 못시키는데 작으면 break
                if (rightIndex == sequence.length) {
                    break;
                }
                sum += sequence[rightIndex];
            }
            // 만약 합이 같다면 left index를 증가시킨다.
            else {
                if (sum == k) {
                    answer = findAnswer(answer, leftIndex, rightIndex);
                    System.out.println("같아요 왼쪽 : " + leftIndex + " 오른쪽 : " + rightIndex);
                }
                sum -= sequence[leftIndex];
                leftIndex += 1;
            }
            //
            // 만약 끝에 왔으면 break
            if (leftIndex == sequence.length) {
                break;
            }
        }

        System.out.println("answer = " + Arrays.toString(answer));
    }
}
