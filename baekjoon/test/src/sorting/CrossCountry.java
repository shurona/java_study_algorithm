package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.BaekAlgoStudy;

public class CrossCountry implements BaekAlgoStudy {

    public void sortTeam() {

    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int groupSize = Integer.parseInt(reader.readLine());

        for (int i = 0; i < groupSize; i++) {
            Map<Integer, Integer> store = new HashMap<>();
            Map<Integer, Team> tp = new HashMap<>();

            int allSize = Integer.parseInt(reader.readLine());

            int[] result = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(data -> Integer.parseInt(data))
                .toArray();

            for (int dt : result) {
                if (store.getOrDefault(dt, -1) == -1) {
                    store.put(dt, 1);
                } else {
                    store.put(dt, store.get(dt) + 1);
                }

                if (store.get(dt) == 6) {
                    tp.put(dt, new Team(dt));
                }
            }

            int currentScore = 0;
            for (int dt : result) {
                // 6명이 아니면 패스
                if (store.get(dt) != 6) {
                    continue;
                }
                currentScore++;

                Team currentTeam = tp.get(dt);

                currentTeam.score[currentTeam.currentIndex] = currentScore;
                currentTeam.currentIndex += 1;
            }

            for (Team value : tp.values()) {
                int o1Sum = value.score[0] + value.score[1] + value.score[2] + value.score[3];
                System.out.println(
                    value.teamNumber + " " + Arrays.toString(value.score) + " " + o1Sum);
            }

            List<Team> list = new ArrayList<>(tp.values().stream().toList());

            list.sort(new Comparator<Team>() {
                @Override
                public int compare(Team o1, Team o2) {

                    int o1Sum = o1.score[0] + o1.score[1] + o1.score[2] + o1.score[3];
                    int o2Sum = o2.score[0] + o2.score[1] + o2.score[2] + o2.score[3];
                    if (o1Sum == o2Sum) {
                        return o1.score[4] - o2.score[4];
                    } else {
                        return o1Sum - o2Sum;
                    }
                }
            });

            System.out.println(list.get(0).teamNumber);

//            System.out.println(store);
//            System.out.println(tp);

        }

        reader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }

    public static class Team {


        int[] score;
        int currentIndex;
        int teamNumber;

        public Team(int num) {
            score = new int[6];
            currentIndex = 0;
            teamNumber = num;
        }
    }
}
