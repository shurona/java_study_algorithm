import java.util.*;
import java.util.stream.Collectors;
public class Phonekemon {
    void ChooseCharacter() {

        int[] nums = { 3,1,2,3 };

        int pickNum = nums.length/2;


        Set<Integer> tp = new HashSet<Integer>();

        for(int num: nums) {
            tp.add(num);
        }

        int answer =  Math.min(tp.size(), pickNum);

    }

    int specialTools() {
        int[] nums = { 3,1,2,3 };
        {
            return Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.collectingAndThen(Collectors.toSet(),
                            phonekemons -> Integer.min(phonekemons.size(), nums.length / 2)));
        }
    }

}
