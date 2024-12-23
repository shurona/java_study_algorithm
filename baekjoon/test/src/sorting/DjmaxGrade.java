package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import utils.BaekAlgoStudy;

public class DjmaxGrade implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Long[] input = Arrays.stream(reader.readLine().split(" "))
            .map(i -> Long.parseLong(i))
            .toArray(Long[]::new);

        long arrLength = input[0];
        long myScore = input[1];
        long rankSize = input[2];

        Long[] scoreList;
        if (arrLength > 0) {
            scoreList = Arrays.stream(reader.readLine().split(" "))
                .map(i -> Long.parseLong(i))
                .toArray(Long[]::new);
        } else {
            System.out.println(1);
            return;
        }

        Arrays.sort(scoreList, new Comparator<Long>() {
            public int compare(Long o1, Long o2) {
                return o2.compareTo(o1);
            }
        });

        int rank = 0;
        long before = -11;
        int step = 0;
        int answer = -1;
        for (int i = 0; i < rankSize; i++) {
            step += 1;

            // 끝 확인
            if (scoreList.length == i) {
                if (myScore < before) {
                    rank += step;
                }
                answer = rank;
                break;
            }

            if (myScore > scoreList[i]) {
                if (myScore < before) {
                    rank += step;
                }
                if (rank == 0) {
                    rank = 1;
                }
                answer = rank;
                break;
            }

            if (before != scoreList[i]) {
                rank += step;
                step = 0;
                System.out.println(scoreList[i] + " : " + rank);
                before = scoreList[i];
            }


        }

        System.out.println(Arrays.toString(scoreList));
        System.out.println(answer);

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
