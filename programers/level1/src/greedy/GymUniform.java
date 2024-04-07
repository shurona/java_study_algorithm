package greedy;

import java.util.Arrays;

public class GymUniform {
    public void solution() {
        System.out.println("GymUniform.solution");
        int n = 5;
        int[] lost= {2, 4, 5};
        int[] reserve = { 1, 3, 5};

//        int[] lost = {2, 3, 4};
//        int[] reserve = {2, 3, 4};

        Arrays.sort(reserve);

        boolean[] checkUniform = new boolean[n + 2];
        Arrays.fill(checkUniform, true);

        for (int lostIndex : lost) {
            checkUniform[lostIndex] = false;
        }


        for(int i = 0; i < reserve.length; i++){
            int ress = reserve[i];
            if (!checkUniform[ress]) {
                checkUniform[ress] = true;
            }
            reserve[i] = 10000;
        }

        System.out.println("reserve = " + Arrays.toString(reserve));

        for (int res : reserve) {
            if(res == 10000){
                continue;
            }

            // 하나 아래
            if (!checkUniform[res - 1]) {
                checkUniform[res -1] = true;
                continue;
            }

            // 하나 뒤
            if (!checkUniform[res + 1]) {
                checkUniform[res + 1] = true;
                continue;
            }
            //
        }

        int falseCount = 0;
        for (boolean b : checkUniform) {
            if (!b) {
                falseCount += 1;
            }
        }


        System.out.println(Arrays.toString(checkUniform));
        System.out.println("answer = " + (n - falseCount));

    }
}
