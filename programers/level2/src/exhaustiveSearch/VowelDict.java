package exhaustiveSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VowelDict {
    public int wordFinder(String[] dict, boolean[] visitChecked, List<Integer> checkWord, String wishWord) {
        int count = 1;
        String currentWord = "";
        // 오른쪽이 비어있으면 넘어가고
        // 자리수가 넘어가면 A로 초기화가 되고
        // 끝에가 U가 되면 기존으로 되돌아온다.
        while (!Objects.equals(currentWord, wishWord)) {
//            System.out.println("currentWord = " + currentWord);
            if (count == 10000) {
//                System.out.println(currentWord);
                break;
            }
            // 오른쪽이 비어 있는지 확인한다.
            if (checkWord.size() < 5) {
                checkWord.add(0);
            }
            // 마지막 단어가 U인지 확인한다.
            else if (checkWord.get(checkWord.size() - 1) == 4) {
                int currentSize = checkWord.size();
                for (int index = 0; index < currentSize - 1; index++) {
                    checkWord.remove(checkWord.size() - 1);
                    Integer lastWord = checkWord.get(checkWord.size() - 1);
                    // 마지막 단어가 U가 아니면 break 아니면 하나 증가시켜준다.;
                    if (lastWord != 4) {
                        checkWord.set(checkWord.size() - 1, lastWord + 1);
                        break;
                    }
                }
            }
            // 길이가 마지막이면 1 증가시킨다.
            else if (checkWord.size() == 5) {
                checkWord.set(checkWord.size() - 1, checkWord.get(checkWord.size() - 1) + 1);
            }

            //
            count+=1;
            // 현재 단어를 확인한다.
            StringBuilder stringBuilder = new StringBuilder();
            for (int wordIndex = 0; wordIndex < checkWord.size(); wordIndex++) {
                stringBuilder.append(dict[checkWord.get(wordIndex)]);
            }

            currentWord = stringBuilder.toString();
//            break;
        }

        return count;
    }

    public void solution() {

//        String word = "AAAAE";
//        String word = "AAAE";
        String word = "U";
//        String word = "AA";
//        String word = "UAA";

        String[] dictionary = {"A", "E", "I", "O", "U"};
        boolean[] visitCheck = new boolean[5];
        List<Integer> start = new ArrayList<>();

        if(Objects.equals(word, "A")) System.out.println("야후");

        start.add(0);
        //3905

        int answer = wordFinder(dictionary, visitCheck, start, word);

        System.out.println("answer = " + answer);
        System.out.println("VowelDict.solution : " + (answer));

//        return answer + baseAnswer
    }

    List<String> list = new ArrayList<>();
    void dfs(String str, int len) {
        if(len > 5) return;
        list.add(str);
        for(int i = 0; i < 5; i++) {
            // 이게 순서가 보장되는구나
            dfs(str + "AEIOU".charAt(i), len + 1);
        }
    }

    public void anotherSolution() {
        String word = "AAAAE";
        dfs("", 0);
        int i = list.indexOf(word);
        System.out.println("i = " + i);
    }
}
