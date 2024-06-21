package Hash;

import utils.AlgoStudy;

import java.lang.reflect.Array;
import java.util.*;

public class SelectTangerine implements AlgoStudy {
    static class TangerineSize {
        int size;
        int count;

        public TangerineSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public int getCount() {
            return count;
        }

        public void countPlus() {
            this.count+=1;
        }

        @Override
        public String toString() {
            return "TangerineSize{" +
                    "size=" + size +
                    ", count=" + count +
                    '}';
        }
    }

    public int solution(int k, int[] tangerine) {

        HashMap<Integer, Integer> check = new HashMap<>();
        ArrayList<TangerineSize> dp = new ArrayList<>();

//        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(tangerine));

        for (int tan : tangerine) {
            if (check.getOrDefault(tan, null) == null) {
                check.put(tan,dp.size());
                dp.add(new TangerineSize(tan));
            }
            dp.get(check.get(tan)).countPlus();

        }

        dp.sort(new Comparator<TangerineSize>() {
            @Override
            public int compare(TangerineSize o1, TangerineSize o2) {
                return o2.count - o1.count;
            }
        });

        System.out.println(dp);

        int answer = 0;
        for (TangerineSize tanInfo : dp) {
            if (tanInfo == null) {
                continue;
            }

            k -= tanInfo.getCount();
//            System.out.println(k);

            answer += 1;
            if (k <= 0) {
                break;
            }
        }
        return answer;
    }

    @Override
    public void init() {
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};

        int solution = solution(k, tangerine);
        System.out.println(solution);
    }
}
