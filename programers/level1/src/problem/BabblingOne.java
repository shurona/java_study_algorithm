package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BabblingOne {
    public int checkWord(String word, int index, char startChar) {
        String[] input = {"aya", "ye", "woo", "ma"};
        int output = -1;
        switch(startChar) {
            case 'a':
                if (word.length() > index + 2 && word.charAt(index + 1) == 'y' && word.charAt(index + 2) == 'a') {
                    output = 3;
                } else {
                    output = -1;
                }
                break;

            case 'y':
                if (word.length() > index + 1 && word.charAt(index + 1) == 'e') {
                    output = 2;
                } else {
                    output = -1;
                }
                break;

            case 'w':
                if (word.length() > index + 2 && word.charAt(index + 1) == 'o' && word.charAt(index + 2) == 'o') {
                    output = 3;
                } else {
                    output = -1;
                }
                break;

            case 'm':
                if (word.length() > index + 1 && word.charAt(index + 1) == 'a') {
                    output = 2;
                } else {
                    output = -1;
                }
                break;

            default:
                return -1;
        }

        return output;
    }

    public void solution() {

//        String[] babbling = {"aya", "yee", "u", "maa", "wyeoo"};
        String[] babbling = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
        String[] input = {"aya", "ye", "woo", "ma"};

        int count = 0;
        boolean check = false;
        for (String bab : babbling) {
            StringBuilder sb = new StringBuilder();
            String before = "";
            for (int index = 0; index < bab.length(); index++) {
                sb.append(bab.charAt(index));
                for (String ss : input) {
                    if (sb.toString().contains(ss)) {
                        if (sb.toString().length() == ss.length() && !before.equals(ss)) {
                            sb = new StringBuilder();
                            before = ss;
                        }
                    }
                }
            }
            if (sb.toString().isEmpty()) {
                 System.out.println("dd = " + bab);
                count += 1;
            }
        }
        System.out.println("count = " + count);
    }

    public void anotherSolution() {
        System.out.println("BabblingOne.solution");


        String[] babbling = {"aya", "yee", "u", "maa", "wyeoo"};


//        List<Integer> answer = new ArrayList<>();
        int count = 0;
        for (String bab : babbling) {
            System.out.println("bab " + bab);
            boolean isInclude = true;
            int checkIndex = 0;
            while (checkIndex < bab.length()) {
                char startChar = bab.charAt(checkIndex);
                int rst = checkWord(bab, checkIndex, startChar);

                if (rst > 0) {
                    checkIndex += rst;
                } else {
                    isInclude = false;
                    break;
                }
            }

            if (isInclude) {
                count +=1;
            }
        }

        System.out.println("answer = " + count);
    }
}
