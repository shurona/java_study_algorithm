package exhaustiveSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FindPrimeNumber {
    Set<Integer> answer = new HashSet<>();
    boolean checkPrimeNumber(int number) {

        if (number == 1) {
            return false;
        }

        if (number == 2) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        int sqrt = (int) Math.sqrt(number);
        System.out.println(sqrt);
        for (int i = 3; i <= sqrt; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    int permutation(int[] arr, int[] output, boolean[] visited, int depth, int r) {
        if (depth == r) {
            StringBuilder tp = new StringBuilder();
            for (int i = 0; i < r; i++) {
                tp.append(output[i]);
//                System.out.print(output[i] + " ");
            }
            boolean isPrime = checkPrimeNumber(Integer.parseInt(tp.toString()));
            if (isPrime) {
                answer.add(Integer.parseInt(tp.toString()));
            }
//            System.out.print("숫자 " + tp.toString() + " 소수인가요? : " + isPrime);
//            System.out.println();

            return 0;
        }

        for (int i = 0; i < output.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth +1, r);
                visited[i] = false;
            }
        }

        return 0;
    }

    public void solution() {
//        String numbers = "17";
        String numbers = "011";

        // 숫자로 쪼개기
        ArrayList<Integer> divNumber = new ArrayList<>();

        for (int i = 0; i < numbers.length(); i++) {
            divNumber.add(Character.getNumericValue(numbers.charAt(i)));
        }

        int[] output = new int[divNumber.size()];
        boolean[] visited = new boolean[divNumber.size()];

        // div Number 로 만들 수 있는 조합
        for (int i = 0; i < divNumber.size(); i++) {
            permutation(divNumber.stream().mapToInt(num -> num).toArray(), output, visited, 0,i + 1);
        }
        System.out.println("길이 : " + answer.size());
    }
}
