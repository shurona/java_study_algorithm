package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class DiskController {
    public void solution() {
//        int[][] jobs = {{0, 3}, {1, 9},{2, 6}, { 4, 6}};
//        int[][] jobs = {{0, 10}, {4, 10}, {5, 11}, {15, 2}};
        int[][] jobs = {{10,1}, {19,21}, {10000,3}};

        //
        PriorityQueue<Integer[]> processQueue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if(Objects.equals(o1[0], o2[0])){
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }

            }
        });

        PriorityQueue<Integer[]> tempProcess = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (int[] job : jobs) {
            processQueue.add(Arrays.stream(job).boxed().toArray(Integer[]::new));
        }

        int endTime = processQueue.peek()[0];
        int sumOfProcess = 0;
        Integer[] arr;
        while (!processQueue.isEmpty() || !tempProcess.isEmpty()) {
            // 만약 시작 시간이 끝난 시간보다 크면 대기열이 비어있으면
            if (!processQueue.isEmpty() && endTime <= processQueue.peek()[0] && tempProcess.isEmpty()) {
                arr = processQueue.poll();
                endTime = arr[0] + arr[1];
                sumOfProcess += (endTime - arr[0]);
                System.out.println(Arrays.toString(arr));
                System.out.println("걸린 시간 : " + (endTime - arr[0]) + " 끝 시간 : " + endTime);
            }
            // 만약 temp Process가 있는데 이전에 들어온다
            else if (!processQueue.isEmpty() && endTime > processQueue.peek()[0]) {
                arr = processQueue.poll();;
                tempProcess.add(arr);
            }
            // 이후 값이 들어왔는데 tempProcess가 있다면
            else {
                // tempProcess에 있는지 확인
                if (!tempProcess.isEmpty()) {
                    arr = tempProcess.poll();
                } else {
                    arr = processQueue.poll();
                }

                endTime += arr[1];
                sumOfProcess += (endTime - arr[0]);
                System.out.println(Arrays.toString(arr));
                System.out.println("걸린 시간 : " + (endTime - arr[0]) + " 끝 시간 : " + endTime);
            }
        }

        System.out.println("완료 : " + Math.floor((double) sumOfProcess / jobs.length));
    }

    public void anotherSolution() {
        int[][] jobs = {{0, 3}, {1, 9},{2, 6}};
//        int[][] jobs = {{0, 10}, {4, 10}, {5, 11}, {15, 2}};
//        int[][] jobs = {{10,1}, {19,21}, {10000,3}};

        int answer = 0;

        // 작업이 요청되는 시점 기준으로 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 작업의 소요시간 기준으로 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int jobs_index = 0; // 작업 배열 인덱스
        int finish_job = 0; // 처리 완료된 작업 개수
        int end_time = 0; // 작업 처리 완료 시간

        while(true) {
            if(finish_job == jobs.length) break; // 모든 작업을 처리했다면 종료

            // 이전 작업 처리 중 요청된 작업 add
            while(jobs_index < jobs.length && jobs[jobs_index][0] <= end_time) {
                pq.add(jobs[jobs_index++]);
            }

            if(!pq.isEmpty()) { // 이전 작업 처리 중 요청된 작업이 있는 경우
                int[] job = pq.poll();
                answer += end_time - job[0] + job[1]; // 작업 요청부터 종료까지 걸린 시간 추가
                end_time += job[1]; // 작업 처리 완료 시간 갱신
                finish_job++; // 처리 완료된 작업 개수 1 증가
            } else { // 이전 작업 처리 중 요청된 작업이 없는 경우
                end_time = jobs[jobs_index][0]; // 다음 작업 요청 시점으로 갱신
            }
        }
        System.out.println(answer);
    }
}
