package problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import utils.BaekAlgoStudy;

public class SwitchSwitchButton implements BaekAlgoStudy {

    // 남학생이면 해당 배수의 스위치를 반대로 한다.
    public static void boySwitch(int[] arr, int input) {
        for (int i = 1; i <= arr.length; i++) {
            int loc = i * input;
            if (loc >= arr.length) {
                break;
            }
            arr[loc] = arr[loc] ^ 1;
        }
    }

    // 여학생이면 양옆이 달라질 때 까지 진행
    public static void girlSwitch(int[] arr, int input) {
        int loc = input;
        // 범위 벗어나면 패스
        if (loc < 0 || loc >= arr.length) {
            return;
        }

        // 현재 위치는 일단 바꾼다.
        arr[loc] = arr[loc] ^ 1;

        for (int i = 1; i < arr.length; i++) {

            // 하나라도 범위를 벗어나면 멈춘다.
            if (loc + i >= arr.length || loc - i < 0) {
                break;
            }

            // 양 옆이 다르면 멈춘다.
            if (arr[loc - i] != arr[loc + i]) {
                break;
            }

            // 같으면 바꾼다.
            arr[loc - i] = arr[loc - i] ^ 1;
            arr[loc + i] = arr[loc + i] ^ 1;
        }


    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int switchCount = Integer.parseInt(reader.readLine());

        List<Integer> tempList = new ArrayList<>();
        tempList.add(0); // 앞에 0 추가
        tempList.addAll(Arrays.stream(reader.readLine().split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList()));

        int[] switchList = tempList.stream().mapToInt(i -> i).toArray();

        int studentCount = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(switchList)).append("\n");
        for (int i = 0; i < studentCount; i++) {
            int[] line = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(a -> Integer.parseInt(a))
                .toArray();

            int gender = line[0];

            // 여성인 경우와 나머지
            if (gender == 2) {
                girlSwitch(switchList, line[1]);
            } else {
                boySwitch(switchList, line[1]);
            }

            sb.append(Arrays.toString(switchList)).append("\n");

        }

//        System.out.println(sb.toString());
//        System.out.println(Arrays.toString(switchList));

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < switchList.length; i++) {
            answer.append(switchList[i]).append(" ");
            if (i % 20 == 0) {
//                System.out.println(i);
                answer.append("\n");
            }
        }

        System.out.println(answer.toString());
        reader.close();

    }

    public void anotherSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] sw = new int[N + 1];    // 스위치는 1번부터이므로 N+1개 배열 생성

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sw[i] = Integer.parseInt(st.nextToken());
        }

        int S = Integer.parseInt(br.readLine());
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());    // 성별
            int num = Integer.parseInt(st.nextToken());        // 받은 수

            if (gender == 1) {    // 남학생
                int count = 1;
                while (num * count <= N) {    // 스위치 개수 전까지 반복
//                    System.out.println(num * count);
                    sw[num * count] ^= 1;    // 받은 수의 배수를 인덱스로 활용하여 접근, XOR 연산으로 스위치 토글
                    count++;
                }
            } else {    // 여학생
                int start = num - 1;
                int end = num + 1;
                while (start >= 1 && end <= N) {    // 스위치 범위 안에서 반복
                    if (sw[start] != sw[end]) {
                        break;    // 좌우 대칭 아니면 탈출
                    }
                    start--;
                    end++;
                }
                start++;
                end--;    // 좌우 대칭이 아니기 바로 전 상태는 좌우 대칭

                for (int j = start; j <= end; j++) {
                    sw[j] ^= 1;    // XOR 연산으로 스위치 토글
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            bw.write(sw[i] + " ");
            if (i % 20 == 0) {    // 한 줄에 20개씩 출력
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    @Override
    public void init() throws IOException {
        solution();
//        anotherSolution();
    }
}
