package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1522
 */
public class StringChange implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Character> store = new ArrayList<>();

        String s = reader.readLine();
        int bCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                bCount += 1;
            }
            store.add(s.charAt(i));
        }

        int middleBCount = 0;
        for (int i = 0; i < s.length() - bCount; i++) {
            if (s.charAt(i) == 'b') {
                middleBCount += 1;
            }
        }

        int windowSize = s.length() - bCount;
        int leftLoc = 1;
        int rightLoc = leftLoc + windowSize - 1;
        int answer = middleBCount;
//        System.out.println(leftLoc + " : " + rightLoc);
        for (int i = 1; i < s.length(); i++) {
            // 앞에서 윈도우 크기만큼 이동
            char delete = s.charAt(leftLoc - 1);
            char add = s.charAt(rightLoc % s.length());

            if (delete == 'b') {
                middleBCount -= 1;
            }

            if (add == 'b') {
                middleBCount += 1;
            }

            answer = Math.min(answer, middleBCount);

            leftLoc += 1;
            rightLoc += 1;

        }

//        System.out.println(bCount + " : " + middleBCount);
        System.out.println(answer);

        reader.close();

    }

    public void wrongSolution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Character> storeOne = new ArrayList<>();
        List<Character> storeTwo = new ArrayList<>();

        String s = reader.readLine();
        int lastBIndex = -1;
        int aCount = -1;
        int lastBeforeAIndex = -1;
        int lastBeforeBIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                if (lastBIndex == -1) {
                    lastBIndex = i;
                } else {
                    lastBeforeAIndex = aCount;
                    lastBeforeBIndex = lastBIndex;
                    lastBIndex = i;
                }
            } else if (s.charAt(i) == 'a') {
                aCount = i;
            }
            storeOne.add(s.charAt(i));
            storeTwo.add(s.charAt(i));
        }

//        System.out.println(lastBeforeBIndex + " : " + lastBIndex);
//        System.out.println(storeOne);

        int frontAnswer = 0;

        // 앞에서 부터 b를 a와 교환
        int cursor = 0;
        int rightCursor = lastBIndex;
        while (cursor < rightCursor) {
            if (storeOne.get(cursor) == 'b') {
                for (int i = rightCursor; i > cursor; i--) {
                    rightCursor -= 1;
                    if (storeOne.get(i) == 'a') {
//                        System.out.println("야후 : " + cursor + " : " + i + " : " + rightCursor);
                        storeOne.set(cursor, 'a');
                        storeOne.set(i, 'b');
                        frontAnswer += 1;

//                        while (storeTwo.get(rightCursor) == 'b') {
////                            System.out.println("?");
//                            rightCursor -= 1;
//
//                        }

                        break;
                    }

                }
            }
//            System.out.println(storeOne + " : " + cursor + " : " + rightCursor);
            cursor += 1;
        }

        // 앞에서 부터 a를 b와 교환
        int backAnswer = 0;
        cursor = 0;
        for (int i = 0; i < storeTwo.size() - 1; i++) {
            if (storeTwo.get(i) == 'b') {
                for (int j = i + 1; j < storeTwo.size(); j++) {
                    if (storeTwo.get(j) == 'a') {
                        cursor = j;
                        break;
                    }
                }
                break;
            }
        }

        rightCursor = storeTwo.size() - 1;
        while (cursor < rightCursor) {
            if (storeTwo.get(cursor) == 'a') {
                for (int i = rightCursor; i > cursor; i--) {
                    rightCursor -= 1;
                    if (storeTwo.get(i) == 'b') {
//                        System.out.println("야후 : " + cursor + " : " + rightCursor);
                        storeTwo.set(cursor, 'b');
                        storeTwo.set(i, 'a');
                        backAnswer += 1;
//                        while (storeTwo.get(rightCursor) == 'b') {
//                            rightCursor -= 1;
//
//                        }

                        break;
                    }

                }
            }
            System.out.println(storeTwo);
            cursor += 1;
        }

        System.out.println(frontAnswer + " : " + backAnswer);
        System.out.println(Math.min(frontAnswer, backAnswer));
        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
