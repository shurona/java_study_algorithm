package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.BaekAlgoStudy;

public class KmpString implements BaekAlgoStudy {

    public int[] findPi(String find, int findLength) {

        int[] pi = new int[find.length()];
        int left = 0;
        for (int right = 1; right < find.length(); right++) {
            int step = 0;
            // 같은 지 확인한다.
            while (right + step < findLength
                && find.charAt(left + step) == find.charAt(right + step)) {

                step += 1;
            }

            for (int next = 0; next < step; next++) {
                pi[right + next] = next + 1;
            }

            System.out.println(right + " : " + step);
            if (step > 0) {
                step -= 1;
            }
            right += step;
        }
//
//        for (int i = 1; i < findLength; i++) {
//            while (left > 0 && find.charAt(i) != find.charAt(left)) {
//                left = pi[left - 1];
//            }
//
//            if (find.charAt(i) == find.charAt(left)) {
//                left++;
//            }
//
//            pi[i] = left;
//        }

        return pi;
    }

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String base = reader.readLine();
            String find = reader.readLine();

//            System.out.println(base.charAt(22));

            // 전처리 과정 진행
            int[] pi = findPi(find, find.length());
            System.out.println(Arrays.toString(pi));
            List<Integer> arr = new ArrayList<>();
            int start = 0;
//            for (int i = 0; i < base.length(); i++) {
//                boolean allSame = true;
//                int step = 0;
//                for (int j = start; j < find.length(); j++) {
//                    if (i + step >= base.length()) {
//                        allSame = false;
//                        break;
//                    }
//                    if (base.charAt(i + step) != find.charAt(j)) {
//                        allSame = false;
//                        if (j != 0) {
//                            start = pi[j - 1];
//                            i = i + step - 1;
//                        } else {
//                            start = 0;
//                        }
////                        System.out.println(i + " : " + (i + step) + " : " + j);
////                        System.out.println(base.charAt(i + step) + " : " + find.charAt(j));
////                        System.out.println();
//
//                        break;
//                    } else {
//                        step += 1;
//                    }
//                }
//
//                if (allSame) {
//                    arr.add(i + 1 - start);
////                    System.out.println("야후 : " + (i - start));
//                }
//
//            }

            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int i = 0; i < base.length(); i++) {
//                System.out.println("i : " + i);
                // 일치하지 않으면 pi 배열로
                while (start > 0 && base.charAt(i) != find.charAt(start)) {
//                    System.out.println(i + " : " + (start));
                    start = pi[start - 1];
                }

                if (base.charAt(i) == find.charAt(start)) {// 패턴 전체가 일치하면 결과에 추가
                    if (start == find.length() - 1) {
                        count += 1; // 정답에 추가
                        sb.append(i - find.length() + 2).append(" ");
//                        System.out.println("젭알 : " + i);
                        start += 1;
                        start = pi[start - 1]; // 다음 비교를 위해 초기화
//                    i = i - (start + 1);
//                    start = 0;
//                        System.out.println("? : " + i + " : " + start);
//                        System.out.println();
                    } else {
                        start += 1;
                    }
                }


            }

            System.out.println(count);
//            for (Integer i : arr) {
//                sb.append(i + " ");
//            }
            System.out.println(sb.toString());
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    @Override
    public void init() throws IOException {
        String input = "ABC ABCDAB ABCDABCDABDE";

        solution();

//        System.out.println("길이 : " + input.length());

    }
}
