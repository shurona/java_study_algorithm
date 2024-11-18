package MapSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ReportUser {

    Map<String, HashSet<String>> reportInfo = new HashMap<>();
    Map<String, HashSet<String>> reportCount = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        for (String id : id_list) {
            reportInfo.put(id, new HashSet());
            reportCount.put(id, new HashSet());
        }

        for (String rep : report) {
            String[] parseRep = rep.split(" ");
            String reporter = parseRep[0];
            String badBoy = parseRep[1];
            reportInfo.get(reporter).add(badBoy);
            reportCount.get(badBoy).add(reporter);
        }

        int[] answer = new int[id_list.length];
        int index = 0;
        for (String id : id_list) {
            for (String badBoy : reportInfo.get(id)) {
                int ct = reportCount.get(badBoy).size();
                if (ct >= k) {
                    answer[index] += 1;
                }
            }
            index += 1;
        }

        // System.out.println(reportInfo);

        return answer;
    }

}
