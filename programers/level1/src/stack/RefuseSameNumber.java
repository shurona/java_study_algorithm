package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RefuseSameNumber {
    public void solution() {
        int[] arr = { 1,1,3,3,0,1,1 };

        List<Integer> output = new ArrayList<>();

        int beforeNumber = -1;
        for(int number: arr) {
            if (number != beforeNumber) {
                output.add(number);
            }
            beforeNumber = number;
        }

        int[] answer = output.stream().mapToInt(data -> data).toArray();

    }
}
