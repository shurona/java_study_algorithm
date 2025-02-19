package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/20055
 */
public class ContainerBelt implements BaekAlgoStudy {

    static int zeroCount = 0;

    private static void shiftContainer(int[][] containerHealth, int[] robotSpot, int N) {
        int step = 2;
        int beforeValue = containerHealth[0][1];
        for (int i = 0; i < N * 2; i++) {
            int upDown = 0;
            int nowStep = step;
            // 만약 2배가 넘어가면 초기화
            if (step > 2 * N) {
                step = 1;
                nowStep = step;
            } else if (step > N) {
                nowStep = step - (2 * (step - N) - 1);
                upDown = 1;
            }
            // 지금 꺼 임시 보관
            int temp = containerHealth[upDown][nowStep];
            containerHealth[upDown][nowStep] = beforeValue;
            beforeValue = temp;

            step += 1;
        }

        // 컨테이터 벨트가 움직이기는 거라서 그냥 한칸씩 오른쪽으로 옮긴다.
        for (int i = robotSpot.length - 1; i > 0; i--) {
            robotSpot[i] = robotSpot[i - 1];
        }

        robotSpot[robotSpot.length - 1] = 0;

    }

    public static void moveRobot(int[][] containerBelt, int[] robotSpot) {
        for (int i = robotSpot.length - 1; i > 0; i--) {
            if (robotSpot[i] == 0 && robotSpot[i - 1] == 1 && containerBelt[0][i] > 0) {
                robotSpot[i] = robotSpot[i - 1];
                robotSpot[i - 1] = 0;
                containerBelt[0][i] -= 1;
                if (containerBelt[0][i] == 0) {
                    zeroCount += 1;
                }
            }
        }

        robotSpot[robotSpot.length - 1] = 0;

    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(i -> Integer.parseInt(i)).toArray();

        int N = firstLine[0];
        int[][] containerHealth = new int[2][N + 1];
        int[] robotSpot = new int[N + 1];

        int[] step = {1};
        int upDown = 0;
        Arrays.stream(reader.readLine().split(" "))
            .forEach(i -> {
                int nowStep = step[0];
                int current = upDown;
                // 만약 2배가 넘어가면 초기화
                if (step[0] > 2 * N) {
                    step[0] = 1;
                    nowStep = 1;
                } else if (step[0] > N) {
                    nowStep = step[0] - (2 * (step[0] - N) - 1);
                    current = 1;
                }
                if (Integer.parseInt(i) == 0) {
                    zeroCount += 1;
                }

                containerHealth[current][nowStep] = Integer.parseInt(i);
                step[0] += 1;
            });

        int s = 1;

        int process = 0;
        while (true) {
//            System.out.println("process : " + process);
//            for (int[] ints : containerHealth) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println(Arrays.toString(robotSpot));
//            System.out.println();
            // 한 칸 회전
            if (process == 0) {
                shiftContainer(containerHealth, robotSpot, N);

            } else if (process == 1) {
                // 로봇 이동
                moveRobot(containerHealth, robotSpot);

            } else if (process == 2) {
                // [1] 에 로봇 올리기
                if (containerHealth[0][1] > 0) {
                    containerHealth[0][1] -= 1;
                    robotSpot[1] = 1;
                    if (containerHealth[0][1] == 0) {
                        zeroCount += 1;
                    }
                }
            } else {
//                System.out.println("step : " + (s + 1));
                if (zeroCount >= firstLine[1]) {
//                    System.out.println(zeroCount + " : " + firstLine[1]);
                    break;
                }
                s += 1;
            }
            process = (process + 1) % 4;
        }

        System.out.println(s);

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
