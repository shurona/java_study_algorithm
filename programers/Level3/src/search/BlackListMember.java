package search;

import utils.AlgoLevel3Study;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class BlackListMember implements AlgoLevel3Study {
    String[] tp = new String[3];
    HashSet<String> answer = new HashSet<>();

    public void dfs(String[] user_id, String[] banned_id, boolean[] visited, int currentIndex) {
        if (currentIndex == banned_id.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(i);
                }
            }
            answer.add(sb.toString());
            return;
        }

        String currentBanId = banned_id[currentIndex];
        // 하나씩 비교 해본다.
        for (int userIndex = 0; userIndex < user_id.length; userIndex++) {
            String currentUserId = user_id[userIndex];
            int samecount = 0;
            // 확인 했 거나 두 개의 길이가 다르면 패스
            if (visited[userIndex] || currentBanId.length() != currentUserId.length()) {
                continue;
            }

            // 밴 아이디를 하나씩 비교 해 본다.
            for (int compareIndex = 0; compareIndex < currentUserId.length(); compareIndex++) {
                char u = currentUserId.charAt(compareIndex);
                char b = currentBanId.charAt(compareIndex);

                if (b == '*' || u == b) {
                    samecount += 1;
                }
            }

            // 같은 지 확인한다.
            if (samecount == currentBanId.length()) {
                visited[userIndex] = true;
                this.tp[currentIndex] = currentUserId;
                dfs(user_id, banned_id, visited, currentIndex + 1);
                this.tp[currentIndex] = null;
                visited[userIndex] = false;
            }
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        // 확인
        boolean[] visited = new boolean[user_id.length];
        dfs(user_id, banned_id, visited, 0);

        return answer.size();
    }

    @Override
    public void init() {
//        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc" };
//        String[] banned_id = {"fr*d*", "abc12*"};

        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        int solution = solution(user_id, banned_id);
        System.out.println(solution);

    }
}
