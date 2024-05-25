package search;

import utils.AlgoStudy;

import java.util.Arrays;
import java.util.HashSet;

public class SumOfSubsequence implements AlgoStudy {
    HashSet<Integer> ans = new HashSet<>();
    public void dfs(int[] doubleElements, boolean[] visit, int ii,  int count) {
        for (int i = 0; i < doubleElements.length / 2; i++) {
            int sum = 0;
            for (int j = 0; j < doubleElements.length / 2; j++) {
                sum += doubleElements[j + i];
                ans.add(sum);
            }
        }
    }

    public int solution(int[] elements) {
        //
        boolean[] visit = new boolean[elements.length];

        int[] doubleElements = new int[elements.length * 2];

        for (int i = 0; i < elements.length; i++) {
            doubleElements[i] = elements[i];
            doubleElements[i + elements.length] = elements[i];
        }

        dfs(elements, visit, -1, 0);

        System.out.println("doubleElements = " + Arrays.toString(doubleElements));
        System.out.println("ans = " + ans);
        System.out.println("ans = " + ans.size());
        int answer = 0;
        return ans.size();
    }

    public int anotherSolution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
//        int[] dp = new int[elements.length];
        for (int len = 1; len <= elements.length; len++) {
            int sum = 0;
            for (int i = 0; i < elements.length; i++) {
                // 나머지 까지 돌림
                sum += elements[(len + i - 1) % elements.length];
                System.out.println("sum = " + sum);
                set.add(sum);
            }
            System.out.println();
        }
        return set.size();
    }

    @Override
    public void init() {

        int[] input = {7, 9, 1, 1, 4};
        int output = anotherSolution(input);
        System.out.println("output = " + output);

    }
}
