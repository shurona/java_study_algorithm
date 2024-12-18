package StringParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import utils.BaekAlgoStudy;

public class AcceptablePassword implements BaekAlgoStudy {

    public void solution() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            HashMap<Character, Integer> data = new HashMap<>();

            String alpha = "abcdefghijklmnopqrstuvwxyz";

            for (int i = 0; i < alpha.length(); i++) {
                char c = alpha.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    data.put(c, 0);
                } else {
                    data.put(c, 1);
                }
            }

//            System.out.println(data);

            reader.lines()
                .takeWhile(s -> !s.equals("end"))
                .forEach(s -> {
                    boolean isOk = true;
                    int beforeStat = data.get(s.charAt(0));
                    int ct = 1;

                    boolean checkVowel = beforeStat == 0;

                    char before = s.charAt(0);
                    for (int i = 1; i < s.length(); i++) {
                        char current = s.charAt(i);
                        int currentStat = data.get(s.charAt(i));

                        if (current != 'e' && current != 'o') {
                            // 두 개가 같으면
                            if (current == before) {
                                System.out.println("두 개가 달라요 : " + i);
                                isOk = false;
                                break;
                            }
                        }

                        // 모음이 한 번이라도 있는지 확인
                        if (currentStat == 0) {
                            checkVowel = true;
                        }

                        if (beforeStat == currentStat) {
                            ct += 1;
                        } else {
                            ct = 1;
                        }

                        // 3번 연속인지 확인
                        if (ct == 3) {
                            System.out.println("세번연속 : " + i);
                            isOk = false;
                            break;
                        }

                        beforeStat = currentStat;
                        before = current;
                    }

                    if (!checkVowel) {
                        System.out.println("?");

                        isOk = false;
                    }

                    if (isOk) {
                        System.out.println("<" + s + "> is acceptable.");
                    } else {
                        System.out.println("<" + s + "> is not acceptable.");
                    }

                });

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        } finally {

        }

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
