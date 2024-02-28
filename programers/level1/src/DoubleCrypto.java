import java.util.ArrayList;

public class DoubleCrypto {
    public void solution() {
        String s = "kkk";
        String skip = "abcdefg";
        int index = 5;

        ArrayList<Character> alphabetInit = new ArrayList<>();
        String alphabetList = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < alphabetList.length(); i++) {
            char currentAlphabet = alphabetList.charAt(i);
            if (skip.indexOf(currentAlphabet) == -1) {
                alphabetInit.add(currentAlphabet);
            }

        }

        String answer = "";
        for (int i = 0; i < s.length(); i++) {

            char now = s.charAt(i);
            int loc = (alphabetInit.indexOf(now) + index) % alphabetInit.size();
            answer += alphabetInit.get(loc);

        }

        System.out.println(answer);
        System.out.println(alphabetInit);
    }

    public void anotherSolution() {
        String s = "aukks";
        String skip = "wbqd";
        int index = 5;

        StringBuilder answer = new StringBuilder();

        for (char letter : s.toCharArray()) {
            char temp = letter;
            int idx = 0;
            while (idx < index) {
                // z 면 a로 변환
                temp = temp == 'z' ? 'a' : (char) (temp + 1);

                if (!skip.contains(String.valueOf(temp))) {
                    idx += 1;
                }
            }
            answer.append(temp);
        }

        System.out.println(answer.toString());
    }
}
