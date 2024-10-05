package test;

import test.custom.MyTest;
import test.domain.User;

import java.util.*;
import java.lang.invoke.*;
import java.util.stream.Collectors;

public class TestMain {
    /**
     * data 는 시작 값
     * @param arr { 1, 2, 3}
     * @param output { 0, 0, 0}
     * @param visited { false, false, false}
     * @param depth 0
     * @param r 시작 길이
     */
    public static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int r) {
        if (depth == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(output[i]);
            }
            System.out.println();
        }


        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, r);
                visited[i] = false;
            }
        }
    }

    public static void combination(int[] arr, boolean[] visited, int start, int depth, int r) {
        if (depth == r) {
//            System.out.print("시작 : " + start + "  ");
//            System.out.println(Arrays.toString(visited));
            for (int i = 0; i < arr.length; i++) {
                if(visited[i]) System.out.print(arr[i]);
            }
            System.out.println();
        }

        for (int i = start; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(arr, visited, i + 1, depth + 1, r);
                visited[i] = false;
            }
        }
    }

    public static void printToTwo(int num) {


        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int now = (num % 2);
            sb.append(now);
            num /= 2;
        }


        System.out.println(sb.reverse().toString());

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        int a = 10;
        a = (1 << 21) - 1;
//        a |= (1 << 2);
//        a &= ~(1 << 1);

//        a &= ~(1 << 3);
//
//        int i = a & 1 << 3;
//
//
//        System.out.println(i);
//        printToTwo(i);
//
//        a = 1 << 3;
//        System.out.println(a);
//        printToTwo(16);
//        printToTwo(8);
//
//        HashMap<Integer, Integer> aa = new HashMap<>();
//
//        aa.put(1, 1);



        int[] tp = new int[2];

        for (int i = 0; i < tp.length; i++) {
            System.out.println(i);
        }
    }
}
