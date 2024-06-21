package dfsbfs;

import utils.AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupPhoto implements AlgoStudy {

    HashMap<String, ArrayList<Condition>> forCheck;

    public static class Condition {
        char c;
        String target;
        int range;

        public Condition(char c, String target, int range) {
            this.c = c;
            this.target = target;
            this.range = range;
        }

        @Override
        public String toString() {
            return "Condition{" +
                    "c=" + c +
                    ", target='" + target + '\'' +
                    ", range=" + range +
                    '}';
        }
    }

    public boolean checkCondition(String[] dp, int index, List<Condition> conditionList) {
        boolean isOk = true;
        for (Condition condition : conditionList) {
            switch(condition.c) {
                case '<':
                    int lp = index - condition.range;
                    for (int i = 0; i <= lp; i++) {
                        if (dp[i].equals(condition.target)) {
                            isOk = false;
                            break;
                        }
                    }
                    break;

                case '>':
                    int llpp = index - condition.range - 1;
                    for (int i = llpp; i < index; i++) {
                        if (i < 0) {
                            continue;
                        }
                        if (dp[i].equals(condition.target)) {
                            isOk = false;
                            break;
                        }
                    }
                    break;

                case '=':
                    int leftPos = index - (condition.range + 1);
//                    if (leftPos >= 0 && dp[leftPos].equals(condition.target)) {
//                        break;
//                    }
                    for (int i = 0; i < index; i++) {
                        if (i == leftPos) {
                            continue;
                        }
                        // 만약 있으면 break
                        if (dp[i].equals(condition.target)) {
                            isOk = false;
                            break;
                        }
                    }
                    break;

                default:
                    break;
            }

        }
        return isOk;
    }

    int answer = 0;

    public void dfs(String[] dp, String[] members, boolean[] visit, int count) {
        if (count == 9) {
            if (answer > 100 && answer < 200) {
                System.out.println(Arrays.toString(dp));
            }
            answer+=1;
            return;
        }

        for (int i = 0; i < members.length; i++) {
            if (!visit[i]) {
                // 여기서 확인
                if (checkCondition(dp, count - 1, this.forCheck.get(members[i]))) {
                    visit[i] = true;
                    dp[count - 1] = members[i];
                    dfs(dp, members, visit, count + 1);
                    dp[count - 1] = null;
                    visit[i] = false;
                }
            }

        }

    }

    public int solution(int n, String[] data) {
        String[] members = {"A", "C", "F", "J", "M", "N", "R", "T"};

        answer = 0;
        this.forCheck = new HashMap<>();

        // hashMap init
        for (String member : members) {
            this.forCheck.put(member, new ArrayList<>());
        }

        // data 파싱
        for (String datum : data) {
            String memOne = String.valueOf(datum.charAt(0));
            String memTwo = String.valueOf(datum.charAt(2));
            char c = datum.charAt(3);
            int range = Integer.parseInt(String.valueOf(datum.charAt(4)));

            this.forCheck.get(memOne).add(new Condition(c, memTwo, range));
            this.forCheck.get(memTwo).add(new Condition(c, memOne, range));
        }
//        System.out.println(this.forCheck);
        String[] dp = new String[members.length];
        dfs(dp, members, new boolean[members.length], 1);

        return answer;
    }

    @Override
    public void init() {
        int n = 2;
//        String[] data = {"N~F=0", "R~T>2"};
//        String[] data = {"M~C<2", "C~M>1"};
//        String[] data = {"N~F=0"};
        String[] data = {"M~C>6"};
        int solution = solution(n, data);
        System.out.println(solution);
    }
}
