package Hash;

import java.util.*;

public class PhoneBookList {
    public void checkPrefix() {
        String[] phone_book = {"119", "97674223", "1195524421"};

        boolean answer = true;

        HashMap<String, Boolean> code = new HashMap<String, Boolean>();

        for(String phoneNumber: phone_book) {
            code.put(phoneNumber, true);
        }

        for(String phoneNumber: phone_book) {
            for(int i = 0 ;  i < phoneNumber.length(); i++) {
                if(code.containsKey(phoneNumber.substring(0, i))){
                    answer = false;
                }
            }
        }
    }

    public boolean anotherSol() {
        String[] phoneBook = {"119", "97674223", "1195524421"};
        Arrays.sort(phoneBook);

        boolean result = true;
        for (int i=0; i<phoneBook.length-1; i++) {
            if (phoneBook[i+1].startsWith(phoneBook[i])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
