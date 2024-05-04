package dp;

import java.util.*;

public class FourBasicOperation {
    int answer = -100000000;
    Map<String, Boolean> saved = new HashMap<>();

    private int bfs(String[] arr) {
        if (arr.length == 1) {
            System.out.println("arr = " + arr[0]);
            answer = Math.max(answer, Integer.parseInt(arr[0]));
            return 0;
        }

        // bfs 처음 두 개 씩 묶어서 넣어주면 가능할 것 같음.
        for (int index = 0; index < arr.length/ 2 ; index+=1) {
            int startIndex = index * 2;
            int output = 0;
            if (arr[startIndex + 1].equals("-")) {
                output = Integer.parseInt(arr[startIndex]) - Integer.parseInt(arr[startIndex + 2]);
            } else {
                output = Integer.parseInt(arr[startIndex]) + Integer.parseInt(arr[startIndex + 2]);
            }

            String[] dp = new String[arr.length - 2];
            StringBuilder a = new StringBuilder();
            int dpIndex = 0;
            for (int arrIndex = 0; arrIndex < arr.length; arrIndex++) {
                if (arrIndex == startIndex) {
                    dp[dpIndex] = String.valueOf(output);
                    dpIndex+=1;
                    a.append(String.valueOf(output));
                } else if (arrIndex == startIndex + 1 || arrIndex == startIndex + 2) {
                    continue;
                } else {
                    dp[dpIndex] = arr[arrIndex];
                    a.append(arr[arrIndex]);
                    dpIndex += 1;

                }
            }
            if (!saved.getOrDefault(a.toString(), false)) {
                bfs(dp);
                saved.put(a.toString(), true);
            }
        }
        return 0;
    }

    public void solution() {

        String[] arr = {"1", "-", "3", "+", "5", "-", "8"};
//                String[] arr = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};


        int[] numbers = new int[arr.length / 2 + 1];
        for (int i = 0; i < arr.length / 2 + 1; i++) {
            numbers[i] = Integer.parseInt(arr[i* 2]);
        }

        int[][] maxDP = new int[numbers.length][numbers.length];
        int[][] minDP = new int[numbers.length][numbers.length];

        // 초기 배열 초기화
        for (int[] ints : maxDP) {
            Arrays.fill(ints, Integer.MIN_VALUE);
        }
        for (int[] ints : minDP) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        for (int step = 0; step < numbers.length; step++) {
            for (int leftIndex = 0; leftIndex < numbers.length - step; leftIndex++) {
                // 변수 초기화
                int rightIndex = leftIndex + step;

                System.out.println("step : " + step + "  leftIndex : " + leftIndex + "  rightIndex : " + rightIndex);

                if (step == 0) {
                    minDP[rightIndex][leftIndex] = numbers[rightIndex];
                    maxDP[rightIndex][leftIndex] = numbers[rightIndex];
                } else {
                    for (int k = leftIndex; k < rightIndex; k++) {
                        // k 번 연산자가 +인 경우
                        if (arr[2 * k + 1].equals("+")) {
                            // i에서 k 까지 와 k + 1 부터 끝까지
                            maxDP[leftIndex][rightIndex] =
                                    Math.max(maxDP[leftIndex][rightIndex], maxDP[leftIndex][k] + maxDP[k + 1][rightIndex]);
                            minDP[leftIndex][rightIndex] =
                                    Math.min(minDP[leftIndex][rightIndex], minDP[leftIndex][k] + minDP[k + 1][rightIndex]);
                        } else {
                            maxDP[leftIndex][rightIndex] =
                                    Math.max(maxDP[leftIndex][rightIndex], maxDP[leftIndex][k] - minDP[k + 1][rightIndex]);
                            minDP[leftIndex][rightIndex] =
                                    Math.min(minDP[leftIndex][rightIndex], minDP[leftIndex][k] - maxDP[k + 1][rightIndex]);
                        }
                    }

                }
            }
        }


        System.out.println("minDP = " + Arrays.deepToString(minDP));
        System.out.println("maxDP = " + Arrays.deepToString(maxDP));
        System.out.println("FourBasicOperation.solution");
    }
}
