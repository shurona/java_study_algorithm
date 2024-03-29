import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekTestMain {

    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        // 계급 초기화
        Map<String, Double> gradeWithScore = new HashMap<>();
        gradeWithScore.put("A+", 4.5);
        gradeWithScore.put("A0", 4.0);
        gradeWithScore.put("B+", 3.5);
        gradeWithScore.put("B0", 3.0);
        gradeWithScore.put("C+", 2.5);
        gradeWithScore.put("C0", 2.0);
        gradeWithScore.put("D+", 1.5);
        gradeWithScore.put("D0", 1.0);
        gradeWithScore.put("F", 0.0);

        String[] input = new String[20];
        for (int i = 0; i < 20; i++) {
            input[i] = bfr.readLine();
        }

        bfr.close();

        ArrayList<Double> scoreList = new ArrayList<>();
        ArrayList<Double> mulList = new ArrayList<>();

        for (String line : input) {
            String[] splitLine = line.split(" ");
            String grade = splitLine[2];
            String score = splitLine[1];

            if (!Objects.equals(grade, "P")) {
                if (Objects.equals(grade, "F")) {
                    scoreList.add(Double.parseDouble(score));
                    mulList.add(0.0);
                } else {
                    scoreList.add(Double.parseDouble(score));
                    mulList.add(Double.parseDouble(score) * gradeWithScore.get(grade));
                }
            }
        }

        System.out.println(scoreList.size());
        System.out.println(scoreList);
        System.out.println(mulList);

        Optional<Double> reduce = scoreList.stream().reduce(Double::sum);
        Optional<Double> sumWithMul = mulList.stream().reduce(Double::sum);

        double down = reduce.get();
        double up = sumWithMul.get();

        if (down < 1) {
            System.out.println(0.00000);
        } else {
            System.out.println((Math.round((up/down)*10000000))/10000000.0);
        }
    }
}
