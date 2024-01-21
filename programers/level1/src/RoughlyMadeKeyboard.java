import java.util.HashMap;

public class RoughlyMadeKeyboard {
    boolean writeWorld() {
        //
        String[] keymap = { "ABACD", "BCEFD" };
        String[] targets = { "ABCD","AABB" };

        int[] answer = new int[targets.length];

        HashMap<Character, Integer> charToNumber = new HashMap<>();

        for(String keymapEach : keymap) {
            for(int i = 0 ; i < keymapEach.length(); i++) {
                Character keyChar = keymapEach.charAt(i);

                Integer clickCount = charToNumber.getOrDefault(keyChar, 1000);

                // 클릭 카운트를 비교해 보고 최소를 넣어준다.
                if(i+1 < clickCount) {
                    charToNumber.put(keyChar, i + 1);
                }
            }
        }

        int index = 0;
        for(String target: targets) {
            int clickCount = 0;
            for(int i = 0; i < target.length(); i++) {
                Integer clickCountForResult = charToNumber.getOrDefault(target.charAt(i), -1);

                if(clickCountForResult == -1) {
                    clickCount = -1;
                    break;
                }

                clickCount += clickCountForResult;
            }

            answer[index] = clickCount;
            index++;
        }

        return true;
    }
}
