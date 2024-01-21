import java.util.*;

public class NotCompleteMarathon {
    boolean findIt() {

        String[] participant = { "mislav", "stanko", "mislav", "ana" };
        String[] completion = { "stanko", "ana", "mislav" };

        String answer = "";

        HashMap <String, Integer> completioncheck = new HashMap<String, Integer>();

        for(String name: completion) {
            int yahoo = completioncheck.getOrDefault(name, 0);
            completioncheck.put(name, yahoo + 1);
        }

        for(String participantName: participant ) {
            int output = completioncheck.getOrDefault(participantName, -1);

            System.out.println(output);

            if(output <= 0) {
                answer = participantName;
                break;
            }

            completioncheck.put(participantName, output - 1);

        }

        System.out.println(answer);

        return true;
    }
}
