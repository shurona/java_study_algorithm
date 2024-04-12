package greedy;

import java.util.Arrays;
import java.util.Stack;

/**
 * 최소한의 숫자로 모든 사람을 옮겨야 한다.
 */
public class Lifeboat {
    public void solution() {

//        int[] people = {70, 50, 80, 50};
//        int limit = 100;

//        int[] people = {70, 80, 50};
//        int limit = 100;

//        int[] people = {20, 50, 50, 80};
//        int limit = 100;

        int[] people = {20, 20, 20, 20};
        int limit = 100;

        Arrays.sort(people);
        int leftIndex = 0;
        int rightIndex = people.length - 1;
        int answer = 0;
        int tempLimit;
        while (leftIndex <= rightIndex) {
            if (leftIndex == rightIndex) {
                answer+=1;
                break;
            }

            if (people[leftIndex] + people[rightIndex] <= limit) {
                leftIndex +=1;
                rightIndex-=1;
                tempLimit = limit - (people[leftIndex] + people[rightIndex]);
                //
//                int tt = leftIndex;
//                for (int i = tt; i <= rightIndex; i++) {
//                    if (people[i] <= tempLimit) {
//                        tempLimit -= people[i];
//                        leftIndex +=1;
//                    }
//                }

                answer +=1;
            }
            else {
                rightIndex -= 1;
                answer +=1;
            }
        }

        System.out.println("answer = " + answer);
    }
}
