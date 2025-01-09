package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/3758
 */
public class ScoreSorting implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int given = Integer.parseInt(reader.readLine());
        for (int allCount = 0; allCount < given; allCount++) {
            int[] array = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(i -> Integer.parseInt(i))
                .toArray();

            int n = array[0];
            int k = array[1];
            int t = array[2];
            int m = array[3];

            Map<Integer, TeamObj> store = new HashMap<>();

            for (int seq = 0; seq < m; seq++) {
                int[] scoreInfo = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(i -> Integer.parseInt(i))
                    .toArray();

                int teamId = scoreInfo[0];
                int problemId = scoreInfo[1];
                int score = scoreInfo[2];

                TeamObj teamInfo = store.getOrDefault(teamId, new TeamObj(teamId, 0, k + 1, 0));

                // 기존 값보다 들어온 값이 크면
                if (score > teamInfo.scoreList[problemId]) {
                    teamInfo.scoreSum += score - teamInfo.scoreList[problemId];
                    teamInfo.scoreList[problemId] = score;

                }
                teamInfo.lastReportTime = seq;
                teamInfo.reportCount += 1;

                store.put(teamId, teamInfo);

            }

            List<TeamObj> list = store.values().stream().sorted(new Comparator<TeamObj>() {
                @Override
                public int compare(TeamObj o1, TeamObj o2) {

                    if (o1.scoreSum == o2.scoreSum) {
                        if (o1.reportCount == o2.reportCount) {
                            return o1.lastReportTime - o2.lastReportTime;
                        } else {
                            return o1.reportCount - o2.reportCount;
                        }
                    } else {
                        return o2.scoreSum - o1.scoreSum;
                    }
                }
            }).collect(Collectors.toList());

            int rank = 1;
            for (TeamObj value : list) {
                if (value.teamId == t) {
                    System.out.println(rank);
                }
//                System.out.println(value);
                rank++;
            }
//            System.out.println();

//            break;
        }
        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class TeamObj {

        int teamId;
        int lastReportTime;
        int[] scoreList;
        int reportCount;
        int scoreSum;

        public TeamObj(int teamId, int lastReportTime, int problemLength, int reportCount) {
            this.teamId = teamId;
            this.lastReportTime = lastReportTime;
            this.scoreList = new int[problemLength];
            this.reportCount = reportCount;
        }

        @Override
        public String toString() {
            return "TeamObj{" +
                "teamId=" + teamId +
                ", lastReportTime=" + lastReportTime +
                ", scoreSum=" + Arrays.toString(scoreList) +
                ", reportCount=" + reportCount +
                ", sum=" + scoreSum +
                '}';
        }
    }
}
